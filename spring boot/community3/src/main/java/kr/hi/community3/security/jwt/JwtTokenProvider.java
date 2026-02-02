package kr.hi.community3.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import kr.hi.community3.util.CustomUser;


@Component
public class JwtTokenProvider {

    private final Key key;
    private final long validityInMilliseconds;


    public JwtTokenProvider(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.validityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    public String createToken(CustomUser user) {
    	//현재 시간을 초로 환산
    	long now = (new Date()).getTime();
    	//현재 시간에 만료 기간을 더한 날짜를 계산
        Date validity = new Date(now + this.validityInMilliseconds);
        
        return Jwts.builder()
                .setSubject(user.getUsername())
                //claim을 통해 토큰에 넣고 싶은 정보를 추가
                .claim("email", user.getUser().getMe_email())
                .claim("role", user.getAuthorities().iterator().next().getAuthority())
                //시작일
                .setIssuedAt(new Date())
                //만료일
                .setExpiration(validity)
                //서명
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //토큰에서 claim 정보들을 가져옴
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

