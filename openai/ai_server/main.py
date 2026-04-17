from google import genai
from google.genai import types
from fastapi import FastAPI, Query
from pydantic import BaseModel
import uvicorn
import os
import sample as sp

app = FastAPI()

# gemini-2.5-flash
GOOGLE_API_KEY = os.getenv('GOOGLE_API_KEY')
# GOOGLE_MODEL_NAME = "gemini-2.5-flash"
GOOGLE_MODEL_NAME = "gemini-3.1-flash-lite-preview"
SYSTEM_INSTRUCTION = "당신은 내 담당 영양사야, 냐가 먹은 음식들을 토대로 식단을 관리해줘."
MAX_HISTORY_SIZE=4

client = genai.Client(api_key=GOOGLE_API_KEY)

chat_history = sp.chat_history if sp.chat_history else []
db = sp.db if sp.chat_history else { "history" : [], "summary" : "" }

@app.get("/ask")
async def ask_gemini(prompt: str):
    response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
	)

    return {
    # "question": prompt,
    "message": response.text
  }

@app.get("/translate")
async def translate(text: str = Query(..., description='번역할 문장'),
                    style: str = Query("formal", description='말투: formal(격식), casual(반말), business(비즈니스)')):
    print(text)
    print(style)
    # return{"answer" : "연결"}

    # 아래 모든 줄은 스페이스바 4칸(혹은 8칸)으로 구성되었습니다.
    # 좋은 번력을 위하여 번역 문장을 좋은 프롬프트로 변환
    # I am a boy => 나는 소년이다
    # f : 문자열 안에 변수를 쉽게 넣을 때 사용
    # """ : 문자열이 여러줄이 가능하게 해줌
    prompt = f"""
    아래의 규칙을 지켜서 번역해줘
		1. 대상 문장:{text}
    2. 요청 말투 : {style}말투로 변역해줘
		2. 도착어 : 한국어면 영어로, 영어이면 한국어로 자동 감지해서 번역해줘.
    3. 결과물 ㅣ 번역된 문장외에 다른 설명운 생략해
    """
    
		# Have you heard of that? I was the only one who did't know.
    # => 그거 들어봤어? 나만 몰랐네.
    # casual(반말) => 그거 들어봤어? 나만 몰랐어.
    response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
	)
    
    return {
        "answer": response.text
    }

@app.get("/ad-copy")
def ad_copy(
	product:str = Query(..., description='상품명'),
	feature:str = Query(..., description='제품 특징'),
	target:str = Query('전연령', description='타겟'),
	# 1과 가까울수록 창의적
	temp:float = Query(0.8, ge=0.0, le=1.0, description='창의성 온도(0~1)'),
	count:int = Query(50, description='광고문구 글자제한'),
):
	# return{"answer" : "연결"}

	prompt = f"""
	{product}의 광고 문구를 {target}을 타겟으로 맞춰 {count}자 내외로 작성해줘
	제품 특징 : {feature}
	"""

	response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
    config=types.GenerateContentConfig(
        temperature=temp,
        top_p=0.95,#단어 선택 범위
        top_k=20,#후보 단어 개수
    ),
)

	return { 'answer' : response.text }

class Summary(BaseModel):
	text:str = "대전 오월드에서 탈출한 늑대 늑구가 엿새 만에 다시 발견됐지만,수색 당국은 또다시 포획에 실패했다.14일 대전소방본부에 따르면 전날 오후 10시 43분쯤 오월드 인근 야산에서 늑구를 목격했다는 신고가 119에 접수됐다.수색 당국은 야간 수색에 나서 이날 0시 6분쯤 오월드에서 약 1.8㎞ 떨어진 곳에서 늑구로 추정되는 개체를 확인했다.당국은 열화상카메라가 부착된 드론으로 늑구를 포착한 뒤 주변에 트랩을 설치하고 경찰 기동대까지 추가 투입했다.이어 이날 오전 5시 51분쯤 물가에 있던 늑구를 상대로 마취총 포획을 시도했다.그러나 오전 6시 35분쯤 늑구는 인간띠로 만든 포획망을 뚫고 달아난 것으로 나타났다.수색 당국은 곧바로 재추적에 나서 15분 만에 좌표를 다시 확인했지만, 드론 이동 중 포착에 실패했다.현재는 군 드론 5대를 투입해 추가 수색을 벌이고 있으나 아직 위치는 확인되지 않고 있다.늑구는 지난 8일 오전 9시 18분쯤 대전 오월드 사파리 철조망 아래 땅을 파고 탈출했다. 늑구는 탈출 다음 날인 지난 9일 오전 1시 30분쯤에도 오월드 인근 야산에서 움직이는 모습이 열화상카메라에 찍혔지만,드론 배터리를 교체하는 과정에서 놓친 바 있다.",
	target_lan : str = "Korean" # 어떤언어로 요약하는지
	max_sentence : int = 3 # n문장 요약

@app.post("/summarize")
async def summarize(summary:Summary):

    prompt = f"""
	너는 복잡한 정보를 명료하게 정리하는 전문 편집자야.
	아래 텍스트를 분석해서 {summary.target_lan}로 요약해줘	

	[텍스트]
	{summary.text}

	[작성 가이드]
	1. 핵심 내용을 최대 {summary.max_sentence}개의 문장으로 요약할 것.
	2. 가장 중요한 키워드를 3개 추출할 것.
	3. 객관적이고 중립적인 어조를 유지할 것.

	[출력형식]
	- 요약 : (내용)
	======================
	- 키워드 : #키워드1, #키워드2, #키워드3
	"""
    """
	- 요약 : 삼성SDS는 국회 빅데이터 플랫폼 구축 1단계 사업을 통해 AI 기반 국회 의정 지원 플랫폼을 공식 오픈했으며, 
	이는 국회의 방대한 데이터를 기반으로 검색, 분석, 작성까지 지원하는 생성형 AI 시스템이다. 
	이 플랫폼은 국회의원 및 보좌진 5000여 명이 활용하여 데이터 기반 의정활동을 본격화하고, 
	AI 어시스턴트, 지능형 검색, 법률안 서비스 등 3가지 핵심 의정지원 서비스를 제공한다. 
	삼성SDS는 이번 사업을 통해 AI 전환 자동화, 데이터 거버넌스 체계 수립, 국회 특화 언어모델 도입 등으로 안정적인 데이터 활용 기반을 마련하고 보안성을 강화했다.
	- 키워드 : #AI국회, #의정지원플랫폼, #생성형AI"
	"""

    response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
    config=types.GenerateContentConfig(temperature=0.2),
)
    return { 'answer' : response.text }

# 최근 N개 대화를 이용한 챗봇
@app.get("/chatbot")
async def chatbot_gemini(prompt: str):
	global chat_history
	chat_history.append(types.Content(
		role="user", 
		parts=[types.Part.from_text(text=prompt)]
		))

	response = client.models.generate_content(
		model= GOOGLE_MODEL_NAME,
		contents=chat_history, #types.Part.from_text(text=prompt),
		config=types.GenerateContentConfig(
			temperature=0.7,
			system_instruction=SYSTEM_INSTRUCTION
		),
	)
	model_text = response.text
	# 대화 기록을 저장(추가)
	# 사용자 질문을 저장
	chat_history.append(types.Content(
		role="user", 
		parts=[types.Part.from_text(text=prompt)]
		))
	chat_history.append(types.Content(
		role="model", 
		parts=[types.Part.from_text(text=model_text)]
		))

	# 대화 기록이 최대 저장수를 넘어가면 앞부분 제거
	if(len(chat_history) >= MAX_HISTORY_SIZE):

		chat_history = chat_history[-10:] # 뒤에서 10개 추출

	# 답변을 리턴
	return {
		"message": model_text
	}
async def update_summary():
	to_summarize = db["history"][:-2] #마지막 2개 제외

	prompt = f"""
	기존 요약 : {db['summary']}
	추가된 대화 : {to_summarize}

	위 내용을 바탕으로 지금가지의 대화 맥락을 한 문장으로 업데이트해줘.
	사용자의 주요 관심사와 언급된 핵심 사실을 포함해줘
	"""

	response = client.models.generate_content(
    model=GOOGLE_SUMMARY_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
    config=types.GenerateContentConfig(
			temperature=0.7,
			system_instruction=SYSTEM_INSTRUCTION
		)
	)
	model_text = response.text
	
@app.get("/summary-chatbot")
async def chatbot_gemini(prompt:str):
    db["history"].append(types.Content(
	    role = "user",
        parts = [types.Part.from_text(text=prompt)]))

    response = client.models.generate_content(
        model=GOOGLE_MODEL_NAME,
        contents=types.Part.from_text(text=prompt),
        config=types.GenerateContentConfig(
		    temperature=0.7,
		    system_instruction=SYSTEM_INSTRUCTION
		),
	)
    model_text = response.text
		
    db["history"].append(types.Content(
	    role = "model",
        parts = [types.Part.from_text(text=model_text)]))

    if(len(chat_history) >= MAX_HISTORY_SIZE):
        await update_summary()
				
    # 답변을 리턴
    return {
		"message": model_text,
		"summary": db["summary"]
	}
if __name__ == '__main__':
	uvicorn.run('main:app',host='0.0.0.0', port=8000, reload=True)