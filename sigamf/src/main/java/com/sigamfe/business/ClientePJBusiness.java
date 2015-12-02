package com.sigamfe.business;

import com.sigamfe.business.base.BaseBusiness;
import com.sigamfe.model.ClientePJ;

public interface ClientePJBusiness extends BaseBusiness<Integer, ClientePJ> {

	ClientePJ findByCnpj(String cnpj);

	ClientePJ findByNome(String nome);

}
