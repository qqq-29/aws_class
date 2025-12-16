# 분류가 악세서리인 제품을 조회
select * from product join category on product.num = category.num  where category.title = "악세서리";
select * from product join category using(num) where category.title = "악세서리";

# 제품명에 18K가 포함된 제품을 조회
select * from product where title like "%18K%";

# abc123회원의 장바구니 목록 조회
#select * from 테이블1 join 테이블2 on 테이블1.컬럼1 = 테이블2.컬럼2;
select * from cart join product on cart.code = product.code
where id = "abc123";

# abc123회원이 구매한 구매 내역 조회
select * from buy join product on buy.num = product.num
where id = "abc123";

# abc123회원의 누적 금액을 조회
select id, sum(price) 누적금액 from buy 
where id = "abc123";

# 회원별 누적 금액을 조회
select id, ifnull(sum(price),0) 누적금액 from buy 
right join user using(id)
group by id;