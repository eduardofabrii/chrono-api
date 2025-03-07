package com.chrono.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chrono.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * Classe de serviço para manipulação de autorização de usuários.
 * Implementa a interface UserDetailsService para fornecer detalhes do usuário com base no nome de usuário.
 */
@Service
@AllArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username);
    }

}
