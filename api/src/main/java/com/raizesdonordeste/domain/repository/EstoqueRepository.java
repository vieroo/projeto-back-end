package com.raizesdonordeste.domain.repository;

import com.raizesdonordeste.domain.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
    Optional<Estoque> findByProdutoIdAndUnidadeId(
            Long produtoId,
            Long unidadeId
    );
}