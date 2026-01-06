package kr.hi.community.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.PostVO;

public interface PostDAO {

	ArrayList<PostVO> selectPostList();

	PostVO getPost(@Param("num")int po_num);

	void viewPlus(@Param("num")int num);

	BoardVO getboard(@Param("num")int board);

	ArrayList<BoardVO> getBoardList();

	void insertPost(@Param("post")PostDTO postDTO);

	void insertBoard(@Param("name")String name);
//@Param는 xml에 씁

	void deleteBoard(@Param("deletenum")int num);

	void updateBoard(@Param("updatename")String name, @Param("updatenum")int num);
}
