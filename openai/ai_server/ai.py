import os
from google import genai
from google.genai import types
import pymysql

def get_user_profile_from_db(num):
    # DB 연결 설정
    connection = pymysql.connect(
        host='localhost',
        port=3306,
        user='root',
        password='root',
        db='nf',
        charset='utf8mb4',
        cursorclass=pymysql.cursors.DictCursor,
    )
    
    try:
        with connection.cursor() as cursor:
            # 사용자의 목표나 알레르기 정보를 가져온다고 가정
            sql = "SELECT * FROM test1 WHERE NUM = %s" 
            cursor.execute(sql, (num))
            result = cursor.fetchone()
            return result 
    finally:
        connection.close()
        
# 1. 초기 설정
GOOGLE_API_KEY = os.getenv('GOOGLE_API_KEY')
client = genai.Client(api_key=GOOGLE_API_KEY)

# 모델 설정 (최신 모델명 확인 필요)
MODEL_NAME = "gemini-3.1-flash-lite-preview" 

# DB에서 2번 유저 정보를 미리 가져옵니다.
user_profile = get_user_profile_from_db(2)

# --- 3. 시스템 지시문 (DB 데이터를 주입!) ---
# DB에서 가져온 NAME(TEST2)을 AI에게 알려줍니다.
SYSTEM_INSTRUCTION = f"""
당신은 세상에서 가장 친절하고 귀여운 '냥이 영양사'입니다. 
지금 대화하는 사용자의 이름은 '{user_profile['NAME']}'(번호: {user_profile['NUM']})입니다.

[페르소나]
- 말투: "~했냥?", "~다냥!", "웅웅!" 같은 고양이 말투를 사용하세요.
- 역할: 사용자의 식단을 기록하고, 아래 영양 가이드를 바탕으로 조언해줍니다.

[사용자 취향 정보]
- 좋아하는 음식: {user_profile['LIKE']}
- 싫어하는 음식: {user_profile['HATE']}

[영양 가이드라인]
1. 하루 총 권장 섭취량: 2,500kcal (3끼 분할 섭취)
2. 권장 탄/단/지 비율:
   - 탄수화물: 55%~65%
   - 단백질: 7%~20%
   - 지방: 15%~30%
3. 끼니별 권장량 및 팁:
   - 아침 (25~30%, 650~750kcal): 복합 탄수화물(통곡물) 위주로 뇌 에너지 공급냥!
   - 점심 (35~40%, 850~1000kcal): 하루 중 가장 든든하게! 단백질과 채소를 충분히 먹어야한다냥!
   - 저녁 (25~30%, 650~750kcal): 소화가 잘 되는 단백질 위주로 먹고, 과식은 절대 금지다냥!

[답변 규칙]
- 기본적으로는 좋아하는 음식({user_profile['LIKE']}) 위주로 추천해줘냥.
- 싫어하는 음식({user_profile['HATE']})은 원칙적으로 피하되, 만약 그 음식이 '건강한 식재료(예: 채소, 발효식품 등)'라면 {user_profile['NAME']}님의 건강을 위해 아주 가끔은 조심스럽게 권해보라냥!
- 단, 튀김이나 자극적인 음식은 아무리 좋아해도 영양사답게 건강한 조리법으로 유도해야 한다냥!
- 사용자가 "뭐 먹을까?"라고 물어보면, 현재 시간(아침/점심/저녁)을 고려해서 위 가이드라인에 맞는 메뉴를 추천해줘냥.
- 답변할 때 가끔 "{user_profile['NAME']}님 반갑다냥!" 혹은 "{user_profile['NAME']}님 힘내라냥!" 처럼 이름을 불러주세요.
"""

# 데이터베이스 (메모리 저장용)
db = {
    "history": [], # 대화 객체 저장
    "summary": ""  # 누적 요약본
}

MAX_HISTORY_SIZE = 6 # 6개가 넘어가면 요약 시작 (사용자3 + 모델3)

def get_chat_response(user_input):
    # 1. 이전 요약 정보와 함께 프롬프트 구성
    context = f"[지금까지의 요약]: {db['summary']}\n" if db['summary'] else ""
    full_prompt = f"{context}사용자: {user_input}"

    # 2. 모델에게 답변 받기
    response = client.models.generate_content(
        model=MODEL_NAME,
        contents=db["history"] + [types.Content(role="user", parts=[types.Part.from_text(text=full_prompt)])],
        config=types.GenerateContentConfig(
            system_instruction=SYSTEM_INSTRUCTION,
            temperature=0.8, # 창의적이고 통통 튀는 답변을 위해 상향
        )
    )
    
    model_answer = response.text
    
    # 3. 히스토리에 저장
    db["history"].append(types.Content(role="user", parts=[types.Part.from_text(text=user_input)]))
    db["history"].append(types.Content(role="model", parts=[types.Part.from_text(text=model_answer)]))
    
    # 4. 히스토리 개수 관리 (요약 로직)
    if len(db["history"]) > MAX_HISTORY_SIZE:
        manage_history()
        
    return model_answer

def manage_history():
    # 최근 2개를 제외한 나머지를 요약
    to_summarize = db["history"][:-2]
    
    summary_prompt = f"""
    기존 요약 내용: {db['summary']}
    추가된 대화: {to_summarize}
    
    위 내용을 바탕으로 식단 기록 중심의 대화 맥락을 한 문장으로 업데이트해줘냥!
    """
    
    summary_res = client.models.generate_content(
        model=MODEL_NAME,
        contents=[types.Part.from_text(text=summary_prompt)],
        config=types.GenerateContentConfig(system_instruction="너는 요약 전문가다냥!")
    )
    
    db["summary"] = summary_res.text
    db["history"] = db["history"][-2:] # 최근 2개만 남기고 삭제
    print(f"\n✨ 요약 완료: {db['summary']}\n")

# --- 실행 테스트 ---
print(get_chat_response("냥이야, 나 오늘 점심 뭐 먹을까?"))
print("--------------------------------------------------------------------------")
print(get_chat_response("점심에 빵만 먹었는데 저녁에 점심 부족한거 다시 저녁에서 먹고싶은데 괜찮을까?"))