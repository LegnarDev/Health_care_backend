package dev.marcorangel.health_care_backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ApiAuthenticationEntryPoint apiAuthenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("Inside Once Per Request Filter originated by request " + request.getRequestURI() + " And authentication header " + request.getHeader(HttpHeaders.AUTHORIZATION));
        String jwt = jwtUtil.resolveToken(request);
        if (jwt != null && jwtUtil.validateToken(jwt)) {
            String username = jwtUtil.getSub(jwt);
            Authentication authentication = apiAuthenticationEntryPoint.getAuthentication(username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}