package com.ous.aethererp.service;


import com.ous.aethererp.entity.UserEntity;
import com.ous.aethererp.io.ProfileRequest;
import com.ous.aethererp.io.ProfileResponse;
import com.ous.aethererp.repo.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final UserEntityRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    @Override
    public ProfileResponse createProfile(ProfileRequest request) {

        UserEntity userEntity= convertToUserEntity(request);
        if(!userRepo.existsByEmail(userEntity.getEmail())){
            userEntity = userRepo.save(userEntity);
            return convertToProfileResponse(userEntity);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists.");
    }

    @Override
    public ProfileResponse getProfile(String email) {
        UserEntity existingUserProfile = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    return convertToProfileResponse(existingUserProfile);
    }

    @Override
    public void sendResetOTP(String email) {
        UserEntity existingUserByEmail = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found " + email));

        // Generate 6 digit otp
        String otp = String.valueOf(ThreadLocalRandom.current().nextInt(
                100000, 1000000));

        // Calculate expiry time (current time 15 mins in milliseconds)
        long expiryTime = System.currentTimeMillis() + (15 * 60 * 1000);

        // Update the profile / user
        existingUserByEmail.setResetOTP(otp);
        existingUserByEmail.setResetOTPExpiredAt(expiryTime);

        // Save the data into the database
        userRepo.save(existingUserByEmail);

        try{
            emailService.sendResetOTPEmail(existingUserByEmail.getEmail(), otp);
        } catch (Exception e){
            throw new RuntimeException("Unable to send email.");
        }
    }

    @Override
    public void resetPassword(String email, String otp, String newPassword) {
       UserEntity existingUser= userRepo.findByEmail(email)
               .orElseThrow(() -> new UsernameNotFoundException("User email not found: " + email));

       // Check if the otp is null or the existing otp is not equal to the provided one
        if (existingUser.getResetOTP() == null || !existingUser.getResetOTP().equals(otp)){
            throw new RuntimeException("Invalid OTP");
        }

        if (existingUser.getResetOTPExpiredAt() < System.currentTimeMillis()){
            throw new RuntimeException("OTP Expired.");
        }

        existingUser.setPassword(passwordEncoder.encode(newPassword));
        existingUser.setResetOTP(null);
        existingUser.setResetOTPExpiredAt(0L);

        // resave user back to the database
        userRepo.save(existingUser);
    }

    @Override
    public void sendOTP(String email) {
        UserEntity existingUser = userRepo.findByEmail(email)
                .orElseThrow(()  -> new UsernameNotFoundException("User not found: "+ email));

        if (existingUser.getIsAccountVerified() != null && existingUser.getIsAccountVerified()){
            return;
        }

        // Generate 6 digits otp
        String otp = String.valueOf(ThreadLocalRandom.current().nextInt(
                100000, 1000000));

        // Calculate expiry time (current time 15 mins in milliseconds)
        long expiryTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000);


        // Update the profile / user
        existingUser.setVerifyOTP(otp);
        existingUser.setVerifyExpiredAt(expiryTime);

        // Save the data into the database
        userRepo.save(existingUser);
    }

    @Override
    public void verifyOTP(String userId, String otp) {

    }

    @Override
    public String getLoggedInUserId(String email) {
        return null;
    }

    // TODO: This method takes a database entity and convert it back into a restful response to reduce load and frequent requests on the database.
    private ProfileResponse convertToProfileResponse(UserEntity newProfile) {
        return ProfileResponse.builder()
                .userId(newProfile.getUserId())
                .name(newProfile.getName())
                .email(newProfile.getEmail())
                .isAccountVerified(newProfile.getIsAccountVerified())
                .build();
    }

    // TODO: This method takes a restful request and turn it back to a database entity
    private UserEntity convertToUserEntity(ProfileRequest request) {
         return UserEntity.builder()
                .email(request.getEmail())
                .userId(UUID.randomUUID().toString())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                 .isAccountVerified(false)
                 .resetOTPExpiredAt(0L)
                 .verifyOTP(null)
                 .verifyExpiredAt(0L)
                 .resetOTP(null)
                 .build();
    }
}
