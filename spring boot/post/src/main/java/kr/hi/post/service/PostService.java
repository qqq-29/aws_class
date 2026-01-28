package kr.hi.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.hi.post.dao.PostDAO;
import kr.hi.post.model.vo.PostVO;

@Service
public class PostService {

	private final PostDAO postDAO;
	
	public PostService(PostDAO postDAO) {
		this.postDAO = postDAO;
	}

	public ArrayList<PostVO> getPosts() {
		ArrayList<PostVO> list = postDAO.selectPosts();
		return list;
	}

	public PostVO getPost(int num) {
		PostVO list = postDAO.selectPost(num);
		return list;
	}

	public boolean InsertPost(PostVO postVO) {
		if(postVO == null || postVO.getTitle().isBlank() ||postVO.getContent().isBlank() ||postVO.getWriter().isBlank()) {
			return false;
		}
		
		boolean result = postDAO.InsertPost(postVO);
		return result;
	}

	public boolean postDelete(int num) {
		boolean result = postDAO.postDelete(num);
		return result;
	}
	


}
