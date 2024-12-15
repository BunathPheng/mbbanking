package co.istad.mbbanking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEnCoderConfig {
    @Bean
    PasswordEncoder  configPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
