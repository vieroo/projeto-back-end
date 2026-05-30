package com.raizesdonordeste.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
}