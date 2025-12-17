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