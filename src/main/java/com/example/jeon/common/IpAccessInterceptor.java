package com.example.jeon.common;

import com.example.jeon.repository.WhiteIpRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class IpAccessInterceptor implements HandlerInterceptor {
    private final WhiteIpRepository whiteIpRepository;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String clientIp = Utils.getClientIP(request);
        if (clientIp.equals("127.0.0.1")) {
            // 로컬 접속이면 당연히 true
            return true;
        }

        if (!whiteIpRepository.findByAccessIp(clientIp).isPresent()) {
            log.warn("Forbidden access, URI: {}, IP: {}", request.getRequestURI(), clientIp);
            response.sendError(403, "IP Forbidden");
            return false;
        }

        return true;
    }


}
