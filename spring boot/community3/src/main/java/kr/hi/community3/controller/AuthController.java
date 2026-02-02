package kr.hi.community3.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hi.community3.model.dto.LoginDTO;
import kr.hi.community3.security.jwt.JwtTokenProvider;
import kr.hi.community3.service.UserService;
import kr.hi.community3.util.CustomUser;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
	
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
	
    @PostMapping("/signup")
	public ResponseEntity<Boolean> signup(@RequestBody LoginDTO user){
		boolean res = userService.signup(user);
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) {
    	
		//사용자의 아이디, 비번을 시큐리티에 넘겨서 유효한 사용자인지 확인
		//=>MemberDetailService에서 확인을 진행
        Authentication authentication = authenticationManager.authenticate(
            //인증이 안된 사용자가 인증을 요청
    		new UsernamePasswordAuthenticationToken(
                request.id(), request.pw()
            )
        );
        //위에서 올바른 정보이면 CustomUser객체를 반환하고, 아니면 null을 반환
        CustomUser user = (CustomUser) authentication.getPrincipal();
        //토큰 생성
        String token = jwtTokenProvider.createToken(user);
        //생성한 토큰을 화면에 전달. accessToken으로 전송
        return ResponseEntity.ok(Map.of("accessToken", token));
    }
	
	@GetMapping("/test")
	public ResponseEntity<Boolean> test(
			@AuthenticationPrincipal CustomUser user) {
		return ResponseEntity.ok(true);
	}
	@GetMapping("/me")
	public ResponseEntity<Map<String, Object>> me(
			@AuthenticationPrincipal CustomUser user) {
		
		return ResponseEntity.ok(Map.of(
				"id", user.getUser().getMe_id(),
				"email", user.getUser().getMe_email(),
				"role", user.getUser().getMe_role()));
	}
}