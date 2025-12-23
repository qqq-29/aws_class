drop database if exists mall;
# 데이터베이스 생성
create database if not exists mall;
use mall;
# 계정
-- CREATE USER 'mall_admin'@'%' IDENTIFIED BY '1234';
# 계정 권한
-- GRANT ALL PRIVILEGES ON WORLD.* TO 'mall_admin'@'%';
#-----------------------------------------------------

DROP TABLE IF EXISTS `member`;
# 회원(member) 테이블
CREATE TABLE `member` (
	`m_id` int PRIMARY KEY,
	`m_name` varchar(20) NOT NULL,
	`m_point` int default 0
);

DROP TABLE IF EXISTS `product`;
# 상품(product) 테이블
CREATE TABLE `product` (
	`p_id` int PRIMARY KEY,
	`p_name` varchar(50),
	`p_price` int,
    `p_stock` int
);
#-----------------------------------------------------
DROP TABLE IF EXISTS `orders`;
# 주문(orders) 테이블
CREATE TABLE `orders` (
	`o_id` int PRIMARY KEY,
	`m_id` int,
    `p_id` int,
	`o_qty` int,
    `o_date` DATETIME
);
# 외래키m_id
ALTER TABLE `orders` ADD CONSTRAINT `FK_member_TO_orders_1` FOREIGN KEY (
	`m_id`
)
REFERENCES `member` (
	`m_id`
);
# 외래키p_id
ALTER TABLE `orders` ADD CONSTRAINT `FK_product_TO_orders_1` FOREIGN KEY (
	`p_id`
)
REFERENCES `product` (
	`p_id`
);
#-----------------------------------------------------
# 회원
insert into member (m_id, m_name, m_point)
	values
    (1, "홍길동", 1000),(2, "김철수", 500);
# 상품
insert into product (p_id, p_name, p_price, p_stock)
	values
    (101, "노트북", 1500000, 10),
    (102, "마우스", 30000, 50);
# 4-2
select * from member;
select * from product;
#-----------------------------------------------------
# 1번 회원이 101번 상품을 2개 주문함
# product 테이블: 101번 상품 재고 2개 감소
update product set p_stock = p_stock - 2 where p_id = 101;
# member 테이블: 1번 회원 포인트 100점 증가
update member set m_point = m_point + 100 where m_id = 1;
# orders 테이블: 주문 내역 추가 (1번 회원, 101번 상품, 수량 2, 오늘 날짜)
insert into orders (o_id, m_id, p_id, o_qty, o_date)
	values
    (1, 1, 101, 2, now());
# 1번 회원이 주문한 상품명을 조회(JOIN 이용)
select m_name 회원, p_name 주문상품명 from member
	join orders 
    on member.m_id = orders.m_id
    join product
    on orders.p_id = product.p_id;
#-----------------------------------------------------
# 데이터베이스 보안과 관리
drop view v_member_public;
create view v_member_public AS
select m_id, m_name from member;

select * from v_member_public;


