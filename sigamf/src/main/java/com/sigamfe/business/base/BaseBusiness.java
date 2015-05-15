package com.sigamfe.business.base;

import java.io.Serializable;

import com.sigamfe.model.base.GenericEntityIface;
import com.sigamfe.repository.base.BaseRepository;

public interface BaseBusiness<ID extends Serializable, E extends GenericEntityIface<ID>> {

	BaseRepository<E, ID> getRepository();

}
