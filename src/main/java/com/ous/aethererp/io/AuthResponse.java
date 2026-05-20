package com.ous.aethererp.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
@AllArgsConstructor
public class AuthResponse {

    private String email;
    private String token;
}
