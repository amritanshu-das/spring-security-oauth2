package com.win.repository;

import com.win.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByLogin(String pLogin);
}