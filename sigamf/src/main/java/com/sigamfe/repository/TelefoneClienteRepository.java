package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.TelefoneCliente;
import com.sigamfe.model.TelefoneCliente.TelefoneClientePK;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface TelefoneClienteRepository
		extends BaseRepository<TelefoneClientePK, TelefoneCliente>, TelefoneClienteRepositoryCustom {

}
