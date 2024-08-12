package com.tienpv.petcare.infrastructure.controller;

import com.nimbusds.jose.JOSEException;
import com.tienpv.petcare.application.dto.authentication.request.IntrospectRequest;
import com.tienpv.petcare.application.dto.authentication.request.LoginRequest;
import com.tienpv.petcare.application.dto.authentication.request.RefreshToken;
import com.tienpv.petcare.application.dto.authentication.request.TokenRequest;
import com.tienpv.petcare.application.dto.authentication.response.IntrospectResponse;
import com.tienpv.petcare.application.dto.authentication.response.LoginResponse;
import com.tienpv.petcare.application.dto.request.RegisterRequest;
import com.tienpv.petcare.application.dto.response.ApiResponse;
import com.tienpv.petcare.application.dto.response.RegisterResponse;
import com.tienpv.petcare.domain.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class Authentication {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ApiResponse<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        RegisterResponse results = authenticationService.register(request);
        ApiResponse<RegisterResponse> response = new ApiResponse<>();
        response.setResult(results);
        return response;
    }

    @PostMapping(value = "/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse results = authenticationService.login(request);
        ApiResponse<LoginResponse> response = new ApiResponse<>();
        response.setResult(results);
        return response;
    }


    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws JOSEException, ParseException {
        IntrospectResponse result = authenticationService.introspect(request);
        ApiResponse<IntrospectResponse> response = new ApiResponse<>();
        response.setResult(result);
        return response;
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody TokenRequest request) throws JOSEException, ParseException {
        authenticationService.logout(request);
        ApiResponse<Void> response = new ApiResponse<>();
        return response;
    }

    @PostMapping("/refresh")
    ApiResponse<LoginResponse> refresh(@RequestBody RefreshToken request) throws JOSEException, ParseException {
        LoginResponse authenticationOutput = authenticationService.refreshToken(request);
        ApiResponse<LoginResponse> response = new ApiResponse<>();
        response.setResult(authenticationOutput);
        return response;
    }
}
