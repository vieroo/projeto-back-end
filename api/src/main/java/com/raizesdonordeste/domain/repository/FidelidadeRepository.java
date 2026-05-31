package com.raizesdonordeste.domain.repository;

import com.raizesdonordeste.domain.entity.Fidelidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FidelidadeRepository extends JpaRepository<Fidelidade, Long>{}