package com.sigamfe.repository;

import com.sigamfe.model.PedidoPagamento;
import com.sigamfe.model.PedidoPagamento.PedidoPagamentoPK;
import com.sigamfe.repository.base.BaseRepository;

public interface PedidoPagamentoRepository
		extends BaseRepository<PedidoPagamentoPK, PedidoPagamento>, PedidoPagamentoRepositoryCustom {

}
