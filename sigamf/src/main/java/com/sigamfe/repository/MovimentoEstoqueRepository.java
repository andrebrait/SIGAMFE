package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.MovimentoEstoque;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface MovimentoEstoqueRepository
		extends BaseRepository<Long, MovimentoEstoque>, MovimentoEstoqueRepositoryCustom {

}
