# 이벤트 스케쥴러
# - 특정 작업이 정기적으로 실행되도록 예약하는 기능
# - 특정 작업이 일정 시간 후 한번만 실행되도록 예약하는 기능
# - 예
#	- 카카오 페이지에서 24:00가 되면 기간이 지난 무료 캐시를 제거 => 이벤트 스케쥴러

# 이벤트 스케쥴러 상태 확인
# value가 on이면 스케쥴러를 사용, off이면 스케쥴러 사용 안함
show variables like 'event%';

# 이벤트 스케쥴러 상태를 변경
# 값 : on | off
# set global event_scheduler = 값;

# 이벤트 스케쥴러 확인
select * from information_schema.events;

# 이벤트 스케쥴러 등록(정의)
/*
delimiter 기호
create event 이벤트명
ON schedule evert 숫자 단위
[start 시간]
[on completion preserve | on completion not preserve]
[comment '설명']
do
begin
	실행한 쿼리; #복잡한 쿼리면 프로시저를 만들어서 생성
end 기호
delimiter ;

- 단위
	- YEAR | QUARTER | MONTH | DAY | HOUR | MINUTE | WEEK | SECOND|
    - YEAR_MONTH, MONTH_HOUR, DAY_MINUTE, DAY_SECOND, HOUR_MINUTE,
	  HOUR_SECOND, MINUTE_SECOND
- STARTS 시간
	- 스케쥴러가 실행될 기준 시간. 생략되면 등록 시간이 기준 시간
- ON COMPLETION PRESERVE
	- 이벤트 스케쥴로 작업이 완료된 후 이벤트를 보존(유지)
- ON COMPLETION NOT PRESERVE
	- 이벤트 스케쥴로 작업이 완료된 후 이벤트를 보존하지 않고 삭제(한번만)
    - 기본값
*/

# 스케쥴러 삭제
# DROP EVENT IF EXISTS 이벤트명;
DROP EVENT IF EXISTS EVENT_BUY;

DELIMITER $$
CREATE EVENT EVENT_BUY
#ON schedule EVERY 2 minute
ON schedule
AT addtime(NOW(), "00:02:00")
ON completion NOT preserve
DO
begin
	declare EXIT handler for sqlexception
    begin
		rollback;
	END;
    
    start transaction;
    
	INSERT INTO BUY(ADDRESS, AMOUNT, CODE, PRICE, ID)
	VALUES("부천", 1, "ACC005", 150000, "abc123");
    COMMIT;
END $$
DELIMITER ;

# ACC001 제품의 수량을 1분마다 1씩 증가시키는 스케쥴러 등록
drop EVENT IF exists AMOUNT_PLUS;

DELIMITER $$
create EVENT AMOUNT_PLUS
ON schedule EVERY 1 minute
ON completion NOT preserve
DO
BEGIN
declare EXIT handler for sqlexception
    begin
		rollback;
	END;
    
    start transaction;
    update product SET AMOUNT = AMOUNT + 1 WHERE CODE = "ACC001";
    commit;
END $$
DELIMITER ;




select * FROM INFORMATION_SCHEMA.EVENTS;
