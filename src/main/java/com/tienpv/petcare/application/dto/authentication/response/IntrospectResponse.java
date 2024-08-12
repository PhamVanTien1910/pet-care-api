package com.tienpv.petcare.application.dto.authentication.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class IntrospectResponse {

    boolean valid;
}
