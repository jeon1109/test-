package com.example.jeon.token;

import com.example.jeon.dto.Role;
import com.example.jeon.dto.TokenDTO;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Component
@Log4j2
public class JwtTokenProvider {

    private final Key encodedKey;
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_VALIDITY = 30 * 60 * 1000L; // 
    private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000L; //

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
        // base64로 디코드해준다 (시크릿키를) => yml에 등록함
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        // 서명 키를 가져온다
        this.encodedKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDTO createTokenDTO(String subject, List<Role> roles) {
        // 권한을 가져온다
        String authorities = roles.stream()
                .map(Role::getType)
                .collect(Collectors.joining(","));

        Instant now = Instant.now();
        
        // 엑세스 토큰 만들기
        String accessToken = Jwts.builder()
                .setSubject(subject)
                .claim("roles", authorities)
                .setExpiration(Date.from(now.plusMillis(ACCESS_TOKEN_VALIDITY)))
                .signWith(encodedKey)
                .compact();
        
        // 리프레쉬 토큰 만들기
        String refreshToken = Jwts.builder()
                .setExpiration(Date.from(now.plusMillis(REFRESH_TOKEN_VALIDITY)))
                .signWith(encodedKey)
                .compact();

        return TokenDTO.builder()
                .tokenType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .duration(Duration.ofMillis(REFRESH_TOKEN_VALIDITY))
                .build();
    }

    /**
     * UsernamePasswordAuthenticationToken으로 보내 인증된 유저인지 확인
     *
     * @param accessToken
     * @return Authentication
     * @throws ExpiredJwtException
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String accessToken) throws ExpiredJwtException {
        // jwt 서명검증
        Claims claims = Jwts.parserBuilder().setSigningKey(encodedKey)
                .build()
                .parseClaimsJws(accessToken).getBody();

        if(claims.get("roles") == null) {
            throw new RuntimeException("권한정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> roles =
                Arrays.stream(claims.get("roles")
                                .toString()
                                .split(","))
                .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        UserDetails user = new User(claims.getSubject(), "", roles);
        return new UsernamePasswordAuthenticationToken(user, "", roles);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(encodedKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
