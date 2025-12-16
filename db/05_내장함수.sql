# 내장 함수
# if(수식, 수식1, 수식2) : 수식이 참이면 수식1을, 거짓이면 수식2를 반환
SELECT IF(2>1, 2, 1);

# IFNULL(수식1, 수식2) : 
# - 수식1이 NULL이 아니면 수식1을, NULL이면 수식2를 반환 
# - 수식1값이 NULL일 때 어떻게 처리할지를 정할 때 사용
SELECT ifnull(NULL, 0), IFNULL(1, 0);
# NULLIF(수식1, 수식2) : 수식1과 수식2가 같으면 NULL, 다르면 수식1을 반환
SELECT NULLIF(1,1),NULLIF(1,0);

# CASE 속성
#   WHEN 값1 THEN 결과
#   WHEN 값2 THEN 결과
#   ELSE 결과
# END
# NUM그쪽만 비교하고 결과나온다
USE SHOPPINGMALL2;
SELECT CASE NUM
	WHEN 1 THEN "악세서리"
    ELSE "기타"
END 결과 FROM PRODUCT;

# CASE
# WHEN 조건 THEN 결과
# ELSE 결과
# END
# PRODUCT전부다 나온다
SELECT PRODUCT.* ,CASE 
	WHEN PRICE > 50000 THEN "비쌈"
    ELSE "적당함"
END 결과 FROM PRODUCT;

# 문자열 내장함수
# CHAR_LENGTH(문자열) : 문자열의 길이
# LENGTH(문자열) : 문자열의 바이트수
SELECT 
	CHAR_LENGTH("ABC") 'ABC길이', LENGTH("ABC") ABC크기,
    CHAR_LENGTH("가나다") '가나다길이', LENGTH("가나다") 가나다크기;
    
# CONCAT(문자열1, 문자열2, ...) : 문자열을 이어 붙임
SELECT CONCAT("%", "검색어", "%");

# FIELD(찾을문자열, 문자열1, 문자열2, ...) : 찾을 문자열이 어디 있는지 위치를 반환
# 1부터 시작, 없으면 0 반환
SELECT FIELD("ABC", "ABC123", "123ABC", "ABC", "123ABC");

# INSTR(기준문자열, 부분문자열) : 기준문자열에서 부분문자열을 찾아 시작 위치를 반환
# LOCATE(부분문자열, 기준문자열) : 기준문자열에서 부분문자열을 찾아 시작 위치를 반환
SELECT INSTR("ABC123", "123"), LOCATE("123", "ABC123");

# FORMAT(숫자, 소수점자리수) : 숫자를 소수점 이하 자리까지 표현, 1000단위마다, 추가
SELECT CONCAT(FORMAT(1000000, 0), "원");

# BIN(숫자), OCT(숫자), HEX(숫자) : 2,8,16진수로 변환
SELECT BIN(15), OCT(15), HEX(15);

# INSERT(기준문자열, 위치, 길이, 삽입할문자열) : 기준문자열에서 위치부터 길이만큼 지우고
# 그위치에 삽입할문자열추가
select insert("I LOVE JAVA",8, 4, "PYTHON");

# LEFT(문자열, 길이) , RIGHT(문자열, 길이) : 왼쪽/오른쪽부터 길이만큼 문자열을 반환
select left("ACC001", 3)카테고리명, right("BIRD.JPG", 3)확장자;

# LOWER(문자열), UPPER(문자열) : 모든 문자열을 소문자/대문자로
select lower("aBCdef"), upper("aBCdef");

#LPAD(문자열, 길이, 채울문자열), RPAD(문자열, 길이, 채울문자열)
# - 문자열을 길이만큼 늘리고 원쪽/오른쪽 빈곳을 채울문자열로 채움
select lpad(1, 3, '0'), RPAD(1, 3, '0');
select concat('ACC', LPAD(1,3,'0')) 제품코드;
select concat('ACC', RPAD(1,3,'0')) 제품코드;

# LTRIM, RTRIM, TRIM(문자열) : 문자열 왼쪽/오른쪽/양쪽 공백을 제거
select 
	ltrim("   ABC   "),char_length(ltrim("   ABC   ")),
    Rtrim("   ABC   "),char_length(Rtrim("   ABC   ")),
    trim("   ABC   "),char_length(trim("   ABC   "));

# REPEAT(문자열, 횟수) : 문자열을 횟수만큼 반복
select repeat("ABC", 3);

# REVERSE(문자열, 횟수) : 문자열을 횟수만큼 반복
select REVERSE("ABC");

# REPLACE(문자열, 문자열A, 문자열B) : 문자열에서 문자열A를 찾아 문자열B로 교체
select REPLACE("I LOVE JAVA","JAVA", "PYTHON");

# SUBSTRING(문자열, 시작위치, 길이) : 문자열에서 시작위치부터 길이만큼 부분문자열을 반환
select SUBSTRING("I LOVE JAVA", 8, 4);

# 날짜/시간 함수
# NOW(), SYSDATE() : 현재 시간을 반환
select now(), sysdate();

# ADDDATE/SUBDATE(날짜, 차이)
# 날짜를 기준으로 차이만큼 더한/뺀 날짜를 반환
select adddate(NOW(), 2), subdate(NOW(), 2);

# ADDTIME/SUBTIME(날짜/시간, 차이)
# 날짜/시간을 기준으로 차이만큼 더한/뺀 날짜/시간을 반환
# 차이에 "시:분:초" 형태로 넣어주어야 함.
select addtime(NOW(), "3:00:00"), subtime("17:00:00", "2:00:00");

# YEAR/MONTH/DAY(날짜) : 날짜에서 년/월/일을 반환
select year(NOW()), MONTH(now()), DAY(NOW());

# HOUR/MINUTE/SECOND/MICROSECOND(시간) : 시간에서 시/분/초/마이크로초를 반환
select
	hour(NOW()) 시,
    minute(NOW()) 분,
    second(NOW()) 초,
    microsecond("2025-12-16 12:13:12.123") 마이크로초;

# DATE/TIME(날자) : 날짜에서 년-월-일/시:분:초를 추출
select date(NOW()), TIME(NOW());

# DATEDIFF(날짜1, 날짜2) : 날짜1에서 날짜2의 차이를 일로 반환. 날짜1-날짜2
select datediff("2026-06-01", NOW()) 남은수강일;

# TIMEDIFF(날짜1/시간1, 날짜2/시간2)
# - 날짜1/시간1에서 날짜/시간2의 차이를 시:분:초로 반환. 날짜1/시간1 - 날짜2/시간2
select timediff("12:20:00", TIME(NOW()));

# DATA_ADD/DATE_SUB(기준날짜, INTERVAL)
# - 기준날짜에서 INTERVAL만큼 더한/뺀 날짜
# - INTERVAL 종류
#	- 시간유형
#	  - YEAR/MONTH/DAY : 년/월/일
#	  - HOUR/MINUTE/SECOND/MICROSECOND : 시/분/초/마이크로초
#	  - QUARTER/WEEK : 분기/주 
#	- 조합유형 
#	  - YEAR_MONTH : 년월
#	  - DAY_HOUR(일 시간), DAY_MINUTE(일 시간:분),
#		DAY_MICROSECOND(일 시간:분:초.마이크로초)
SELECT
	DATE_ADD(NOW(), INTERVAL 1 YEAR) 1년후,
    DATE_ADD(NOW(), INTERVAL 1 QUARTER) 1분기후,
    DATE_ADD(NOW(), INTERVAL "1 1" YEAR_MONTH) 1년1달후,
    DATE_ADD(NOW(), INTERVAL "2 3:00:00.000000" DAY_MICROSECOND) 2일3시간후;
