package com.sigamfe.repository;

import com.sigamfe.model.MovimentoEstoque;
import com.sigamfe.repository.base.BaseRepository;

public interface MovimentoEstoqueRepository
		extends BaseRepository<Long, MovimentoEstoque>, MovimentoEstoqueRepositoryCustom {

}
