package kr.hi.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.hi.boot.dao.CommentDAO;
import kr.hi.boot.model.vo.Comment;

@Service
public class CommentService {

private final CommentDAO commentDAO;
	
	public CommentService(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public List<Comment> getcomments(int poNum) {
		//다오에게 게시글 번호를 주면서 댓글 목록을 가져오라고 요청
		//게시글 목록=다오야.댓글목록가져와(게시글번호);
		List<Comment> list = commentDAO.selectComments(poNum);
		
		//가져온 댓글 목록을 반환
		return list;
	}



}
