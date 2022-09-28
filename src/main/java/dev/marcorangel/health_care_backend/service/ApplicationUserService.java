package dev.marcorangel.health_care_backend.service;

import dev.marcorangel.health_care_backend.model.ApplicationUser;
import dev.marcorangel.health_care_backend.repository.ApplicationUserRepository;
import dev.marcorangel.health_care_backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final PasswordEncoder passwordEncoder;

    public ApplicationUser registerUser(ApplicationUser user) {
        ApplicationUser userRepository = applicationUserRepository.save(ApplicationUser.builder().user_name(user.getUser_name()).user_email(user.getUser_email()).password(passwordEncoder.encode(user.getPassword())).user_mobile(user.getUser_mobile()).location(user.getLocation()).build());
        log.info(userRepository.toString());
        return userRepository;
    }

    public ApplicationUser loginUser(ApplicationUser user) {
        if (user.getUser_email() == null) {
            ApplicationUser loggedUser = applicationUserRepository.findByUsername(user.getUser_name()).filter(validateUser -> passwordEncoder.matches(user.getPassword(), validateUser.getPassword())).orElseThrow(() -> new NullPointerException("login information is invalid"));
            loggedUser = loggedUser.builder().build();
            return loggedUser;
        }
        return applicationUserRepository.findByEmail(user.getUser_email()).filter(validateUser -> passwordEncoder.matches(user.getPassword(), validateUser.getPassword())).orElseThrow(() -> new NullPointerException("login information is invalid"));
    }

    public ApplicationUser viewAllProfiles(String username) {
        Optional<ApplicationUser> userEntity = applicationUserRepository.findById(username);
        if (userEntity.isPresent()) {
            return userEntity.get();
        }else {
            throw new NullPointerException("User not found");
        }
    }

    public ApplicationUser editProfile(String user_name, String user_email, String password, String user_mobile,String location) {
        return applicationUserRepository.save(new ApplicationUser(user_name, user_email, password, user_mobile, location));
    }
}
