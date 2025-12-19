# 뷰
# - 사용자에게는 일반 테이블과 똑같이 보이는 가상 테이블
# - select문이 저장된 객체
# - 왜?
# 	- 보안 : 민감한 컬럼을 숨겨 필요한 정보만 보여줌
#   - 편의성 : 여러개의 테이블을 join한 뷰를 만들면 join 없이 결과를 뷰를 통해 확인
#	- 독립성 : 원본 테이블 구조가 바뀌어도 뷰의 쿼리만 수정하면 기존 방식 그대로 조회가 가능

# 뷰 생성
/*
create view 뷰이름 as
select문;
*/
create view LECTURE_SUBJECT as
select * from lecture join subject on lt_sj_code = sj_code;

# 뷰 조회
# select * from 뷰이름
select * from LECTURE_SUBJECT;

# 뷰 삭제
# drop view 뷰이름;
drop view LECTURE_SUBJECT;

# 생성된 뷰 조회(테이블 포함)
show full tables;

# 뷰만 조회
show full tables where table_type = 'view';

# 수강에 과목 정보도 포함되는 view를 생성
create view COURSE_SUBJECT AS
select * from course
	JOIN lecture ON co_lt_num = lt_num
    JOIN subject ON lt_sj_code = sj_code;
    
# 학생별 총 이수학점을 조회
select st_num, st_name, ifnull(sum(sj_point), 0) from 
	( select * from COURSE_SUBJECT
		where
			co_score is not null and co_score not in('F', 'FALL'))C
	right JOIN student ON st_num = CO_ST_NUM
    group by st_num;

# 교수가 학생을 조회할 때 주민번호를 안보이게 하기위해 뷰를 생성
create view view_student as
select st_num, st_name, st_contact, st_year from student;

select * from view_student;


