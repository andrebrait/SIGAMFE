package com.sigamfe.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.ClientePJ;
import com.sigamfe.model.TelefoneCliente;
import com.sigamfe.repository.ClientePJRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClientePJBusinessImpl extends AbstractBusiness<Integer, ClientePJ> implements ClientePJBusiness {

	private static final long serialVersionUID = -8736679327188728842L;

	@Getter
	@Autowired
	private ClientePJRepository repository;

	@Autowired
	private TelefoneClienteBusiness telefoneClienteBusiness;

	@Autowired
	private EnderecoBusiness enderecoBusiness;

	@Transactional
	@Override
	public ClientePJ save(ClientePJ entity) {
		entity.setEndereco(enderecoBusiness.save(entity.getEndereco()));
		List<TelefoneCliente> tc = entity.getTelefones();
		entity.setTelefones(null);
		entity = super.save(entity);
		if (tc != null) {
			for (TelefoneCliente t : tc) {
				t.setCliente(entity);
			}
			tc = telefoneClienteBusiness.save(tc);
		}
		entity.setTelefones(tc);
		return entity;
	}

	@Override
	public ClientePJ findByCnpj(String cnpj) {
		return repository.findByCnpj(cnpj);
	}

	@Override
	public ClientePJ findByNome(String nome) {
		return repository.findByNome(nome);
	}
}
