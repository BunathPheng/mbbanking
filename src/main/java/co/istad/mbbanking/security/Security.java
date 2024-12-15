package co.istad.mbbanking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class Security {
    private  final PasswordEncoder passwordEnCoder;
    private final UserDetailsService userDetailsService;

    @Bean
    DaoAuthenticationProvider  configDaoAuthenticationProvider()
    {
        DaoAuthenticationProvider  auth  =  new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEnCoder);
        return auth;
    }

//    @Bean
//    InMemoryUserDetailsManager configureUserSecurity() {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEnCoder.encode("user123"))
//                .roles("USER")// row password  is  not encrypted
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEnCoder.encode("admin123"))  // row password  is  not encrypted
//                .roles("USER", "ADMIN")
//                .build();
//        UserDetails manager = User.withUsername("manger")
//                .password(passwordEnCoder.encode("manger123"))
//                .roles("USER", "MANAGER")
//                .build();
//        UserDetails customer = User.withUsername("customer")
//                .password(passwordEnCoder.encode("customer123"))
//                .roles("USER", "CUSTOMER")
//                .build();
//        InMemoryUserDetailsManager managerDetail = new InMemoryUserDetailsManager();
//        managerDetail.createUser(admin);
//        managerDetail.createUser(manager);
//        managerDetail.createUser(customer);
//        managerDetail.createUser(user);
//        return managerDetail;
//    }

    @Bean
    public SecurityFilterChain  configureApiSecurity(HttpSecurity  httpSecurity)  throws Exception
    {
        //Endpoint security   Config
        //When   we set  to  rules we  use  in config  all endpoint security
        httpSecurity.authorizeHttpRequests( endpoint ->  endpoint.
                requestMatchers(HttpMethod.GET ,  "api/v1/accounts/**").hasAnyRole("USER" , "ADMIN")
                .requestMatchers(HttpMethod.POST ,  "api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PUT , "api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PATCH , "api/v1/accounts/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.DELETE , "api/v1/accounts/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.POST , "api/v1/account-types/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PUT,  "api/v1/account-types/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.DELETE , "api/v1/account-types/**").hasAnyRole("ADMIN")
                .requestMatchers(HttpMethod.GET , "api/v1/account-types/**").hasAnyRole("USER")
                .requestMatchers(HttpMethod.PATCH , "api/v1/account-types/**").hasAnyRole("USER")
                .anyRequest().authenticated());
        //Security  Mechanism  (Http basic  Auth)
        //HTTP  Basic  Auth   (Username & Password)
        httpSecurity.httpBasic(Customizer.withDefaults());

        //Disabled   CSRF  token
        httpSecurity.csrf( token ->  token.disable() );

        //Make stateless Session
        httpSecurity.sessionManagement(session ->  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return  httpSecurity.build();
    }

}