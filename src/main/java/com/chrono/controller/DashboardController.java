package com.chrono.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chrono.response.dashboard.DashboardResponse;
import com.chrono.service.dashboard.DashboardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/dashboard")
@RequiredArgsConstructor
@Tag(name = "DashboardController", description = "Endpoints para dados do painel de controle")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Obter dados do dashboard", description = "Recupera dados do dashboard incluindo contagens de projetos e informações de status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Dados do dashboard recuperados com sucesso", 
                    content = @Content(schema = @Schema(implementation = DashboardResponse.class))),
        @ApiResponse(responseCode = "500", description = "Erro do servidor", content = @Content)
    })
    @GetMapping()
    public ResponseEntity<DashboardResponse> getDashboardData() {
        return ResponseEntity.ok(dashboardService.getDashboardData());
    }
}
