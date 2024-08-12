package com.tienpv.petcare.infrastructure.converter.impl;

import com.tienpv.petcare.application.dto.request.RegisterRequest;
import com.tienpv.petcare.application.dto.response.RegisterResponse;
import com.tienpv.petcare.domain.entity.UserEntity;
import com.tienpv.petcare.infrastructure.converter.IAuthenticationConverter;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationConverter implements IAuthenticationConverter {
    @Override
    public RegisterResponse toRegisterDTO(UserEntity entity) {
        RegisterResponse response = new RegisterResponse();
        response.setEmail(entity.getEmail());
        response.setUserName(entity.getUserName());
        response.setId(entity.getId());
        return response;
    }

    @Override
    public UserEntity toRegisterEntity(RegisterRequest request) {
        UserEntity entity = new UserEntity();
        entity.setEmail(request.getEmail());
        entity.setUserName(request.getUsername());
        return entity;
    }
}
