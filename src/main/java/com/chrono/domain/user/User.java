package com.chrono.domain.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nome")
    @NotBlank(message = "O nome do usuario é obrigatório.")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "O email do usuario é obrigatório.")
    private String email;

    @Column(name = "senha")
    @NotBlank(message = "É obrigatório o usuario ter uma senha.")
    private String password;
    
    @Column(name = "data_criacao", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @CreationTimestamp
    private LocalDateTime creationDate;
    
    @Column(name = "ultimo_login")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime lastLogin;
    
    @Column(name = "perfil")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    // toString
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", creationDate="
                + creationDate + ", lastLogin=" + lastLogin + ", profileEnum=" + role + "]";
    }

    
    // Constructors
    public User(@NotBlank(message = "O nome do usuario é obrigatório.") String name,
            @NotBlank(message = "O email do usuario é obrigatório.") String email,
            @NotBlank(message = "É obrigatório o usuario ter uma senha.") String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.role = role;
    }

    // Extra Getters and Setters
    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
