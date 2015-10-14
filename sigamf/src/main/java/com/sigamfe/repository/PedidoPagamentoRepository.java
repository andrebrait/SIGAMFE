package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.PedidoPagamento;
import com.sigamfe.model.PedidoPagamento.PedidoPagamentoPK;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface PedidoPagamentoRepository
		extends BaseRepository<PedidoPagamentoPK, PedidoPagamento>, PedidoPagamentoRepositoryCustom {

}
