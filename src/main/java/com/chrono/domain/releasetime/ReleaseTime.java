package com.chrono.domain.releasetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.chrono.domain.activity.Activity;
import com.chrono.domain.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "lancamento_hora")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReleaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_atividade")
    @NotNull(message = "A atividade é obrigatória.")
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @NotNull(message = "O usuário que lançou as horas é obrigatório.")
    private User user;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    @NotBlank(message = "A descrição da atividade é obrigatória.")
    private String description;
    
    @Column(name = "data_inicio")
    @NotNull(message = "A data de início é obrigatória.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;
    
    @Column(name = "data_fim")
    @NotNull(message = "A data de fim é obrigatória.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @Column(name = "data_registro")
    @NotNull(message = "A data de registro é obrigatória.")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:SS")
    private LocalDateTime registerDate;
}