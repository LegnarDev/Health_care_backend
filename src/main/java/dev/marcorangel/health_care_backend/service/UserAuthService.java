package dev.marcorangel.health_care_backend.service;

import dev.marcorangel.health_care_backend.model.ApplicationUser;
import dev.marcorangel.health_care_backend.repository.ApplicationUserRepository;
import dev.marcorangel.health_care_backend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        //return new User(user.getUser_name(), user.getPassword(), new ArrayList<>());
        return UserDetailsImpl.builder()
                .user_name(user.getUser_name())
                .user_email(user.getUser_email())
                .user_mobile(user.getUser_mobile())
                .password(user.getPassword())
                .location(user.getLocation())
                .build();
    }

}