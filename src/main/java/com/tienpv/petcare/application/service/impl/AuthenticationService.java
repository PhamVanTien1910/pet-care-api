package com.tienpv.petcare.application.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tienpv.petcare.application.dto.authentication.request.IntrospectRequest;
import com.tienpv.petcare.application.dto.authentication.request.LoginRequest;
import com.tienpv.petcare.application.dto.authentication.request.RefreshToken;
import com.tienpv.petcare.application.dto.authentication.request.TokenRequest;
import com.tienpv.petcare.application.dto.authentication.response.IntrospectResponse;
import com.tienpv.petcare.application.dto.authentication.response.LoginResponse;
import com.tienpv.petcare.application.dto.request.RegisterRequest;
import com.tienpv.petcare.application.dto.response.RegisterResponse;
import com.tienpv.petcare.application.exception.AppException;
import com.tienpv.petcare.application.exception.ErrorCode;
import com.tienpv.petcare.domain.constant.PredefineRole;
import com.tienpv.petcare.domain.entity.auth.RoleEntity;
import com.tienpv.petcare.domain.entity.auth.TokenEntity;
import com.tienpv.petcare.domain.entity.UserEntity;
import com.tienpv.petcare.domain.repository.IUserRepository;
import com.tienpv.petcare.domain.repository.InvalidatedTokenRepository;
import com.tienpv.petcare.domain.repository.admin.RoleRepository;
import com.tienpv.petcare.domain.service.IAuthenticationService;
import com.tienpv.petcare.infrastructure.converter.IAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements IAuthenticationService {

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGER_KEY;
    @Value("${jwt.issuer}")
    protected String ISSUER;
    @Autowired
    private IAuthenticationConverter userConverter;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private InvalidatedTokenRepository tokenRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        UserEntity userEntity = new UserEntity();
        if (userRepository.existsByUserName(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        userEntity = userConverter.toRegisterEntity(request);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<RoleEntity> roles = new HashSet<>();
        roleRepository.findById(PredefineRole.USER_ROLE).ifPresent(roles::add);
        userEntity.setRoles(roles);
        try {
            userEntity = userRepository.save(userEntity);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        return userConverter.toRegisterDTO(userEntity);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var userEntity = userRepository
                .findByUserName(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean authenticated = passwordEncoder.matches(request.getPassword(), userEntity.getPassword());

        if (authenticated == true) {
            String token = generationToken(userEntity);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            return loginResponse;

        } else {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest input) throws JOSEException, ParseException {
        IntrospectResponse introspectOutput = new IntrospectResponse();
        String token = input.getToken();

        try {
            verifyToken(token);
        } catch (AppException e) {
            introspectOutput.setValid(false);
            return introspectOutput;
        }

        introspectOutput.setValid(true);
        return introspectOutput;
    }

    private String buildScope(UserEntity user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!org.springframework.util.CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!org.springframework.util.CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }

    @Override
    public void logout(TokenRequest request) throws JOSEException, ParseException {
        SignedJWT signToken = verifyToken(request.getToken());

        String jti = signToken.getJWTClaimsSet().getJWTID();
        Date expirytime = signToken.getJWTClaimsSet().getExpirationTime();

        TokenEntity invalidatedTokenEntity =
                TokenEntity.builder().id(jti).expirytime(expirytime).build();
        tokenRepository.save(invalidatedTokenEntity);
    }

    @Override
    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(verifier);

        if (!(verified && expityTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        if (tokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return signedJWT;
    }

    @Override
    public LoginResponse refreshToken(RefreshToken request) throws JOSEException, ParseException {
        SignedJWT signedJWT = verifyToken(request.getToken());
        String jti = signedJWT.getJWTClaimsSet().getJWTID();
        Date expirytime = signedJWT.getJWTClaimsSet().getExpirationTime();
        TokenEntity invalidatedTokenEntity =
                TokenEntity.builder().id(jti).expirytime(expirytime).build();
        tokenRepository.save(invalidatedTokenEntity);

        String username = signedJWT.getJWTClaimsSet().getSubject();
        UserEntity user =
                userRepository.findByUserName(username).orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));
        String token = generationToken(user);
        LoginResponse authenticationOutput = new LoginResponse();
        authenticationOutput.setToken(token);
        return authenticationOutput;
    }

    @Override
    public String generationToken(UserEntity user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer(ISSUER)
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException();
        }
    }


}
