package com.ous.aethererp.service;

import com.ous.aethererp.io.ProfileRequest;
import com.ous.aethererp.io.ProfileResponse;

public interface ProfileService {


    ProfileResponse createProfile(ProfileRequest request);

    ProfileResponse getProfile(String email);
    void sendResetOTP(String email);
}
