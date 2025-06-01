package com.example.jeon.token;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor /* 생성자 필요없음 */
@Log4j2
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        // 이 경로들은 JWT 검사 안 하고 통과시킴
        if (path.startsWith("/auth") || path.startsWith("/public") || path.equals("/")) {
            filterChain.doFilter(request, response); // 다음 필터로 넘어가!
            return;
        }

          String jwt = resolveToken(request);
        // jwt와 밸리데이션 토큰 조건
         if(StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
            // 서명 검증
            Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
         filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String BEARER_PREFIX = "Bearer ";
        if (StringUtils.hasText(token) && token.startsWith(BEARER_PREFIX)) {
            return token.substring (BEARER_PREFIX.length());
        }
        return null;
    }

}
