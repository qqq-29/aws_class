START transaction;
# 컴퓨터공학과를 등록
# 코드 : 160, 사무실 : 하이미디어 구리관 401호, 이름 : 컴퓨터공학과
insert into major (mj_code, mj_office, mj_name)
values('160', '하이미디어 구리관 401호', '컴퓨터공학과');
# 디자인과를 등록
# 코드 :123, 사무실 : 하이미디어 구리관 301호, 이름 : 디자인과
insert into major (mj_code, mj_office, mj_name)
values('123', '하이미디어 구리관 301호', '디자인과');

# 기계공학과를 등록
 # 코드 :456, 사무실 : 하이미디어 구리관 201호, 이름 : 기계공학과
insert into major (mj_code, mj_office, mj_name)
values('456', '하이미디어 구리관 201호', '기계공학과');
 
 COMMIT;
 
 START TRANSACTION;
 
 # 홍길동(2025160001), 고길등(2025160002)은
 # 지도 교수님이 홍교수님(P2025160001)으로 배정
 UPDATE student 
	SET st_pr_num = "P2025160001"
    # st_num = "2025160001" or st_num = "2025160002";
    where st_num in("2025160001","2025160002");
 
 
# 김길동(2025160003), 하나(2025160004)은
# 지도 교수님이 김교수님(P2025160002)으로 배정
 UPDATE student 
	SET st_pr_num = "P2025160002"
    where st_num in("2025160003","2025160004");
    
# 학번과 강의 번호가 주어지면 성적들을 이용하여 학점을 계산하는 프로시저
# 출석이 60미만이면 성적에 상관없이 F
# 비율은 중간 35%, 기말 45%, 과제 10%, 출석10%
# 80, 90, 50, 100 => 28, 40.5, 5, 10 => 83.5
# A+ : 95이상, A : 90이상, B+ : 85이상, B : 80이상
# C+ : 75이상, C : 70이상, D+ : 65이상, D : 60이상, F : 60미상
    
# 프로시저명 : SCORE, IN : _ST_NUM, _LT_NUM
DROP PROCEDURE IF EXISTS SCORE;
DELIMITER $$
CREATE PROCEDURE SCORE(
	IN _st_num CHAR(10),
    IN _lt_num INT
)
BEGIN
    
	DECLARE _mid int;
    declare _final int;
    declare _hw int;
    declare _att int;
    declare _total double;
    declare _score varchar(2);
    
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
	END;

    START TRANSACTION;
    
    set _mid = (select co_mid from course 
					where co_st_num = _st_num and co_lt_num = _lt_num);
	set _final = (select co_final from course 
					where co_st_num = _st_num and co_lt_num = _lt_num);
	set _hw = (select co_hw from course 
					where co_st_num = _st_num and co_lt_num = _lt_num);
	set _att = (select co_att from course 
					where co_st_num = _st_num and co_lt_num = _lt_num);
    
    # 성적을 계산 = 중간 * 0.35 + 기말 * 0.45 + 과제 * 0.1 + 출석 * 0.1
    set _total = _mid * 0.35 + _final * 0.45 + _hw * 0.1 + _att * 0.1; 
    
    # 성적에 따라 학점을 업데이트
    # 출석이 60미만이면 학점을 F로 업데이트
    if _att < 60 then
		set _score = 'F';
	# A+
    ELSEIF _total >= 95 THEN
		set _score = 'A+';
	ELSEIF _total >= 90 THEN
		set _score = 'A';
	ELSEIF _total >= 85 THEN
		set _score = 'B+';
	ELSEIF _total >= 80 THEN
		set _score = 'B';
	ELSEIF _total >= 75 THEN
		set _score = 'C+';
	ELSEIF _total >= 70 THEN
		set _score = 'C';
	ELSEIF _total >= 65 THEN
		set _score = 'D+';
	ELSEIF _total >= 60 THEN
		set _score = 'D';
	ELSE
		set _score = 'F';
	end if;
    
    UPDATE course
    set 
		co_score = _score
	where
		co_st_num = _st_num and co_lt_num = _lt_num;
    COMMIT;
END $$
DELIMITER ;
    
call score('2025160001', 6);
    
    
    
    
    
    