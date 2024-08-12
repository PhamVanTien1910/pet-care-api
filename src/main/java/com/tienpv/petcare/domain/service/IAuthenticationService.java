package com.tienpv.petcare.domain.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.tienpv.petcare.application.dto.authentication.request.IntrospectRequest;
import com.tienpv.petcare.application.dto.authentication.request.LoginRequest;
import com.tienpv.petcare.application.dto.authentication.request.RefreshToken;
import com.tienpv.petcare.application.dto.authentication.request.TokenRequest;
import com.tienpv.petcare.application.dto.authentication.response.IntrospectResponse;
import com.tienpv.petcare.application.dto.authentication.response.LoginResponse;
import com.tienpv.petcare.application.dto.request.RegisterRequest;
import com.tienpv.petcare.application.dto.response.RegisterResponse;
import com.tienpv.petcare.domain.entity.UserEntity;

import java.text.ParseException;

public interface IAuthenticationService {
    RegisterResponse register(RegisterRequest request);

    String generationToken(UserEntity user);

    LoginResponse login(LoginRequest request);

    IntrospectResponse introspect(IntrospectRequest input) throws JOSEException, ParseException;

    void logout(TokenRequest request) throws JOSEException, ParseException;

    SignedJWT verifyToken(String token) throws JOSEException, ParseException;

    LoginResponse refreshToken(RefreshToken request) throws JOSEException, ParseException;
}
