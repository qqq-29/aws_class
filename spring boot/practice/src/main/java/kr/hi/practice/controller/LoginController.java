package kr.hi.practice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.practice.domain.UserDTO;
import kr.hi.practice.service.JwtService;
import kr.hi.practice.service.UserService;

@RestController
//중요! React(3000번 포트)에서 오는 요청을 허용해주는 설정입니다.
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;
	//Map해야 React는 이게 토큰인지, 에러 메시지인지구분할수있어요.
	//이렇게 Map에 데이터를 담아서 @RestController에서 return하면, 
	//Spring Boot가 똑똑하게도 이 Map을 보고 **"아! 이걸 JSON으로 바꿔서 보내달라는 뜻이구나!"
	//**라고 알아차리고 아래처럼 변환해줍니다.
	//Java의 Map: { "token" = "abc123" }
	//변환된 JSON: { "token" : "abc123" }
	
	//그리고 로그인할 때 우리가 Map<String, String> loginData를 사용한 이유도 React가 보내준
	//JSON({"username": "test", "password": "..."})을 자바가 읽기 편하게 이름표(Key)를 붙여서 가져오기 위해서예요.

	//그래서 map는 JSON 형식으로 데이터를 주고받는 게 웹의 규칙입니다.(자바에서 JSON과 가장 비슷하게 생긴 게 **Map**입니다.)
	@PostMapping("/api/login")
	public Map<String, String> login(@RequestBody Map<String, String> loginData){
		String username = loginData.get("username");
		String password = loginData.get("password");
		
		Map<String, String> user = userService.authenticate(username, password);
		
		if(user != null) {
			// 성공하면 아까 만든 서비스로 토큰 생성!
			String token = jwtService.createToken(username);
			
			Map<String, String> response = new HashMap<>();
			response.put("token", token);
			return response;
		}else {
			throw new RuntimeException("아이디나 비밀번호가 틀렸어요.");
		}
	}
	
	@GetMapping("/api/user/info")
	public Map<String, String> getUserInfo(@RequestHeader("Authorization") String authHeader) {
	    // 1. 헤더에서 토큰 추출 (보통 "Bearer 토큰문자열" 형태로 옵니다)
	    String token = authHeader.replace("Bearer ", "");
	    
	    // 2. 토큰을 해석해서 이름 가져오기
	    String username = jwtService.getUsernameFromToken(token);
	    
	    // 3. 응답 보내기
	    Map<String, String> response = new HashMap<>();
	    response.put("username", username);
	    response.put("message", "당신은 인증된 사용자입니다!");
	    return response;
	}
	
	@PostMapping("/api/signup")
	public Map<String, String> signup(@RequestBody UserDTO userDTO){
		// 1. 서비스 호출 (암호화 및 DB 저장이 일어남)
	    userService.register(userDTO);
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "회원가입이 완료되었습니다!");
		return response;
		
	}

}
