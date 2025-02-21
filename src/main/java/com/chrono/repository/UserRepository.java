package com.chrono.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.chrono.domain.user.User;
import com.chrono.domain.user.UserRole;

public interface UserRepository extends JpaRepository<User, Long> {
    public UserDetails findByName(String name);
    public List<User> findByNameContaining(String name); 
    public Optional<User> findById(Integer id); 
    public Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);
}
