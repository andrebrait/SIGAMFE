package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.TelefoneFornecedor;
import com.sigamfe.model.TelefoneFornecedor.TelefoneFornecedorPK;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface TelefoneFornecedorRepository
		extends BaseRepository<TelefoneFornecedorPK, TelefoneFornecedor>, TelefoneFornecedorRepositoryCustom {

}
