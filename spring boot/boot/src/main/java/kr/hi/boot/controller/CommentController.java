package kr.hi.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.boot.model.dto.CommentResponseDTO;
import kr.hi.boot.model.util.Criteria;
import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.model.util.PageMaker;
import kr.hi.boot.model.vo.Comment;
import kr.hi.boot.service.CommentService;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

	private final CommentService commentService;
	
	//생성자를 이용한 의존성 주입
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping("/posts/{num}/comments")
	public ResponseEntity<CommentResponseDTO> getComments(
			//화면에서 보낸 게시글 번호를 가져옴
	        @PathVariable("num") int poNum,
	        //화면에서 보낸 페이지 정보를 가져옴
	        Criteria cri) {
		
		//한페이에 댓글 5개로 설정
		cri.setPerPageNum(5);
		//서비스에게 게시글 번호와 페이지 정보를 주면서 댓글 목록 가져오라고 요청
		//댓글목록 = 서비스야.댓글목록가져와(게시글번호);
		List<Comment> list =commentService.getcomments(poNum, cri);
		
		//서비스에게 게시글 번호와 페이지 정보를 주면서 PageMaker 객체를 가져오라고 요청
		//PageMaker객체 = 서비스야.PageMaker객체가져와(게시글번호, 페이지정보)
		PageMaker pm = commentService.getPageMaker(poNum, cri);
		
		//가져온 댓글 목록과 페이지네이션정보를 화면에 전달
		CommentResponseDTO dto = new CommentResponseDTO(list, pm);
	    return ResponseEntity.ok(dto);
	}
	
	@PostMapping("/posts/{num}/comments")
	public ResponseEntity<String> getCommentsPost(
			//- 화면에서 보낸 게시글 번호를 가져옴
				@PathVariable("num")int postNum,
				//- 화면에서 보낸 댓글 내용을 가져옴
				@RequestBody Comment comment,
				//- 로그인한 사용자 정보 가져옴
				@AuthenticationPrincipal CustomUser user
				) {
		
		//- 서비스에게 게시글번호와 댓글내용, 사용자정보를 주면서 댓글을 등록하고 결과를 문자열로 가져오라고 요청
		comment.setPostNum(postNum);
		
		//결과 = 서비스야.댓글등록해(게시글번호와댓글내용, 사용자정보);
		String result = commentService.insertComment(comment, user);
		//- 가져온 결과를 화면에 전송
		return ResponseEntity.ok(result); 
	}
	@DeleteMapping("/posts/{postNum}/comments/{comments}")
	public ResponseEntity<String> postsCommentsDelete(
			//- 화면에서 보낸 댓글 번호를 가져옴
				@PathVariable("comments")int coNum,
			//- 로그인한 사용자 정보를 가져옴
				@AuthenticationPrincipal CustomUser user
			){
		//- 서비스에게 댓글 번호와 로그인한 사용자 정보를 주면서 댓글을 삭제하고 결과를 알려달라고 요청
		String result = commentService.deleteComment(coNum, user);
		//- 결과를 화면에 전달
		return ResponseEntity.ok(result); 
	}

}
