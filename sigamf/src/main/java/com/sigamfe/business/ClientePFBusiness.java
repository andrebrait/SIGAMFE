package com.sigamfe.business;

import com.sigamfe.business.base.BaseBusiness;
import com.sigamfe.model.ClientePF;

public interface ClientePFBusiness extends BaseBusiness<Integer, ClientePF> {

	ClientePF findByCpf(String cpf);

	ClientePF findByNome(String nome);

	ClientePF findByRg(String rg);

}
