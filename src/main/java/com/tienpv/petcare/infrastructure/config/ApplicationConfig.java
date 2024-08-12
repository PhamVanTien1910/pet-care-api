package com.tienpv.petcare.infrastructure.config;

import com.tienpv.petcare.domain.constant.PredefineRole;
import com.tienpv.petcare.domain.entity.auth.RoleEntity;
import com.tienpv.petcare.domain.entity.UserEntity;
import com.tienpv.petcare.domain.repository.IUserRepository;
import com.tienpv.petcare.domain.repository.admin.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class ApplicationConfig {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    ApplicationRunner applicationRunner(IUserRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {

                RoleEntity adminRole = roleRepository.save(RoleEntity.builder()
                        .name(PredefineRole.ADMIN_ROLE)
                        .description("Admin role")
                        .build());

                Set<RoleEntity> roles = new HashSet<>();
                roles.add(adminRole);

                UserEntity user = UserEntity.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been create with default password: admin, 1");
            }
        };
    }
}
