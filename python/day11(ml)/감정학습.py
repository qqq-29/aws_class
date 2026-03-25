# # konlpy를 import할떄 생기는 에러처리를 워한 초기화
# def init(java_path:str):
# 	import jpype
# 	import os
# 	# jvm 강제 종료
# 	if jpype.isJVMStarted():
# 		jpype.shutdownJVM()

# 	# 환경 변수에 JAVA_HOME이라는 변수를 등록
# 	# 일반 문자열에서 \는 기능이 있음. \문자를 하려면 \\해야함
# 	os.environ['JAVA_HOME'] = java_path

# init(r'C:\Users\hi6\Documents\JAVA CLASS\IDE\JDK\jdk_21')

# import pandas as pd
# from konlpy.tag import Okt
# from sklearn.feature_extraction.text import TfidfVectorizer
# from sklearn.linear_model import LogisticRegression
# import joblib as jl

# def text_preprocessing(text):
# 	okt = Okt()
# 	# stem=True : 동사를 동사 원형으로 바꿈
# 	result = okt.pos(text, stem=True)
# 	# 반복문 결과를 리스트를 저장
# 	words = [word for word, pos in result if pos in ['Noun', 'Verb', 'Adjective']]
# 	return " ".join(words)

# def train_and_save_model(dataset_x, dataset_y):
# 	# 형태소 분리
#   dataset_pp_x = dataset_x.apply(text_preprocessing)
# 	# 벡터화
#   vectorizer = TfidfVectorizer()
# 	# 독립, 종속 분리
#   x = vectorizer.fit_transform(dataset_pp_x)
#   y = dataset_y
#   # 학습
#   model = LogisticRegression()
#   model.fit(x, y)
# 	# 모델과 벡터라이저를 저장
#   model_data = {
# 		'vectorizer' : vectorizer,
# 		'model' : model
#   }
#   jl.dump(model_data, 'model.pkl')

# if __name__ == '__main__':
# 	# print(text_preprocessing("오늘은 날이 너무 너무 좋습니다."))
# 	df = pd.read_csv(r'C:\Users\C603\Documents\AWS Class\git\aws_class\python\day11(ml)\sample.csv')
# 	train_and_save_model(df['sentence'], df['label'])
# 	pass


# konlpy를 import할 때 생기는 에러 처리를 위한 초기화
def init(java_path:str):
	import jpype
	import os

	# 켜져 있다면 완전히 끄기
	if jpype.isJVMStarted():
			jpype.shutdownJVM()

	#환경 변수를 코드 상단에서 다시 세팅
	os.environ['JAVA_HOME'] = java_path
  

init(r'C:\Users\C603\Documents\AWS Class\ide\jdk\jdk-21')

import pandas as pd 
from konlpy.tag import Okt
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
import joblib as jl

# 문장에서 명사, 동사, 형용사면 원형을 추출해서 문장을 만들어 반환
def text_preprocessing(text):
	okt = Okt()
	result = okt.pos(text, stem=True)
	words = [word for word, pos in result if pos in  ['Noun', 'Verb', 'Adjective']]
	return " ".join(words)

def train_and_save_model(dataset_x, dataset_y):

	# 형태소 분리
	dataset_pp_x = dataset_x.apply(text_preprocessing)
	#벡터화 
	vectorizer = TfidfVectorizer()
	# 독립, 종속 분리
	X = vectorizer.fit_transform(dataset_pp_x)
	y = dataset_y
	# 학습
	model = LogisticRegression()
	model.fit(X, y)
	# 모델과 벡터라이저를 저장
	model_data = {
		'vectorizer' : vectorizer, 
		'model' : model
	}
	jl.dump(model_data, 'model.pkl')
	
def predict(text):
	# 모델과 벡터라이저를 불러옴
	mode_data = jl.load('model.pkl')
	model , vectorizer = mode_data['model'], mode_data['vectorizer']
	
  # 형태소가 처리된 문자열로 변환
	cleaned_text = text_preprocessing(text)
	
  # 문장을 벡터화
	vector_text = vectorizer.transform([cleaned_text])
	
  # 예측
	predict = model.predict(vector_text)
	# 예측 결과를 반환(0,1)
	return predict[0]

if __name__ == '__main__':
	# print(text_preprocessing("오늘은 날이 너무 너무 좋습니다."))
	# 테스트 할 때 sample.csv를 인식하게 하기 위해 cd "day11(ml)" 명령어 입력 후 실행
	# df = pd.read_csv(r'sample.csv')
	# train_and_save_model(df['sentence'], df['label'])
	print(predict('오늘은 날이 너무 너무 좋습니다.'))
	print(predict('날씨가 화창한데 자격증 시험에 떨어졌습니다.'))
	print(predict('상사한테 혼났습니다.'))