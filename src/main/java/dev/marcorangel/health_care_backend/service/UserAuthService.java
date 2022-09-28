package dev.marcorangel.health_care_backend.service;

import dev.marcorangel.health_care_backend.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return applicationUserRepository.findByEmail(email).orElse(null);
    }
}