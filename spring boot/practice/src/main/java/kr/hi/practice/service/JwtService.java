package kr.hi.practice.service;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String createToken(String username) {
		long now = System.currentTimeMillis();
		return Jwts.builder()				// 1. "자, 지금부터 토큰을 조립할 거야!"
				.setHeaderParam("typ", "JWT") // 2. "이 물건의 종류(Type)는 JWT라고 써놔."
			    .setSubject(username)        // 3. "이 토큰은 '홍길동'꺼야." (내용물 주체)
			    .setIssuedAt(new Date(now))  // 4. "만든 날짜는 지금이야."
			    .setExpiration(new Date(now + 3600000)) // 5. "근데 1시간 뒤엔 못 쓰게 유효기간을 걸어줘."
			    .signWith(key)               // 6. "마지막으로 아까 만든 비밀 열쇠로 도장 꽝 찍어!"
			    .compact();                  // 7. "다 됐으면 긴 문자열 하나로 꽉 압축해!"
	}
	public String getUsernameFromToken(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(key) // 우리가 아까 만든 그 열쇠!
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject(); // 토큰에 담긴 이름(username) 반환
	}

}
