package dev.marcorangel.health_care_backend.service;

import dev.marcorangel.health_care_backend.model.ApplicationUser;
import dev.marcorangel.health_care_backend.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;

    public void registerUser(String user_name,  String password) {
        applicationUserRepository.save(new ApplicationUser(user_name, password));
    }

    public String viewAllProfiles() {
        return applicationUserRepository.findAll().toString();
    }

    public ApplicationUser editProfile(String user_name, String user_email, String password, String user_mobile,String location) {
        return applicationUserRepository.save(new ApplicationUser(user_name, user_email, password, user_mobile, location));
    }
}
