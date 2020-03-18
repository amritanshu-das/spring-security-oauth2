package com.win.security;

import com.win.security.encoder.CustomPasswordEncoder;
import com.win.security.service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Override
    protected void configure(AuthenticationManagerBuilder pAuth) throws Exception {
        pAuth.authenticationProvider(authProvider());
    }
    
    @Bean
    public UserDetailsService customUserDetails() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder customPasswordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider lAuthProvider = new DaoAuthenticationProvider();
        lAuthProvider.setUserDetailsService(customUserDetails());
        lAuthProvider.setPasswordEncoder(customPasswordEncoder());
        return lAuthProvider;
    }

    // @Bean
    // @Override
    // protected UserDetailsService userDetailsService() {
    // UserDetails user = User.withDefaultPasswordEncoder()
    // .username("enduser")
    // .password("password")
    // .roles("USER")
    // .build();

    // LOGGER.info("Inside UserDetailsService " + user.getPassword());
    // return new InMemoryUserDetailsManager(user);
    // }
}