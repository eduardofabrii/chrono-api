package com.chrono.service.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.user.User;
import com.chrono.domain.user.UserRole;
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
     * Realiza a exclusão lógica (soft delete) de um usuário no sistema.
     * Este método marca o usuário como excluído ao definir o campo 'deletedAt' 
     * com a data e hora atual, sem remover fisicamente o registro do banco de dados.
     * Também desativa o usuário ao definir o campo 'active' como false.
     *
     * @param id O identificador único do usuário a ser excluído logicamente
     * @throws ResourceNotFoundException Se nenhum usuário for encontrado com o ID fornecido
     */
    public void softDeleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setDeletedAt(LocalDateTime.now());
        user.setActive(false); // Também desativa o usuário
        userRepository.save(user);
    }

    /**
     * Restaura um usuário que foi previamente desativado (soft delete).
     * Este método remove a marcação de exclusão e também reativa o usuário.
     *
     * @param id O identificador do usuário a ser restaurado
     * @throws ResourceNotFoundException Se nenhum usuário com o ID fornecido for encontrado
     * @throws IllegalStateException Se o usuário não estiver em estado de exclusão
     */
    public void restoreUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (user.getDeletedAt() != null) {
            user.setDeletedAt(null); // Remove o soft delete
            user.setActive(true); // Reativa o usuário
            userRepository.save(user);
        } else {
            throw new IllegalStateException("User is not deleted");
        }
    }

    /**
     * Atualiza o último login do usuário.
     * 
     * @param name o nome do usuário cujo último login será atualizado.
     * @throws ResourceNotFoundException se o usuário não for encontrado.
     */
    @Override
    public void updateLastLogin(String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    /**
     * Encontra e retorna uma lista de usuários por função.
     *
     * @param role a função dos usuários a serem encontrados
     * @return uma lista de usuários com a função especificada
     * @throws ResourceNotFoundException se o papel for nulo
     */
    @Override
    public List<UserGetResponse> findUsersByRole(UserRole role) {
        if (role == null) {
            throw new ResourceNotFoundException("Admin role not found");
        }
        List<User> users = userRepository.findByRole(role);
        return mapper.toUserGetResponseList(users);
    }

    /**
     * Obtém o papel do usuário pelo nome de usuário.
     *
     * @param name o nome de usuário
     * @return o papel do usuário
     * @throws ResourceNotFoundException se o usuário não for encontrado
     */
    public UserRole getRoleByUserName(String name) {
        return userRepository.findByUsername(name)
                .map(User::getRole) 
                .orElseThrow(() -> new ResourceNotFoundException("User is not admin"));
    }
}
