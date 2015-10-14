package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.Pedido;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface PedidoRepository extends BaseRepository<Integer, Pedido>, PedidoRepositoryCustom {
}
