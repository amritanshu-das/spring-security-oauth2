package com.win.security.service;

import com.win.model.User;
import com.win.repository.UserRepository;
import com.win.security.principal.CustomUserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

// @Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String pUsername) throws UsernameNotFoundException {
        final User lUser = userRepository.findByLogin(pUsername);
        if (lUser == null) {
            throw new UsernameNotFoundException(pUsername);
        }
        return new CustomUserPrincipal(lUser);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}