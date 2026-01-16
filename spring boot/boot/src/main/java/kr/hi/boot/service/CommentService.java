package kr.hi.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.hi.boot.dao.CommentDAO;
import kr.hi.boot.model.dto.CommentResponseDTO;
import kr.hi.boot.model.util.Criteria;
import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.model.util.PageMaker;
import kr.hi.boot.model.vo.Comment;

@Service
public class CommentService {

private final CommentDAO commentDAO;
	
	public CommentService(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public List<Comment> getcomments(int poNum, Criteria cri) {
		//다오에게 게시글 번호와 페이지 정보를 주면서 댓글 목록을 가져오라고 요청
		//게시글 목록=다오야.댓글목록가져와(게시글번호);
		List<Comment> list = commentDAO.selectComments(poNum, cri);
		
		//가져온 댓글 목록을 반환
		return list;
	}

	public PageMaker getPageMaker(int poNum, Criteria cri) {
		//한페이지네이션에서 최대 페이지수를 3개로 선언
		int pageCount = 3;
		//다오에게 게시글번호를 주면서 전체 댓글 수를 가져오라고 요청
		//전체댓글수 = 디오야.전체댓글수가져와(게시글번호, 현재페이지);
		int count = commentDAO.selectCommentsCount(poNum);
		//최대 페이지수, 전체게시글수, 현재페이지 정보를 이용하여 PageMaker 객체를 생성
		PageMaker pm = new PageMaker(pageCount, cri, count);
		//생성된 객체를 반환
		return pm;
	}

	public String insertComment(Comment comment, CustomUser user) {
		//- 사용자가 로그인 안했으면 "로그인이 필요한 서비스입니다."라고 반환
		if(user == null) {
			return "로그인이 필요한 서비스입니다.";
		}
		//댓글 내용이 비었으면 "댓글을 입력하세요."라고 반환
		if(comment.getContent() == null ||
				comment.getContent().isBlank()) {
			return "댓글을 입력하세요.";
		}
		
		//- 다오에게 게시글 번호, 댓글 내용, 사용자아이디를 주면서 게시글을 등록하라고 요청후 등록 여부를 알려달라고 요청
		comment.setId(user.getUsername());
		//등록결과 = 다오야.게시글등록해(댓글정보);
		boolean result = commentDAO.insertComment(comment);
		//- 등록했으면 "댓글을 등록했습니다."라고 반환하고
		if(result) {
			return "댓글을 등록했습니다.";
		}
		//- 못했으면 댓글을 등록하지 못했습니다라고 반환
		return null;
		
	}

	public String deleteComment(int coNum, CustomUser user) {
		//- 사용자가 로그인되어 있지 않으면 "로그인이 필요한 서비스입니다" 반환
		if(user == null) {
			return "로그인이 필요한 서비스입니다.";
		}
		//  - 댓글작성자와 사용자아이디가 다르면 "작성자가 아닙니다"를 반환
		String id = user.getUsername();
		if(!isWriter(coNum, id)) {
			return "작성자가 아닙니다";
		}
		
		//- 다오에게 댓글 번호를 주면서 삭제하고 결과를 알려달라고 요청
		boolean result = commentDAO.deleteComment(coNum);
		//- 삭제했으면 "댓글을 삭제했습니다"를 반환
		if(result) {
			return "댓글을 삭제했습니다.";
		}
		//- 못했으면 "댓글을 삭제하지 못했습니다"를 반환
		
		return "댓글을 삭제하지 못했습니다";
	}
	private boolean isWriter(int coNum, String id) {
		//  - 다오에게 댓글번호 주면서 댓글 정보 가져오라고 요청
		Comment comment = commentDAO.selectComment(coNum);
		
		//- 가져온 댓글 정보가 없거나 댓글작성자와 사용아이디가 다르면
		//false를 반환
		if(comment == null || !comment.getId().equals(id)) {
			return false;
		}
		//아니면 true를 반환
		return true;
	}



}
