package com.sigamfe.model.base;

import java.io.Serializable;

public interface GenericEntityIface<ID extends Serializable> {

	public abstract ID getId();

	public abstract void setId(ID id);

}
