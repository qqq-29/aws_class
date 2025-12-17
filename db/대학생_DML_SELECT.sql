# 학과별 등록된 교수들를 조회하는 쿼리
select * from major join professor on mj_code = pr_mj_code 
group by mj_code;

# 학과별 등록된 교수 수를 조회하는 쿼리
select major.*, count(pr_num) from major join professor on mj_code = pr_mj_code 
group by mj_code;

# 2025년도에 컴퓨터공학에 임용한 교수 수를 조회
select count(*) from professor where pr_num like concat('_', 2025, 160, '%');

# 교수 등록
# 이름 : 홍교수, 연락처 : 111-1234-5678, 주민번호 : 700101-5111111,
# 임용연도 : 2025, 전공 : 컴퓨터공학과 (160)
insert into professor(pr_num, pr_name, pr_contact, pr_resident, pr_year, pr_mj_code)
values("P2025160001","홍교수", "111-1234-5678", "700101-5111111", 2025, 160);

insert into professor(pr_num, pr_name, pr_contact, pr_resident, pr_year, pr_mj_code)
select concat("P", 2025, 160, LPAD(count(*)+1, 3, '0')), "홍교수", "111-1234-5678", "700101-5111111", 2025, 160
from professor where pr_year = 2025 and pr_mj_code = 160;

# 교수 등록
# 이름 : 김교수, 연락처 : 111-1111-1111, 주민번호 : 710101-5222222,
# 임용연도 : 2025, 전공 : 컴퓨터공학과(160)
insert into professor(pr_num, pr_name, pr_contact, pr_resident, pr_year, pr_mj_code)
select concat("P", 2025, 160, LPAD(count(*)+1, 3, '0')), "김교수", "111-1111-1111", "710101-5222222",
2025, 160
from professor where pr_year = 2025 and pr_mj_code = 160;

# 교수 등록
# 이름 : 박교수, 연락처 : 111-9999-9999, 주민번호 : 740505-6222222,
# 임용연도 : 2025, 전공 : 디자인과 (123)
insert into professor(pr_num, pr_name, pr_contact, pr_resident, pr_year, pr_mj_code)
select concat("P", 2025, 123, LPAD(count(*)+1, 3, '0')), "박교수", "111-9999-9999", "740505-6222222",
2025, 123
from professor where pr_year = 2025 and pr_mj_code = 123;

# 교수 등록
# 이름 : 이교수, 연락처 : 111-5555-5555, 주민번호 : 740505-6333333,
# 임용연도 : 2025, 전공 : 디자인과 (123)
insert into professor(pr_num, pr_name, pr_contact, pr_resident, pr_year, pr_mj_code)
select concat("P", 2025, 123, LPAD(count(*)+1, 3, '0')), "이교수", "111-5555-5555", "740505-6333333",
2025, 123
from professor where pr_year = 2025 and pr_mj_code = 123;

# 교수 등록
# 이름 : 최교수, 연락처 : 111-1234-5555, 주민번호 : 840505-5444444,
# 임용연도 : 2025, 전공 : 기계공학과 (456)
insert into professor(pr_num, pr_name, pr_contact, pr_resident, pr_year, pr_mj_code)
select concat("P", 2025, 456, LPAD(count(*)+1, 3, '0')), "최교수", "111-1234-5555", "840505-5444444",
2025, 456
from professor where pr_year = 2025 and pr_mj_code = 456;

# 교수 등록
# 이름 : 장교수, 연락처 : 112-1234-5555, 주민번호 : 841005-6666666,
# 임용연도 : 2025, 전공 : 기계공학과 (456)
insert into professor(pr_num, pr_name, pr_contact, pr_resident, pr_year, pr_mj_code)
select concat("P", 2025, 456, LPAD(count(*)+1, 3, '0')), "장교수", "112-1234-5555", "841005-6666666",
2025, 456
from professor where pr_year = 2025 and pr_mj_code = 456;

commit;

select * FROM professor;


