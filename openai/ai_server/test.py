# from google import genai
# from google.genai import types
# from chromadb.config import Settings
# import pypdf

# def log(text:str):
# 	print("-"*50)
# 	print(text)
# 	print("-"*50)

# def ingest_pdf(file_path:str, size : int):
# 	log("지식 학습 시작")
              

# 	with open(file_path, "rb") as f:
# 		pdf_reader = pypdf.PdfReader(f)
# 		texts = [page.extract_text() or "" for page in pdf_reader.pages]
# 		full_text = "".join(texts)

# 		if not full_text.strip():
# 			log("PDF에서 읽어올 글자가 없습니다.")
# 		chunks = [full_text[i:i+size] for i in range(0, len(full_text), size)]
# 		log("[분할 완료]")
# 		print(f"Success: 총 {len(chunks)}개의 청크가 생성")
# 		return chunks


# # 사용 예시
# ingest_pdf('2026_미래소프트_복지규정.pdf', 100)


#------------------------------------------------------------------------------------


import os
import time
import chromadb
from google import genai

from google import genai
from google.genai import types
from chromadb.config import Settings
import pypdf

def log(text:str):
	print("-"*50)
	print(text)
	print("-"*50)

def ingest_pdf(file_path:str, size : int):
	log("지식 학습 시작")
              

	with open(file_path, "rb") as f:
		pdf_reader = pypdf.PdfReader(f)
		texts = [page.extract_text() or "" for page in pdf_reader.pages]
		full_text = "".join(texts)

		if not full_text.strip():
			log("PDF에서 읽어올 글자가 없습니다.")
		chunks = [full_text[i:i+size] for i in range(0, len(full_text), size)]
		log("[분할 완료]")
		print(f"Success: 총 {len(chunks)}개의 청크가 생성")
		return chunks
result_chunks = ingest_pdf("2026_미래소프트_복지규정.pdf", 100)

GOOGLE_API_KEY = os.getenv('GOOGLE_API_KEY')
EMBED_MODEL = "gemini-embedding-2-preview" 
CHROMA_PATH = './knowledge_base'

client = genai.Client(api_key=GOOGLE_API_KEY)
chroma_client = chromadb.PersistentClient(path=CHROMA_PATH)
collection = chroma_client.get_or_create_collection(name="class_knowledge")

def store_chunks_to_db(chunks: list, file_path: str):

    try:
        embeddings = []
        for chunk in chunks:
            embed_res = client.models.embed_content(
                model=EMBED_MODEL,
                contents=chunk  
            )
            # 결과물에서 실제 벡터(숫자 리스트)만 추출해서 추가
            embeddings.append(embed_res.embeddings[0].values)

        collection.add(
            ids=[f"{os.path.basename(file_path)}_{i}_{int(time.time())}" for i in range(len(chunks))],
            embeddings=embeddings, 
            documents=chunks,
            metadatas=[{"source": file_path} for _ in chunks]
        )
        
        print(f"성공: {len(chunks)}개의 데이터가 벡터 DB에 저장되었습니다.")

    except Exception as e:
        print(f"저장 중 오류 발생: {e}")

store_chunks_to_db(result_chunks, "2026_미래소프트_복지규정.pdf")