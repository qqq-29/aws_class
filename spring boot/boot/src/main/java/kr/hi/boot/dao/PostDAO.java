package kr.hi.boot.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.hi.boot.model.dto.PostDTO;
import kr.hi.boot.model.vo.Board;
import kr.hi.boot.model.vo.Post;

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
	
	boolean insertBoard(@Param("name")String name);

	void deleteBoard(@Param("num")int num);
	
	void updateBoard(@Param("num")int num, @Param("name")String name);

	ArrayList<Post> getPostList();

	ArrayList<Board> getBoardList();
	
	Post getPost(@Param("num")int num);
	
	Board getBoard(@Param("num")int board);
	
	void insertPost(@Param("post")PostDTO post);

	void updatePostView(@Param("num")int num);
}
