package com.tienpv.petcare.domain.constant;

public class PublicEnpoints {

    public static final String[] POST_ENDPOINTS = {
            "/auth/login", "/auth/introspect", "/auth/logout", "/auth/refresh", "/auth/register"
    };

    public static final String[] GET_ENDPOINTS = {
            "/api-docs/**", "/swagger-ui/**"
    };

    private PublicEnpoints() {

    }
}
