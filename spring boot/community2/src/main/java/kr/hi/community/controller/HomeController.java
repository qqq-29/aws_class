package kr.hi.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.hi.community.model.dto.MemberDTO;
import kr.hi.community.service.MemberService;

@Controller
public class HomeController {
	//객체를 생성해서 연결. 단, 빈(bean)에 등록된 클래스/인터페이스만
	//@Bean, @Service, @Repository 등
	@Autowired 
	MemberService memberService;
	
	/* Get : URL을 직접 입력하거나, 링크를 클릭해서 이동했을 때 대부분 Get.
	 * Post: 값을 입력하여 중요한 정보를 전송할 때(form태그 + method=post)
	 * */
	@GetMapping("/")
	public String home() {
		//여러 작업을 하는 코드가 있다고 가정
		return "index";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signupPost(/* 화면이 보낸 회원 정보를 받아옴 */
		MemberDTO member) {
		
		boolean result = memberService.signup(member);
		//회원가입 성공하면 메인 페이지로
		if(result) {
			/* - redirect는 PostMapping일 때 주로 사용.
			 * - url을 지정된 URL로 바꿔줌.(여기에선 메인페이지(/)) 
			 * */
			return "redirect:/";
		}
		//실패하면
		else {
			return "redirect:/signup";
		}
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
