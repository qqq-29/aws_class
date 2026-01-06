package kr.hi.community.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.hi.community.model.dto.PostDTO;
import kr.hi.community.model.util.CustomUser;
import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.model.vo.PostVO;
import kr.hi.community.service.PostService;


@Controller
public class PostController {
	@Autowired
	PostService postService;
	
	@GetMapping("/post/list")
	public String postlist(Model model) {
		//서비스에게 게시글 목록을 가져오라고 요청
		//가져온 게시글 목록을 list에 저장
		ArrayList<PostVO> list = postService.getPostList();
		
		//list를 게시글 목록을 화면에 전송
		model.addAttribute("list", list);
		return "post/list"; // post폴더에 list.html을 화면으로 보내줌
	}
	//@PathVariable 주소
	@GetMapping("/post/detail/{num}")
	public String postdetail(Model model,
						@PathVariable("num") int po_num) {
		//게시글 번호를 이용해서 조회수 증가
		postService.viewPlus(po_num);
		//게시글 번호를 이용해서 게시글 가져옴
		//게시글 번호(기본키)를 이용하여 게시글을 조회하면? => 최대 1개(왜?)
		//기본키이니까 => 기본키의 정의 => 기본키로 검색하면 최대 1행이 조회되는 컬럼
		PostVO post = postService.getPost(po_num);
		//가져온 게시글을 화면에 전달
		model.addAttribute("post", post);
		return "post/detail";
	}
	
	@GetMapping("/post/insert")
	public String postinsert(Model model) {
		//게시판 목록을 가져옴
		ArrayList<BoardVO> boardlist = postService.getBoardList();
		
		//게시판 목록을 화면에 전송
		model.addAttribute("boardlist", boardlist);
		return "post/insert";
	}
	
	@PostMapping("/post/insert")
	public String postInsertPost(
			//게시글 등록에 필요한 정보를 받아옴
			PostDTO postDTO, //제목, 내용, 게시판 번호
			@AuthenticationPrincipal CustomUser customUser //작성자(로그인한사용자) 정보
			) {
		//게시글 정보와 작성자 정보를 서비스에게 주면서 등록하라고 요청
		boolean result = postService.insertPost(postDTO, customUser);
		//등록에 성공하면 /post/list로 이동, 실패하면 /post/insert로 이동
		if(result) {
			return "redirect:/post/list";
		}
		return "redirect:/post/list";
	}

}
