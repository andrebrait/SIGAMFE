package com.sigamfe.business.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.model.base.GenericEntityIface;

@Transactional(propagation = Propagation.SUPPORTS)
public abstract class AbstractBusiness<ID extends Serializable, E extends GenericEntityIface<ID>> implements BaseBusiness<ID, E> {

	public Optional<E> findById(ID id) {
		return getRepository().findById(id);
	}

	public E findOne(ID id) {
		return getRepository().findOne(id);
	}

	public List<E> findAll(List<ID> ids, Sort sort) {
		return getRepository().findAllById(ids, sort);
	}

	public Page<E> findAllById(Iterable<ID> ids, Pageable pageable) {
		return getRepository().findAllById(ids, pageable);
	}

	public E saveAndFlush(E entity) {
		return getRepository().saveAndFlush(entity);
	}

	public E save(E entity) {
		return getRepository().save(entity);
	}

	public List<E> save(List<E> entities) {
		return getRepository().save(entities);
	}

	public void deleteInBatch(Iterable<E> entities) {
		getRepository().deleteInBatch(entities);
	}

	public List<E> findAll(Sort sort) {
		return getRepository().findAll(sort);
	}

	public Page<E> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

}
