package kr.hi.community.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.PostVO;

public interface PostDAO {

	ArrayList<PostVO> selectPostList();

	PostVO getPost(@Param("num")int po_num);

	void viewPlus(@Param("num")int num);

	BoardVO getboard();

	ArrayList<BoardVO> getBoardList();


}
