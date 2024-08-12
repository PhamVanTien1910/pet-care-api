package com.tienpv.petcare.application.dto.authentication.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenRequest {

    private String token;
}
