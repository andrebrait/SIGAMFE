package com.sigamfe.repository;

import com.sigamfe.model.TelefoneCliente;
import com.sigamfe.model.TelefoneCliente.TelefoneClientePK;
import com.sigamfe.repository.base.BaseRepository;

public interface TelefoneClienteRepository
		extends BaseRepository<TelefoneClientePK, TelefoneCliente>, TelefoneClienteRepositoryCustom {

}
