package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.ClientePF;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface ClientePFRepository extends BaseRepository<Integer, ClientePF>, ClientePFRepositoryCustom {

	ClientePF findByCpf(String cpf);

	ClientePF findByNome(String nome);

	ClientePF findByRg(String rg);

}
