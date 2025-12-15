# DML : 데이터 추가, 수정, 삭제, 조회

# 테이블 이름만 하는 경우 => 내가 작업하려는 DB가 선택 되어 있어야 정상 동작
# DB명.테이블명을 이용하는 경우 => 현재 선택한 DB종류 상관없이 동작
# DB명.테이블명 == `DB명`, `테이블명`
# ``를 반드시 써야하는 경우

# 데이터 추가 : insert
# insert values문 => 내가 추가하려는 값들을 모두 알고 있는 경우
# insert into 테이블명 values(값1, 값2, ...),(값1, 값2, ...)
# 값들은 테이블의 속성 개수만큼 작성해야 함. 속성의 개수가 5개이면 값도 5개
# 값들은 테이블의 속성 순서에 맞게 값 순서를 결정해야 함.
# 이 방법을 이용하여 추가하는 경우 테이블 구성은 같지만 속성 순서가 다를 때 적용되지 않을 수 있다
use sample;
# insert into student values(1, 1, 1, 1, "홍길동");

# insert into 테이블명(컬럽1, 컬럽2,...,컬럽N) values(값1, 값2,...,값N)
insert into student(grade, class, num, name) values(1, 1, 2, "임꺽정");
insert into sample.student(grade, class, num, name) values(1, 1, 3, "고등어");

#insert select문 => 내가 추가하려는 값 일부를 검색해서 사용해야 하는 경우

# 테이터 수정 : update
# update 테이블

/*
	set 
		컬럼1 = 값1,
        컬럼2 = 값2
	where
		조건
*/

# SQL에서 =은 대입연산자가 아니라 비교 연산자 == 의미
# =이 대입연산자 역할을 하려면 앞에 set이 나와야 함
# 같다 : =, 같지 않다 : != 또는 <>
# where이 없으면 모든 행을 수정
# 자바에서 &&연산자가 MySQL에서는 AND, ||연산자가 OR
# 자바에서는 &&와 || 연산자 우선순위가 같지만
# MySQL에서는 AND가 OR보다 연산자 우선순위가 높음
# MySQL에서는 AND가 OR보다 연산자 우선순위가 높음

# 아래 코드 실행시 에러가 발생(워크벤치에서)
# 워크벤치에서는 기본키를 이용하지 않은 수정이나 삭제를 방지(기본값)
# 설정 변경 방법
# Edit > Preferences...
# >SQL Editor에서 제일 하단에 Safe updates(~~~) 체크박스를 해제 
# 아래 코드를 MySQL Command Line Client 에서는 에러 없이 실행
# 1학년 1반 1번 학생 이름이 홍깅동에서 가길동으로 수정
update student 
	set 
		name = '가로등'
	where 
		grade = 1 
        and class = 1 
        and num= 1;

# 모든 학생을 2학년으로
update student
	set
		grade = 2;

# 삭제 : delete
# delete from 테이블명 where 조건
delete from student
	where
		grade = 2 and class = 1 and num = 1;
# 전체 학생 삭제
delete from student;
delete from score;
delete from subject;

# 샘플 데이터 추가
insert into student(grade, class, num, name) values(1, 1, 1, "홍길동");
insert into student(grade, class, num, name) values(1, 1, 2, "임꺽정");
insert into student(grade, class, num, name) values(1, 2, 1, "홍가네");
insert into student(grade, class, num, name) values(2, 1, 1, "고길등");

# 과목추가
# 1학년 1학기 국어 추가
insert into subject(grade, semester, title) values(1, 1, "국어");
# 1학년 1학기 수학 추가
insert into subject(grade, semester, title) values(1, 1, "수학");
# 1학년 2학기 국어 수학 추가
insert into subject(grade, semester, title) values(1, 2, "국어"),(1, 2, "수학");
# 2학년 1학기 국어 수학 추가
insert into subject(grade, semester, title) values(2, 1, "국어"),(2, 1, "수학");

# 성적추가
SELECT * FROM sample.student;
# 1학년 1반 1번 학생의 1학년 1학기 성적 - 국어 : 90, 수학 : 80
insert into score(st_code, sj_code, score)
value(26,8,90),(26,9,80);
# 1학년 1반 2번 학생의 1학년 1학기 성적 - 국어 : 100, 수학 : 100
insert into score(st_code, sj_code, score)
value(22,10,100),(22,11,100);
# 1학년 2반 1번 학생의 1학년 1학기 성적 - 국어 : 50, 수학 : 100
insert into score(st_code, sj_code, score)
value(23,10,50),(23,11,100);
# 2학년 1반 1번 학생의 2학년 1학기 성적 - 국어 : 100, 수학 : 50
insert into score(st_code, sj_code, score)
value(24,12,100),(24,13,50);