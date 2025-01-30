package com.chrono.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chrono.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findByNameContaining(String name); 
    public Optional<User> findById(Integer id); 
    public Optional<User> findByEmail(String email);
}
