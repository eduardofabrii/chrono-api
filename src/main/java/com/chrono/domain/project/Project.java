package com.chrono.domain.project;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.chrono.domain.user.User;
import com.chrono.response.user.UserGetResponseToProject;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    private String name;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "data_inicio")
    private LocalDate startDate;
    
    @Column(name = "data_fim")
    private LocalDate endDate;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario_responsavel")
    private User responsible;
    
    @Column(name = "data_criacao", updatable = false)
    @CreationTimestamp
    private LocalDateTime creationDate;
    
    @Column(name = "prioridade")
    @Enumerated(EnumType.STRING)
    private ProjectPriority priority;

    // Constructors
    public Project(String name, String description, LocalDate startDate, LocalDate endDate, ProjectStatus status, User responsible, ProjectPriority priority) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.responsible = responsible;
        this.priority = priority;
    }

    // Getters and Setters
    public void setResponsibleToOnlyProject(UserGetResponseToProject responsible) {
        this.responsible = new User(responsible.id(), responsible.name(), responsible.email());
    }
}
