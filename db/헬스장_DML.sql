# 센터를 운영할 트레이너 등록
# 팀장
# - 김철수, 전공 : 보디빌딩, 경력 10년
insert into trainer (name, specialty, experience)
	values ("김철수", "보디빌딩", 10);
# 신입 트레이너
# - 이영희, 전공 : 요가, 경력 2년
# - 홍길동, 전공 : 보디빌딩, 경력 3년
insert into trainer (name, specialty, experience)
	values ("이영희", "요가", 2);
insert into trainer (name, specialty, experience)
	values ("홍길동", "보디빌딩", 3);
# 신입 트레이너의 팀장 배정
# - 이영희 : 김철수
# - 홍길동 : 김철수
update trainer set MANAGER_ID = 1 
	where trainer_id in(2,3);
    
# 사물함 등록
# - 사물함은 2군데 : A구역, B구역
# 	- 사물함 별 번호, A구역은 1~5번, B구역은 1~15번
# - 사물함 이름 : 구역명-번호
# 	- A구역-01
insert into locker(location)values
("A구역-01"),("A구역-02"),("A구역-03"),("A구역-04"),("A구역-05"),
("B구역-01"),("B구역-02"),("B구역-03"),("B구역-04"),("B구역-05"),
("B구역-06"),("B구역-07"),("B구역-08"),("B구역-09"),("B구역-10"),
("B구역-11"),("B구역-12"),("B구역-13"),("B구역-14"),("B구역-15");

# 이순신 회원이 헬스장에 가입, 관리자는 A구역 1번 사물함을 배정
# 연락처 : 020-1111-1111, 가입일은 현재시간
# 기본값 CURRENT_TIMESTAMP
# - INSERT 될 때 값을 넣지 않으면 기본값으로 현재 시간의 년,월,일,시,분초가 자동으로 추가
# - 단, DATETIME에서만 가능
insert into member(name, contact, locker_id)
	values("이순신", "020-1111-1111", 1);

# 성춘향 회원이 헬스장에 가입, 관리자는 B구역 1번 사물함을 배정
# 연락처 : 020-2222-2222, 가입일은 현재시간
insert into member(name, contact, join_date, locker_id)
select "성춘향", "020-2222-2222", now(), locker_id
	from locker
    where LOCATION = concat('B구역', '-', LPAD(1, 2, '0'));  

# 강좌 개설
# 강좌1
# - 강좌명 : 모닝 요가, 정원 20명, 수강료 100,000원, 담당 : 이영희
insert INTO class(NAME, CAPACITY, FEE, TRAINER_ID)
	values("모닝 요가", 20, 100000, 2);
# 강좌1
# - 강좌명 : 지옥의 크로스핏, 정원 15명, 수강료 150,000원, 담당 : 홍길동
insert INTO class(NAME, CAPACITY, FEE, TRAINER_ID)
	values("지옥의 크로스핏", 15, 150000, 3);

# 수강 신청
# - 이순신(1) 회원이 모닝 요가(1)와 지옥의 크로스핏(2)을 수강 신청 후 결재 완료 함
# - 성춘향(2) 회원이 모닝 요가(1)를 수강 신청함
# - 결재완료하면 '결재', 수강신청만 하면 '신청'으로 관리
insert into enrollment (payment_state, MEMBER_ID, CLASS_ID)
	values('결재', 1, 1),('결재', 1, 2),('신청', 2, 1);

# 이순신(1) 회원이 지옥의 크로스핏 강좌(2) 를 출석체크 함(현재시간)
insert into attendance (MEMBER_ID, CLASS_ID)
	values(1,2);


# 새로운 강좌를 등록
# - 강좌명 : 크로스핏 초급반, 정원 : 30, 수강료 : 50,000
# - 담당 : 김철수(1)
insert into class(name, capacity, fee, trainer_id)
	values('크로스핏 초급반', 30, 50000, 1);


