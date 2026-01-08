package kr.hi.community.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.util.Criteria;
import kr.hi.community.model.util.CustomUser;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.FileVO;
import kr.hi.community.model.vo.PostVO;
import lombok.NonNull;

public interface PostDAO {

	ArrayList<PostVO> selectPostList(@Param("cri")Criteria cri);

	PostVO getPost(@Param("num")int po_num);

	void viewPlus(@Param("num")int num);

	BoardVO getboard(@Param("num")int board);

	ArrayList<BoardVO> getBoardList();

	void insertPost(@Param("post")PostDTO postDTO);

	void insertBoard(@Param("name")String name);
//@Param는 xml에 씁

	void deleteBoard(@Param("deletenum")int num);

	void updateBoard(@Param("updatename")String name, @Param("updatenum")int num);

	int selectTotalCount(@Param("cri")Criteria cri);

	int deletePost(@Param("num")int num);

	void postUpdatePost(@Param("post")PostDTO postDTO, @Param("num")int postNum);
//디른방법
	void updatePost(@Param("post")PostDTO postDTO);

	void insertFile(@Param("file")FileVO fileVo);

	List<FileVO> selectFileList(@Param("num")int postNum);

	void deleteFile(@Param("fiNum") int fi_num);

	
}
