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
import org.springframework.web.bind.annotation.RestController;

import com.chrono.request.releasetime.ReleaseTimePostRequest;
import com.chrono.request.releasetime.ReleaseTimePutRequest;
import com.chrono.response.releasetime.ReleaseTimeGetResponse;
import com.chrono.response.releasetime.ReleaseTimePostResponse;
import com.chrono.response.releasetime.ReleaseTimePutResponse;
import com.chrono.service.releasetime.ReleaseTimeService;

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
@RequestMapping("v1/hours")
@RequiredArgsConstructor
@Tag(name = "ReleaseTimeController", description = "Endpoints para gerenciamento de lancamento de hora")
public class ReleaseTimeController {

    private final ReleaseTimeService releaseTimeService;

    @Operation(summary = "Listar todos os lancamentos de horas", description = "Retorna uma lista de todos os lancamentos de horas cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de lancamentos de horas retornada com sucesso", content = @Content(schema = @Schema(implementation = ReleaseTimeGetResponse.class, type = "array")))
    })
    @GetMapping
    public ResponseEntity<List<ReleaseTimeGetResponse>> listAll() {
        return ResponseEntity.ok(releaseTimeService.findAllReleases());
    }

    @Operation(summary = "Buscar Lancamento de hora por ID", description = "Retorna um lancamento de hora com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lancamento de hora encontrado com sucesso", content = @Content(schema = @Schema(implementation = ReleaseTimeGetResponse.class))),
        @ApiResponse(responseCode = "404", description = "Lancamento de hora não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @GetMapping("{id}")
    public ResponseEntity<ReleaseTimeGetResponse> getReleaseTimeById(
        @Parameter(description = "ID do lancamento de hora a ser buscado", example = "1")
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(releaseTimeService.findReleaseTimeById(id));
    }

    @Operation(summary = "Atualizar Lancamento de hora", description = "Atualiza um lancamento de hora existente com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lancamento de hora atualizado com sucesso", content = @Content(schema = @Schema(implementation = ReleaseTimePutResponse.class))),
        @ApiResponse(responseCode = "404", description = "Lancamento de hora não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @PutMapping("{id}")
    public ResponseEntity<ReleaseTimePutResponse> updateReleaseTime(
        @Valid @RequestBody ReleaseTimePutRequest dto,
        @Parameter(description = "ID do lancamento de hora a ser atualizado", example = "1")
        @PathVariable Integer id
    ) {
        return ResponseEntity.ok(releaseTimeService.updateReleaseTime(dto, id));
    }

    @Operation(summary = "Criar lancamento de hora", description = "Cria um novo lancamento de hora com os dados fornecidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Lancamento de hora criado com sucesso", content = @Content(schema = @Schema(implementation = ReleaseTimePostResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ReleaseTimePostResponse> saveReleaseTime(
        @Valid @RequestBody ReleaseTimePostRequest postRequest
    ) throws URISyntaxException {
        ReleaseTimePostResponse response = releaseTimeService.saveReleaseTime(postRequest);
        return ResponseEntity.created(new URI("/v1/hours/" + response.id())).body(response);
    }

    @Operation(summary = "Excluir lancamento de hora", description = "Exclui um lancamento de hora com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Lancamento de hora excluído com sucesso", content = @Content),
        @ApiResponse(responseCode = "404", description = "Lancamento de hora não encontrado", content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro inesperado", content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReleaseTimeById(
        @Parameter(description = "ID do lancamento de hora a ser excluído", example = "1")
        @PathVariable Long id
    ) {
        releaseTimeService.deleteReleaseTimeById(id);
        return ResponseEntity.noContent().build();
    }
}