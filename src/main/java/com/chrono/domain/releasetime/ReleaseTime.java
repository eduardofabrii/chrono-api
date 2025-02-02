package com.chrono.domain.releasetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
import jakarta.validation.constraints.Size;
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
    @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres.")
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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime registerDate;



    // Constructors
    public ReleaseTime(@NotNull(message = "A atividade é obrigatória.") Activity activity,
            @NotNull(message = "O usuário que lançou as horas é obrigatório.") User user,
            @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres.") @NotBlank(message = "A descrição da atividade é obrigatória.") String description,
            @NotNull(message = "A data de início é obrigatória.") LocalDate startDate,
            @NotNull(message = "A data de fim é obrigatória.") LocalDate endDate) {
        this.activity = activity;
        this.user = user;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public ReleaseTime(@NotNull(message = "A atividade é obrigatória.") Activity activity,
            @NotNull(message = "O usuário que lançou as horas é obrigatório.") User user,
            @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres.") @NotBlank(message = "A descrição da atividade é obrigatória.") String description,
            @NotNull(message = "A data de início é obrigatória.") LocalDate startDate,
            @NotNull(message = "A data de fim é obrigatória.") LocalDate endDate, LocalDateTime registerDate) {
        this.activity = activity;
        this.user = user;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.registerDate = registerDate;
    }    

    
}