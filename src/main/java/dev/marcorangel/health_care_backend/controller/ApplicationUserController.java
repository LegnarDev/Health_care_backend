package dev.marcorangel.health_care_backend.controller;

import dev.marcorangel.health_care_backend.model.ApplicationUser;
import dev.marcorangel.health_care_backend.repository.ApplicationUserRepository;
import dev.marcorangel.health_care_backend.security.JwtUtil;
import dev.marcorangel.health_care_backend.service.ApplicationUserService;
import dev.marcorangel.health_care_backend.service.UserAuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
public class ApplicationUserController {

    private ApplicationUserService applicationUserService;

    private AuthenticationManager authenticationManager;

    private ApplicationUserRepository applicationUserRepository;
    private JwtUtil jwtUtil;

    //register user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody ApplicationUser applicationUser) {

        if (applicationUser == null)
            return ResponseEntity.badRequest().build();
        applicationUserService.registerUser(applicationUser.getUser_name(), applicationUser.getPassword());
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    //login user
    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody ApplicationUser user) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    user.getUser_name(),
                    user.getPassword()
        ));

        log.info(String.valueOf(authentication));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAuthService userAuthService = (UserAuthService) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtil.generateJwtCookie(userAuthService);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new ApplicationUser(user.getUser_name(),
                        user.getPassword()).toString()
                );
    }
    //view user profile
    @GetMapping("/viewprofile/{userId}")
    public ResponseEntity<String> viewProfile(@PathVariable String userId) {
        applicationUserService.viewAllProfiles();
        return new ResponseEntity<>("View all users", HttpStatus.OK);
    }
    //edit user profile
    @PutMapping("/editprofile/{userId}")
    public ResponseEntity<String> editProfile(@PathVariable String userId) {
        return new ResponseEntity<>("User profile edited", HttpStatus.OK);
    }
}
