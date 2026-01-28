package kr.hi.post.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import kr.hi.post.model.vo.PostVO;



public interface PostDAO {

	ArrayList<PostVO> selectPosts();

	PostVO selectPost(@Param("num") int num);

	boolean InsertPost(@Param("postVO") PostVO postVO);

	boolean postDelete(@Param("num") int num);

}
