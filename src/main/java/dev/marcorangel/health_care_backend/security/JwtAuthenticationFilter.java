package dev.marcorangel.health_care_backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilter {

    private final JwtUtil jwtUtil;
    private final ApiAuthenticationEntryPoint apiAuthenticationEntryPoint;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = jwtUtil.resolveToken(request);
        if (jwt != null && jwtUtil.validateToken(jwt)) {
            String username = jwtUtil.getSub(jwt);
            Authentication authentication = apiAuthenticationEntryPoint.getAuthentication(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}