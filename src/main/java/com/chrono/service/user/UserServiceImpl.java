package com.chrono.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.user.User;
import com.chrono.mapper.UserMapper;
import com.chrono.repository.UserRepository;
import com.chrono.request.user.UserPostRequest;
import com.chrono.request.user.UserPutRequest;
import com.chrono.response.user.UserGetResponse;
import com.chrono.response.user.UserPostResponse;
import com.chrono.response.user.UserPutResponse;

import com.chrono.exceptions.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

// Implementação do serviço de usuários.
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final UserHelper userHelper;

    /**
     * Obtém todos os usuários.
     * 
     * @return uma lista de respostas de usuários.
     */
    @Override
    public List<UserGetResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return mapper.toUserGetResponseList(users);
    }

    /**
     * Obtém os usuários pelo nome.
     * 
     * @param name o nome do usuário a ser buscado.
     * @return uma lista de respostas de usuários que contêm o nome fornecido.
     */
    @Override
    public List<UserGetResponse> findUserByName(String name) {
        List<User> users = userRepository.findByNameContaining(name);

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        return mapper.toUserGetResponseList(users);
    }

    /**
     * Obtém um usuário pelo ID.
     * 
     * @param id o ID do usuário a ser buscado.
     * @return a resposta do usuário encontrado.
     * @throws ResourceNotFoundException se o usuário não for encontrado.
     */
    @Override
    public UserGetResponse findUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return mapper.toUserGetResponse(user);
    }

    /**
     * Atualiza um usuário existente.
     * 
     * @param id o ID do usuário a ser atualizado.
     * @param dto os dados de atualização do usuário.
     * @return a resposta do usuário atualizado.
     * @throws ResourceNotFoundException se o usuário não for encontrado.
     */
    @Override
    public UserPutResponse updateUser(Integer id, UserPutRequest dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Delegar a atualização dos campos do usuário para o helper
        userHelper.updateUserFields(user, dto);

        // Salvar o usuário atualizado
        userRepository.save(user);
        return mapper.toUserPutResponse(user);
    }

    /**
     * Salva um novo usuário.
     * 
     * @param postRequest os dados do novo usuário.
     * @return a resposta do usuário salvo.
     */
    @Override
    public UserPostResponse saveUser(UserPostRequest postRequest) {
        User user = mapper.toUserPost(postRequest);
        userRepository.save(user);
        return mapper.toUserPostResponse(user);
    }

    /**
     * Exclui um usuário pelo ID.
     * 
     * @param id o ID do usuário a ser excluído.
     * @throws ResourceNotFoundException se o usuário não for encontrado.
     */
    @Override
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }

    /**
     * Atualiza o último login do usuário.
     * 
     * @param email o email do usuário cujo último login será atualizado.
     * @throws ResourceNotFoundException se o usuário não for encontrado.
     */
    @Override
    public void updateLastLogin(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }
}
