package com.sigamfe.business.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.model.base.BaseEntity;

@Transactional(propagation = Propagation.SUPPORTS)
public abstract class AbstractBusiness<ID extends Serializable, E extends BaseEntity<ID>> implements BaseBusiness<ID, E> {

	private static final long serialVersionUID = 7586458730414174725L;

	@Override
	public long count() {
		return getRepository().count();
	}

	@Override
	public void delete(ID id) {
		getRepository().delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(List<E> entities) {
		getRepository().delete(entities);
	}

	@Override
	public void deleteAll() {
		getRepository().deleteAll();
	}

	@Override
	public void deleteAllInBatch() {
		getRepository().deleteAllInBatch();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteInBatch(List<E> entities) {
		getRepository().deleteInBatch(entities);
	}

	@Override
	public boolean exists(ID id) {
		return getRepository().exists(id);
	}

	@Override
	public List<E> findAll() {
		return getRepository().findAll();
	}

	@Override
	public List<E> findAll(List<ID> ids, Sort sort) {
		return getRepository().findAllById(ids, sort);
	}

	@Override
	public Page<E> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	@Override
	public List<E> findAll(Sort sort) {
		return getRepository().findAll(sort);
	}

	@Override
	public Optional<E> findById(ID id) {
		return getRepository().findById(id);
	}

	@Override
	public Page<E> findById(Iterable<ID> ids, Pageable pageable) {
		return getRepository().findAllById(ids, pageable);
	}

	@Override
	public List<E> findById(List<ID> ids) {
		return getRepository().findAll(ids);
	}

	@Override
	public E findOne(ID id) {
		return getRepository().findOne(id);
	}

	@Override
	public E save(E entity) {
		return getRepository().save(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<E> save(List<E> entities) {
		return getRepository().save(entities);
	}

	@Override
	public E saveAndFlush(E entity) {
		return getRepository().saveAndFlush(entity);
	}

}
