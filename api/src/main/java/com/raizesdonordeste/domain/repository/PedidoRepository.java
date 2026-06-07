package com.raizesdonordeste.domain.repository;

import com.raizesdonordeste.domain.entity.Pedido;
import com.raizesdonordeste.domain.enums.CanalPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    List<Pedido> findByCanalPedido(CanalPedido canalPedido);
}