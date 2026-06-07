package com.raizesdonordeste.domain.repository;

import com.raizesdonordeste.domain.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository
        extends JpaRepository<Auditoria, Long> {
}
