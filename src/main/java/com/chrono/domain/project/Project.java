package com.chrono.domain.project;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.chrono.domain.user.User;
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
}
