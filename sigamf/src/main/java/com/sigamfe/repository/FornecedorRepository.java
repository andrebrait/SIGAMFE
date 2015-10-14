package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.Fornecedor;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface FornecedorRepository extends BaseRepository<Integer, Fornecedor>, FornecedorRepositoryCustom {

}
