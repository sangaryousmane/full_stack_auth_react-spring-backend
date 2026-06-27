package com.ous.aethererp.controller;

import com.ous.aethererp.io.ProfileRequest;
import com.ous.aethererp.io.ProfileResponse;
import com.ous.aethererp.service.EmailService;
import com.ous.aethererp.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Profile API")
public class ProfileController {

    private final ProfileService profileService;
    private final EmailService emailService;

    @Operation(summary = "Register User", description = "Creates New User's Profile")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody ProfileRequest request){
        ProfileResponse profile = profileService.createProfile(request);

        // Send email
        emailService.sendWelcomeEmail(profile.getEmail(), profile.getName());
        return profile;
    }


    @GetMapping("/profile")
    public ProfileResponse getProfileDetails(
            @CurrentSecurityContext(expression = "authentication?.name") String email){
        if (email == null) throw new RuntimeException("Email is unavailable.");
    return profileService.getProfile(email);
    }

    // Testable API
    @GetMapping("/test")
    public String hello(){
        return "Auth is working";
    }

}

// pwd: 83180043
// pwd: mulbah3030