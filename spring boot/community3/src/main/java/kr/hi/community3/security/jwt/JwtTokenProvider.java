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
    private final long accessTokenValidity;
    private final long refreshTokenValidity;

    public JwtTokenProvider(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.token-validity-in-seconds}") long accessSeconds,
        @Value("${jwt.refresh-token-validity-in-seconds}") long refreshSeconds
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidity = accessSeconds * 1000;
        this.refreshTokenValidity = refreshSeconds * 1000;
    }

    /* ======================
       Access Token
       ====================== */
    public String createAccessToken(CustomUser user) {
        return createToken(user, accessTokenValidity, false);
    }

    /* ======================
       Refresh Token
       ====================== */
    public String createRefreshToken(CustomUser user) {
        return createToken(user, refreshTokenValidity, true);
    }

    /* ======================
       공통 토큰 생성
       ====================== */
    private String createToken(CustomUser user, long validity, boolean isRefresh) {
        long now = System.currentTimeMillis();
        Date expiry = new Date(now + validity);

        var builder = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(now))
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256);

        if (isRefresh) {
            builder.claim("type", "refresh");
        } else {
            builder.claim("email", user.getUser().getMe_email());
            builder.claim("role", user.getAuthorities().iterator().next().getAuthority());
        }

        return builder.compact();
    }

    /* ======================
       토큰 파싱 & 검증
       ====================== */
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isRefreshToken(String token) {
        return "refresh".equals(parseClaims(token).get("type"));
    }
}
