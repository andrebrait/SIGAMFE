package com.sigamfe.business.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sigamfe.model.base.BaseEntity;
import com.sigamfe.repository.base.BaseRepository;

public interface BaseBusiness<ID extends Serializable, E extends BaseEntity<ID>> extends Serializable {

	BaseRepository<ID, E> getRepository();

	public long count();

	public void delete(ID id);

	public void delete(List<E> entities);

	public void deleteAll();

	public void deleteAllInBatch();

	public void deleteInBatch(List<E> entities);

	public boolean exists(ID id);

	public List<E> findAll();

	public List<E> findAll(List<ID> ids, Sort sort);

	public Page<E> findAll(Pageable pageable);

	public List<E> findAll(Sort sort);

	public Page<E> findById(Iterable<ID> ids, Pageable pageable);

	public List<E> findById(List<ID> ids);

	public E findById(ID id);

	public E save(E entity);

	public List<E> save(List<E> entities);

}
