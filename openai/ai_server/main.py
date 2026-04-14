from google import genai
from google.genai import types
from fastapi import FastAPI, Query
from pydantic import BaseModel
import uvicorn
import os

app = FastAPI()

# gemini-2.5-flash
GOOGLE_API_KEY = os.getenv('GOOGLE_API_KEY')
GOOGLE_MODEL_NAME = "gemini-2.5-flash"
client = genai.Client(api_key=GOOGLE_API_KEY)

# @app.get("/ask")
@app.post("/ask")
async def ask_gemini(prompt: str):

	response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
	)

	return {
    "question": prompt,
    "answer": response.text
  }

@app.get("/translate")
async def translate(text: str = Query(..., description='번역할 문장'),
                    style: str = Query("formal", description='말투: formal(격식), casual(반말), business(비즈니스)')):
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
	아래 텍스트를 보고 간단히 {summary.target_lan}언어로 {summary.max_sentence}개 문장으로 요약해주세요.
	그리고 가장 중요한 키워드를 3개 추출할 것.객관적이고 중립적인 어조를 유지할것
	텍스트 : {summary.text}

	[출력형식]
	- 요악 : (내용)
	- 키워드 : #키워드1, #키워드2, #키워드3,
	"""

	response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
    config=types.GenerateContentConfig(temperature=0.2),
)
	return { 'answer' : response.text }

if __name__ == '__main__':
	uvicorn.run('main:app',host='0.0.0.0', port=8000, reload=True)