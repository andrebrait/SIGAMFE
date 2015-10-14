package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.PedidoEstado;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface PedidoEstadoRepository extends BaseRepository<Integer, PedidoEstado>, PedidoEstadoRepositoryCustom {

}
