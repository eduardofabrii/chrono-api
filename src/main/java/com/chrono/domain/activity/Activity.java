package com.chrono.domain.activity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.chrono.domain.project.Project;
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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "atividade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_projeto")
    private Project project;
    
    @Column(name = "nome")
    private String name;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "data_inicio")
    private LocalDate startDate;
    
    @Column(name = "data_fim")
    private LocalDate endDate;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ActivityStatus status;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario_responsavel")
    private User responsible;
    
    @Column(name = "data_criacao", updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;


    // Constructors
    public Activity(@NotNull(message = "O id do projeto é obrigatório.") Project project,
            @NotBlank(message = "O nome da atividade é obrigatória.") String name,
            @NotBlank(message = "A descrição do projeto é obrigatória.") String description,
            @NotNull(message = "A data de início é obrigatória.") LocalDate startDate,
            @NotNull(message = "A data de fim é obrigatória.") LocalDate endDate,
            @NotNull(message = "O status da atividade é obrigatório.") ActivityStatus status,
            @NotNull(message = "É obrigatório ter um usuario dono do projeto") User responsible) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.responsible = responsible;
    }    
}
