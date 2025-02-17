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

import com.chrono.request.project.ProjectPostRequest;
import com.chrono.request.project.ProjectPutRequest;
import com.chrono.response.project.ProjectGetResponse;
import com.chrono.response.project.ProjectPostResponse;
import com.chrono.response.project.ProjectPutResponse;
import com.chrono.service.project.ProjectService;

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
@RequestMapping("v1/project")
@RequiredArgsConstructor
@Tag(name = "ProjectController", description = "Endpoints para gerenciamento de projetos")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Listar todos os projetos", description = "Retorna uma lista de todos os projetos cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de projetos retornada com sucesso", content = @Content(schema = @Schema(implementation = ProjectGetResponse.class, type = "array")))
    })
    @GetMapping
    public ResponseEntity<List<ProjectGetResponse>> listAll() {
        return ResponseEntity.ok(projectService.findAllProjects());
    }

    @Operation(summary = "Buscar projetos por nome", description = "Retorna uma lista de projetos que correspondem ao nome fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de projetos retornada com sucesso", content = @Content(schema = @Schema(implementation = ProjectGetResponse.class, type = "array"))),
        @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content)
    })
    @GetMapping("name")
    public ResponseEntity<List<ProjectGetResponse>> getProjectByName(
        @Parameter(description = "Nome do projeto a ser buscado", example = "Projeto Alpha")
        @RequestParam String name
    ) {
        return ResponseEntity.ok(projectService.findProjectByName(name));
    }

    @Operation(summary = "Buscar projeto por ID", description = "Retorna um projeto com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Projeto encontrado com sucesso", content = @Content(schema = @Schema(implementation = ProjectGetResponse.class))),
        @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<ProjectGetResponse> getProjectById(
        @Parameter(description = "ID do projeto a ser buscado", example = "1")
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(projectService.findProjectById(id));
    }

    @Operation(summary = "Atualizar projeto", description = "Atualiza um projeto existente com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Projeto atualizado com sucesso", content = @Content(schema = @Schema(implementation = ProjectPutResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content),
        @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<ProjectPutResponse> updateProject(
        @Valid @RequestBody ProjectPutRequest dto,
        @Parameter(description = "ID do projeto a ser atualizado", example = "1")
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(projectService.updateProject(id, dto));
    }

    @Operation(summary = "Criar projeto", description = "Cria um novo projeto com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso", content = @Content(schema = @Schema(implementation = ProjectPostResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProjectPostResponse> saveProject(@Valid @RequestBody ProjectPostRequest postRequest) throws URISyntaxException {
        ProjectPostResponse response = projectService.saveProject(postRequest);
        return ResponseEntity.created(new URI("/v1/project/" + response.id())).body(response);
    }

    @Operation(summary = "Excluir projeto", description = "Exclui um projeto com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Projeto excluído com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@Parameter(description = "ID do projeto a ser excluído", example = "1") @PathVariable Long id) {
        projectService.deleteProjectById(id);
        return ResponseEntity.noContent().build();
    }
}