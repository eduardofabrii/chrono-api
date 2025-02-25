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

import com.chrono.request.activity.ActivityPostRequest;
import com.chrono.request.activity.ActivityPutRequest;
import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.activity.ActivityPostResponse;
import com.chrono.response.activity.ActivityPutResponse;
import com.chrono.service.activity.ActivityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/activity")
@RequiredArgsConstructor
@Tag(name = "ActivityController", description = "Controller responsável pelas operações de CRUD das Atividades.")
public class ActivityController {

    private final ActivityService activityService;

    @Operation(summary = "Listar todas as atividades", description = "Busca uma lista de todas as atividades.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de atividades buscada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityGetResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<ActivityGetResponse>> listAll() {
        return ResponseEntity.ok(activityService.findAllActivities());
    }

    @Operation(summary = "Encontrar atividade pelo nome", description = "Busca uma lista de atividades pelo nome.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atividades buscadas com sucesso pelo nome", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityGetResponse.class))),
        @ApiResponse(responseCode = "404", description = "Atividades não encontradas", content = @Content)
    })
    @GetMapping("name")
    public ResponseEntity<List<ActivityGetResponse>> getActivityByName(@Parameter(description = "Nome da atividade a ser encontrada", example = "Aplicação de gestão de projetos") @RequestParam String name) {
        return ResponseEntity.ok(activityService.findActivityByName(name));
    }

    @Operation(summary = "Encontrar atividade pelo ID", description = "Busca uma atividade pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atividade buscada com sucesso pelo ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityGetResponse.class))),
        @ApiResponse(responseCode = "404", description = "Atividade não encontrada", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<ActivityGetResponse> getActivityById(@Parameter(description = "ID da atividade a ser encontrada", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(activityService.findActivityById(id));
    }

    @Operation(summary = "Atualizar atividade", description = "Atualizar uma atividade existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atividade atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityPutResponse.class))),
        @ApiResponse(responseCode = "404", description = "Atividade não encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<ActivityPutResponse> updateActivity(@Valid @RequestBody ActivityPutRequest dto, @Parameter(description = "ID da atividade a ser atualizada", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(activityService.updateActivity(id, dto));
    }

    @Operation(summary = "Salvar atividade", description = "Salvar uma nova atividade.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Atividade salva com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityPostResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", 
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<ActivityPostResponse> saveActivity(@Valid @RequestBody ActivityPostRequest postRequest) throws URISyntaxException {
        ActivityPostResponse response = activityService.saveActivity(postRequest);
        return ResponseEntity.created(new URI("/v1/activity/" + response.id())).body(response);
    }

    @Operation(summary = "Excluir atividade", description = "Excluir uma atividade pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Atividade excluída com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Atividade não encontrada", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteActivityById(@Parameter(description = "ID da atividade a ser excluída", example = "1") @PathVariable Long id) {
        activityService.deleteActivityById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar atividades por ID do projeto", description = "Busca uma lista de atividades pelo ID do projeto.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atividades buscadas com sucesso pelo ID do projeto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActivityGetResponse.class))),
        @ApiResponse(responseCode = "404", description = "Atividades não encontradas", content = @Content)
    })
    @GetMapping("project/{projectId}")
    public ResponseEntity<List<ActivityGetResponse>> listarAtividadesPorProjetoId(@Parameter(description = "ID do projeto a ser encontrado", example = "1") @PathVariable Integer projectId) {
        List<ActivityGetResponse> atividades = activityService.findActivitiesByProjectId(projectId);
        return ResponseEntity.ok(atividades);
    }
}
