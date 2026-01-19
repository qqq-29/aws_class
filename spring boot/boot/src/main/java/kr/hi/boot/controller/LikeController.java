package kr.hi.boot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.service.LikeService;

@RestController
public class LikeController {
	
	private final LikeService likeService;
	
	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	/* 메서드 추가
	 * url : /api/v2/posts/게시글번호/like
	 * method : post
	 * 화면에 문자열 전달
	 */
	@PostMapping("/api/v2/posts/{postNum}/like")
	public ResponseEntity<String> likePost(
				//게시글 번호 가져옴
				@PathVariable("postNum") int postNum,
				//로그인한 사용자 정보 가져옴
				@AuthenticationPrincipal CustomUser user
			){
		//서비스에게 게시글 번호, 사용자 정보를 주면서 게시글 추천하라고 요청하고
		//결과를 가져옴
		String result = likeService.like(postNum, user);
		//가져온 결과를 화면에 전달
		return ResponseEntity.ok(result);
	}

}
