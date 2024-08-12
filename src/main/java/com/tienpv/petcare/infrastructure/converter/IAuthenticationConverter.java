package com.tienpv.petcare.infrastructure.converter;

import com.tienpv.petcare.application.dto.request.RegisterRequest;
import com.tienpv.petcare.application.dto.response.RegisterResponse;
import com.tienpv.petcare.domain.entity.UserEntity;

public interface IAuthenticationConverter {
    RegisterResponse toRegisterDTO(UserEntity entity);

    UserEntity toRegisterEntity(RegisterRequest request);

}
