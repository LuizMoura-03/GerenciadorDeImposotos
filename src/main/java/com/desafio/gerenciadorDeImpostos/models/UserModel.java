package com.desafio.gerenciadorDeImpostos.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.management.relation.Role;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
