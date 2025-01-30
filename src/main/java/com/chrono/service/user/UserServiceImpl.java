package com.chrono.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrono.domain.user.User;
import com.chrono.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // GET to list all users
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // GET to find user by name
    @Override
    public List<User> findUserByName(String name) {
        return userRepository.findByNameContaining(name);
    }

    // GET to find user by id
    @Override
    public User findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // PUT to update user
    @Override
    public void updateUser(User user) {
        User currentUser = this.findUserById(user.getId());
        if (user.getName() != null) {
            currentUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            currentUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            currentUser.setPassword(user.getPassword());
        }
        if (user.getRole() != null) {
            currentUser.setRole(user.getRole());
        }

        userRepository.save(currentUser);
    }

    // POST to save user
    @Override
    public User saveUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    // DELETE to delete user
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}