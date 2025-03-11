package com.chrono.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chrono.domain.user.UserRole;
import com.chrono.request.user.UserPostRequest;
import com.chrono.request.user.UserPutRequest;
import com.chrono.response.user.UserGetResponse;
import com.chrono.response.user.UserPostResponse;
import com.chrono.response.user.UserPutResponse;
import com.chrono.service.user.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/user")
@RequiredArgsConstructor
@Tag(name = "UserController", description = "Endpoints para gerenciamento de usuários") 
public class UserController {

    private final UserService userService;

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso", content = @Content(schema = @Schema(implementation = UserGetResponse.class, type = "array")))
    })
    @GetMapping
    public ResponseEntity<List<UserGetResponse>> listAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @Operation(summary = "Buscar usuários por nome", description = "Retorna uma lista de usuários que correspondem ao nome fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso", content = @Content(schema = @Schema(implementation = UserGetResponse.class, type = "array"))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @GetMapping("name")
    public ResponseEntity<List<UserGetResponse>> getUserByName(
        @Parameter(description = "Nome do usuário a ser buscado", example = "João Silva")
        @RequestParam String name
    ) {
        return ResponseEntity.ok(userService.findUserByName(name));
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UserGetResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<UserGetResponse> getUserById(
        @Parameter(description = "ID do usuário a ser buscado", example = "1")
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza um usuário existente com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(schema = @Schema(implementation = UserPutResponse.class))),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<UserPutResponse> updateUser(
        @Valid @RequestBody UserPutRequest dto,
        @Parameter(description = "ID do usuário a ser atualizado", example = "1")
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @Operation(summary = "Criar usuário", description = "Cria um novo usuário com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(schema = @Schema(implementation = UserPostResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserPostResponse> saveUser(
        @Valid @RequestBody UserPostRequest postRequest
    ) throws URISyntaxException {
        UserPostResponse response = userService.saveUser(postRequest);
        return ResponseEntity.created(new URI("/v1/user/" + response.id())).body(response);
    }

    @Operation(summary = "Excluir usuário", description = "Exclui um usuário com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(
        @Parameter(description = "ID do usuário a ser excluído", example = "1")
        @PathVariable Long id
    ) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
        }

    @Operation(summary = "Exclusão lógica de usuário", description = "Marca um usuário como excluído sem removê-lo fisicamente do banco de dados")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário excluído logicamente com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        })
    @DeleteMapping("soft/{id}")
        public ResponseEntity<String> softDeleteUser(@Parameter(description = "ID do usuário a ser excluído logicamente", 
            required = true) @PathVariable Integer id) {
                userService.softDeleteUser(id);
                return ResponseEntity.ok("User soft deleted successfully");
    }

    @Operation(summary = "Restaurar usuário", description = "Restaura um usuário previamente desativado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário restaurado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Usuário não foi deletado anteriormente"),
    })
    @PutMapping("restore/{id}")
    public ResponseEntity<String> restoreUser(
        @Parameter(description = "ID do usuário a ser restaurado", required = true)
        @PathVariable Integer id) {
        userService.restoreUser(id);
        return ResponseEntity.ok("User restored successfully");
    }

    @Operation(summary = "Listar usuários administradores", description = "Retorna uma lista de usuários com a função de administrador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários administradores retornada com sucesso", content = @Content(schema = @Schema(implementation = UserGetResponse.class, type = "array"))),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário administrador encontrado", content = @Content)
    })
    @GetMapping("admin_users")
    public ResponseEntity<List<UserGetResponse>> getAdminUsers() {
        List<UserGetResponse> adminUsers = userService.findUsersByRole(UserRole.ADMIN);
        return ResponseEntity.ok(adminUsers);
    }
}