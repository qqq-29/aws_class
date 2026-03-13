# 네이버 뉴스 가져오는 기능들이 

# 위 예제를 이용하여 키워드가 주어졌을 때 뉴스 목록을 가져와서 데이터프레임으로 반환하는
# 함수를 만들고 테스트 하세요
import requests
from bs4 import BeautifulSoup
import pandas as pd
import time

def get_naver_news_list(keyword : str, start : int=1):

	url = f'https://search.naver.com/search.naver'

	params = {
		'where' : 'news',
		'query' : keyword,
		'start' : start,
	}
	headers = {
		'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36',
		'Referer' : 'https://www.naver.com/'
	}
	# 뉴스 검색 화면을 요청
	response = requests.get(url, params=params, headers=headers)
	
	try:
		# 연결
		response.raise_for_status()
		
		# Beautifulsoup 객체 생성
		soup = BeautifulSoup(response.text, 'lxml')

		# 뉴스 박스들
		links_divs = soup.select('.n8_DDlCjHxLYRO50CY50')

		hrefs, titles = [], []
		# 반복문으로 제목과 링크를 추출하여 리스트에 담음
		for link_div in links_divs:
			# 기사 제목링크 => 여기에서 기사 제목 추출
			title_link = link_div.select_one('.XEtVZ4N7DI2Pv29xe7f3')
			# 실제 기사 네이버링크 => 여기에서 기사 링크 추출
			href_link = link_div.select_one('.RQNKk8QZaQZble3gmgEj [data-heatmap-target=".nav"]')
			# 리스트에 제목과 링크를 추가
			hrefs.append(href_link.get("href") if href_link else None)
			titles.append(title_link.text)

		return pd.DataFrame({
			'title' : titles,
			'href' : hrefs,
		})
	except Exception as e:
		print(f"예외 발생 : {e}")
		return None
	
# 뉴스 리트스 DataFrame을 csv로 저장
def save_nl(df:pd.DataFrame,keyword:str, start:int = 1):
	if df is None or df.empty:
		return
	df.to_csv(f'naver_nl_{keyword}_{start//10+1}.csv', index=False, encoding='utf-8-sig')

def get_naver_news_article(url):
	"""네이버 기사에서 내용을 추출하여 반환"""

	headers = {
		'User-Agent' : 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36',
		'Referer' : 'https://www.naver.com/'
	}
	response = requests.get(url, headers=headers)
	try:
		response.raise_for_status()

	except Exception as e:
		print(f"예외 발생 {e}")
		return None


	soup = BeautifulSoup(response.text, 'lxml')
	artical = soup.select_one('#dic_area')
	return artical.text


# 모듈 테스트
if __name__ == "__main__":
	start = 1
	# start = 41
	keyword = 'ai'
	nl_df = get_naver_news_list(keyword,start)
	for href in nl_df['href']:
		print(get_naver_news_article(href))
	# save_nl(nl_df, keyword, start)
