package kr.hi.boot.dao;

import java.util.ArrayList;

import kr.hi.boot.model.vo.Board;

/* 
 * DAO의 특징
	구현 클래스가 없다
	SQL도 없다
	메서드 선언만 있음
	
👉 그럼 누가 구현하냐?
✔ MyBatis가 런타임에 자동으로 구현
 * */

public interface PostDAO {

	/*
	 * 	1.
		XML의 id = DAO 메서드명
			(이름이 다르면 실행 안 됨 (에러 발생))
	
		2.
		resultType = 반환 타입
			SQL 결과 한 행 → Board
			여러 행 → ArrayList<Board>

	 */
	ArrayList<Board> getBoardList();

}
