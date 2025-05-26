package com.nanhng.FastFood.security.interceptor;

import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.service.repository.user.UserRepository;
import com.nanhng.FastFood.security.JwtToKenProvider;
import com.nanhng.FastFood.security.SecurityContexts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;
    private final JwtToKenProvider jwtToKenProvider;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtToKenProvider.validateJwt(token)) {
                Integer id = jwtToKenProvider.getSubIdFromToken(token);
                SecurityContexts.newContext();
                User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
                SecurityContexts.getContext().setData(user);
                return true;
            }
            throw new Exception("Invalid token");
        }
        return true;
    }
}
