package com.chrono.domain.project;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.chrono.domain.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "projeto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Integer id;
    
    @Column(name = "nome")
    @NotBlank(message = "O nome do projeto é obrigatório.")
    private String name;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    @NotBlank(message = "A descrição do projeto é obrigatória.")
    private String description;
    
    @Column(name = "data_inicio")
    @NotNull(message = "A data de início é obrigatória.")
    private LocalDate startDate;
    
    @Column(name = "data_fim")
    @NotNull(message = "A data de fim é obrigatória.")
    private LocalDate endDate;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do projeto é obrigatório.")
    private ProjectStatus status;
    
    @ManyToOne(fetch = FetchType.EAGER) // Força o carregamento do usuário completo
    @JoinColumn(name = "id_usuario_responsavel")
    @NotNull(message = "É obrigatório ter um usuario dono do projeto")
    private User responsible;
    
    @Column(name = "data_criacao", updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:SS")
    private LocalDateTime creationDate;
    
    @Column(name = "prioridade")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "A prioridade do projeto é obrigatória.")
    private ProjectPriority priority;
    
    // Constructors
    public Project(Integer id, @NotBlank(message = "O nome do projeto é obrigatório.") String name,
            @NotBlank(message = "A descrição do projeto é obrigatório.") String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}