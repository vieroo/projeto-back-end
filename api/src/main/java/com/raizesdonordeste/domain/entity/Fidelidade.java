package com.raizesdonordeste.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fidelidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Fidelidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int pontos;

    @OneToOne
    @JoinColumn(name = "cliehte_id", nullable = false)
    private Usuario cliente;
}
