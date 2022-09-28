package dev.marcorangel.health_care_backend.security;

import dev.marcorangel.health_care_backend.model.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class ApiAuthenticationEntryPoint implements Serializable {

    @Serial
    private static final long serialVersionUID = 1330736483562178207L;

    private final UserDetailsService userDetailsService;

    public Authentication getAuthentication(String username) {
        UserDetails userDetail = userDetailsService.loadUserByUsername(username);
        if (userDetail == null) return null;
        ApplicationUser applicationUser = (ApplicationUser) userDetail;
        ApplicationUser authenticatedUser = ApplicationUser.builder()
                .user_name(applicationUser.getUser_name())
                .user_email(applicationUser.getUser_email())
                .user_mobile(applicationUser.getUser_mobile())
                .location(applicationUser.getLocation())
                .build();
        return new UsernamePasswordAuthenticationToken(authenticatedUser, "", userDetail.getAuthorities());
    }

}