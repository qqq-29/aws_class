package kr.hi.community.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hi.community.dao.PostDAO;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.PostVO;

@Service
public class PostService {

	@Autowired
	PostDAO postDAO;
	
	public ArrayList<PostVO> getPostList(){
		//다오에게 게시글 목록을 가져오라고 요청
		ArrayList<PostVO> list = postDAO.selectPostList();
		
		return list;
	}

	public PostVO getPost(int num) {
		PostVO post = postDAO.getPost(num);
		return post;
	}

	public void viewPlus(int num) {
		postDAO.viewPlus(num);
		
		
	}

	public ArrayList<BoardVO> getBoardList() {
		//다오에게 게시판 목록을 가져오라고 요청
		ArrayList<BoardVO> boardlist = postDAO.getBoardList();
		return boardlist;
	}
	
}
