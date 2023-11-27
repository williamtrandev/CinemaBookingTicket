package com.codingduo.cinemabookingticket.config;

import com.codingduo.cinemabookingticket.model.UserSystem;
import com.codingduo.cinemabookingticket.repository.UserSystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserSystemRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void run(String... args) {
        // Tạo một tài khoản admin nếu chưa tồn tại
        if (!adminRepository.existsByEmail("admin")) {
            UserSystem admin = new UserSystem();
            admin.setName("admin");
            admin.setEmail("admin");
            admin.setPassword(bCryptPasswordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            adminRepository.save(admin);
        }
    }
}
