package com.raizesdonordeste.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;

    private String acao;

    private String entidade;

    private Long entidadeId;

    private LocalDateTime dataHora;
}