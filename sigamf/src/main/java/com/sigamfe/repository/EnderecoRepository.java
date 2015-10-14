package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.Endereco;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface EnderecoRepository extends BaseRepository<Long, Endereco>, EnderecoRepositoryCustom {

}
