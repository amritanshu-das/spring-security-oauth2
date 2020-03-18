package com.win.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer pClients) throws Exception {
        
        pClients.inMemory().withClient("first-client").secret(passwordEncoder().encode("first_password"))
        .scopes("resource:read")
        .authorizedGrantTypes("authorization_code").redirectUris("http://localhost:8080/oauth/login/");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer pSecurity) throws Exception {
        pSecurity.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
        .allowFormAuthenticationForClients();
    }
}