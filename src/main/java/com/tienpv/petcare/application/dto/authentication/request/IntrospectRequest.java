package com.tienpv.petcare.application.dto.authentication.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectRequest {

    private String token;
}
