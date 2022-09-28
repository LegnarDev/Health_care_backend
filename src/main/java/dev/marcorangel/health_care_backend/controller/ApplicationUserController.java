package dev.marcorangel.health_care_backend.controller;

import dev.marcorangel.health_care_backend.model.ApplicationUser;
import dev.marcorangel.health_care_backend.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Data
@Builder
public class ApplicationUserController {
    private ApplicationUserService applicationUserService;

    //register user
    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registerUser(@RequestBody ApplicationUser user) {
        if (user == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(applicationUserService.registerUser(user));
    }

    @GetMapping
    public ApplicationUser currentUser(@AuthenticationPrincipal ApplicationUser authUser) {
        return applicationUserService.currentUser(authUser);
    }

    //login user
    @PostMapping("/signin")
    public ResponseEntity<ApplicationUser> loginUser(@RequestBody ApplicationUser user) {
        return ResponseEntity.ok(applicationUserService.loginUser(user));
    }
    //view user profile
    @GetMapping("/viewprofile/{userId}")
    public ResponseEntity<ApplicationUser> viewProfile(@PathVariable String userId) {
        return new ResponseEntity<>(applicationUserService.viewAllProfiles(userId), HttpStatus.OK);
    }
    //edit user profile
    @PutMapping("/editprofile/{userId}")
    public ResponseEntity<String> editProfile(@PathVariable String userId) {
        return new ResponseEntity<>("User profile edited", HttpStatus.OK);
    }
}
