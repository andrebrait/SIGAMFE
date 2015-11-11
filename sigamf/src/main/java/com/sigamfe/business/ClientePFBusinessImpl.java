package com.sigamfe.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.ClientePF;
import com.sigamfe.model.TelefoneCliente;
import com.sigamfe.repository.ClientePFRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClientePFBusinessImpl extends AbstractBusiness<Integer, ClientePF> implements ClientePFBusiness {

	private static final long serialVersionUID = 224328374334868349L;

	@Getter
	@Autowired
	private ClientePFRepository repository;

	@Autowired
	private TelefoneClienteBusiness telefoneClienteBusiness;

	@Autowired
	private EnderecoBusiness enderecoBusiness;

	@Transactional
	@Override
	public ClientePF save(ClientePF entity) {
		entity.setEndereco(enderecoBusiness.save(entity.getEndereco()));
		List<TelefoneCliente> tc = entity.getTelefones();
		entity.setTelefones(null);
		entity = super.save(entity);
		for (TelefoneCliente t : tc) {
			t.setCliente(entity);
		}
		tc = telefoneClienteBusiness.save(tc);
		entity.setTelefones(tc);
		return entity;
	}

}
