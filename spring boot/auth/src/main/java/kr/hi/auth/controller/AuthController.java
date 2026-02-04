package kr.hi.auth.controller;

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

import kr.hi.auth.domain.dto.UserDTO;
import kr.hi.auth.domain.vo.UserVO;
import kr.hi.auth.security.jwt.JwtTokenProvider;
import kr.hi.auth.service.UserService;
import kr.hi.auth.util.CustomUser;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

	private final UserService userService;
	private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
	
	@PostMapping("/signup")
	public ResponseEntity<Boolean> postSignup(@RequestBody UserDTO userDTO){
		boolean sign = userService.postSignup(userDTO);
		
		return ResponseEntity.ok(sign);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> postLogin(@RequestBody UserDTO userDTO){
		Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	            		userDTO.id(), userDTO.pw()
	            )
	        );
	        CustomUser customUser = (CustomUser) authentication.getPrincipal();
	        String accessToken = jwtTokenProvider.createAccessToken(customUser);
		
		return ResponseEntity.ok(Map.of("accessToken",accessToken));
	}
	
	@GetMapping("/me")
	public ResponseEntity<Map<String, Object>> me(@AuthenticationPrincipal CustomUser customUser){

		UserVO user = customUser.getUser();
		
		return ResponseEntity.ok(Map.of(
				"id" , user.getMe_id(),
				"email", user.getMe_email(),
				"role", user.getMe_role()
				));
	}
}
