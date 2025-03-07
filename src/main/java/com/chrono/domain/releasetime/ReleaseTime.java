package com.chrono.domain.releasetime;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.chrono.domain.activity.Activity;
import com.chrono.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @ManyToOne
    @JoinColumn(name = "id_atividade")
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "data_inicio")
    private LocalDateTime startDate;
    
    @Column(name = "data_fim")
    private LocalDateTime endDate;

    @Column(name = "data_registro")
    @CreationTimestamp
    private LocalDateTime registerDate;
}