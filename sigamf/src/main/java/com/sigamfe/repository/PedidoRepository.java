package com.sigamfe.repository;

import com.sigamfe.model.Pedido;
import com.sigamfe.repository.base.BaseRepository;

public interface PedidoRepository extends BaseRepository<Integer, Pedido>, PedidoRepositoryCustom {
}
