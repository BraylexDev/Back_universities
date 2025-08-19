package com.universities.universities.utilities;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.universities.universities.model.User;
import com.universities.universities.repository.UserRepository;


@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUserName("Admin").isEmpty()) {
            User user = new User();
            user.setUserName("Admin");
            user.setName("Administrator");
            user.setRol("Administrator");
            user.setEmail("user@example.com");

            // Encriptamos la contraseña
            String hashedPassword = passwordEncoder.encode("admin");
            user.setPassword(hashedPassword);

            userRepository.save(user);
        }
    }

}
