package kr.hi.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.hi.boot.model.dto.PostDTO;
import kr.hi.boot.model.util.CustomUser;
import kr.hi.boot.model.util.PageMaker;
import kr.hi.boot.model.util.PostCriteria;
import kr.hi.boot.model.vo.Board;
import kr.hi.boot.model.vo.Post;
import kr.hi.boot.service.PostService;

@Controller
public class PostController {

	@Autowired
	PostService postService;
	
	@GetMapping("/post/list")
	public String postList(Model model, PostCriteria cri) {
		//한페이지에 게시글 10개
		cri.setPerPageNum(3);
		//서비스에게 현재 페이지 정보를 주면서 게시글 목록을 가져오려고 함
		//게시글 목록 = 서비스야.게시글목록가져와(현재페이지정보);
		ArrayList<Post> list = postService.getPostList(cri);
		
		//서비스에게 현재 페이지 정보를 주면서 PageMaker 객체를 가져오라고 요청
		//PageMaker객체 = 서비스야.PageMaker객체가져와(현재페이지정보);
		PageMaker pm = postService.getPageMaker(cri);
		
		//- 서비스에게 게시판 목록 전체를 가져오라고 요청
		//게시판목록 = 서비스야.게시판목록가져와()
	    List<Board> boardList = postService.getBoardList();
		
		//가져온 게시글 목록을 화면에 전달
		model.addAttribute("list", list);
		model.addAttribute("pm", pm);
		model.addAttribute("bList", boardList);
	    return "post/list";
	}
	
	@GetMapping("/post/detail/{num}")
	public String postDetail(
			Model model,
		@PathVariable("num")int num){
		//조회수 증가
		postService.updatePostView(num);
		//번호에 맞는 게시글을 가져와서 화면에 전달
		//컨트롤러, 서비스, 다오, 매퍼
		Post post = postService.getPost(num);
		model.addAttribute("post", post);	
		return "post/detail";
	}
	
	@GetMapping("/post/insert")
	public String postInsert(Model model){
		//게시판 목록을 가져옴
		ArrayList<Board> list = postService.getBoardList();
		//화면에 게시판 목록을 전달
		model.addAttribute("list", list);
		return "post/insert";
	}
	
	@PostMapping("/post/insert")
	public String postInsertPost(PostDTO post,
			@AuthenticationPrincipal CustomUser cUser) {
		//화면에서 넘겨준, 제목(title), 게시판 번호(board), 내용(content)을 받아옴
		postService.insertPost(post, cUser);
		return "redirect:/post/list";
	}

	@PostMapping("/post/delete/{num}")
	public String postDelete(
//								- url로 넘겨준 게시글 번호를 가져옴
								@PathVariable("num")int poNum,
//								- 로그인한 사용자 정보를 가져옴
								@AuthenticationPrincipal CustomUser customUser) {
//		- 서비스에게 게시글 번호와 사용자 정보를 주면서 삭제하라고 요청
		postService.deletePost(customUser, poNum);
		
		return "redirect:/post/list";
	}
	
	@GetMapping("/post/update/{num}")
	public String postUpdate(
			//- 화면에서 보낸 게시글 번호를 가져옴
			@PathVariable("num")int num,
			Model model) {
		//- 서비스에게 게시글 번호를 주면서 게시글을 가져오라고 요청
		Post post = postService.getPost(num);
		//- 가져온 게시글을 화면에 전달
		//model.addAttribute("화면에쓸이름", 게시글);
		model.addAttribute("post", post);
		
		return "post/update";
	}
	
	@PostMapping("/post/update/{num}")
	public String postUpdate(
			//url에 있는 게시글 번호를 가져옴
			@PathVariable("num")int ponum,
			//로그인한 사용자 정보 가져옴
			@AuthenticationPrincipal CustomUser user,
			//화면에서 보낸 제목과 내용을 가져옴
			// @RequestParam("title")String title,
			// @RequestParam("content")String content) 
			PostDTO dto){
		//서비스에게 게시글번호, 제목, 내용, 작성자 정보를 주면서 수정하라고
		postService.updatePost(ponum,user,dto);
		
		return "redirect:/post/detail/{num}";
	}
}
