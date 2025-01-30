package com.chrono.service.user;

import java.util.List;

import com.chrono.domain.user.User;

public interface UserService {
    public List<User> findAllUsers();
    public List<User> findUserByName(String name);
    public User findUserById(Integer id);
    public void updateUser(User user);
    public User saveUser(User user);
    public void deleteUserById(Long id);
    public void updateLastLogin(String email);
}
