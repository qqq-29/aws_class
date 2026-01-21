package kr.hi.community.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.hi.community.model.vo.BoardVO;
import kr.hi.community.service.PostService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	PostService postService;

	@GetMapping("/board/list")//실제 URL : /admin/board/list
	public String baordList(Model model) {
		//서비스에게 게시판 목록을 가져오라고 요청
		ArrayList<BoardVO> list = postService.getBoardList();
		//화면에 게시판 목록을 전송
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@PostMapping("/board/insert")
	public String boardInsert(
		@RequestParam("board") String name) {
		
		//서비스에게 name을 주면서 게시판을 등록하라고 요청 
		postService.insertBoard(name);
		return "redirect:/admin/board/list";
	}
	@PostMapping("/board/delete/{num}")
	public String boardDelete(
		@PathVariable("num")int num) {
		postService.deleteBoard(num);
		return "redirect:/admin/board/list";
	}
	
	@PostMapping("/board/update/{num}")
	public String boardUpdate(
		@PathVariable("num")int num,
		//화면에서 보낸 게시판명을 가져옴
		@RequestParam("name")String name) {
		//게시판번호, 이름을 이용하여 서비스에게 게시판명을 수정하라고 요청
		postService.updateBoard(num, name);
		return "redirect:/admin/board/list";
	}
	
	
	
	
	
	
	
}
