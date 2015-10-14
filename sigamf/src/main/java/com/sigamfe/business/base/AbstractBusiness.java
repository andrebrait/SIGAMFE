package com.sigamfe.business.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sigamfe.configuration.ApplicationConfiguration;
import com.sigamfe.model.base.AuditableBaseEntity;
import com.sigamfe.model.base.BaseEntity;

@Transactional(propagation = Propagation.SUPPORTS)
public abstract class AbstractBusiness<ID extends Serializable, E extends BaseEntity<ID>>
		implements BaseBusiness<ID, E> {

	private static final long serialVersionUID = 7586458730414174725L;

	@Override
	public long count() {
		return getRepository().count();
	}

	@Override
	@Transactional
	public void delete(ID id) {
		getRepository().delete(id);
	}

	@Override
	@Transactional
	public void delete(List<E> entities) {
		getRepository().delete(entities);
	}

	@Override
	@Transactional
	public void deleteAll() {
		getRepository().deleteAll();
	}

	@Override
	@Transactional
	public void deleteAllInBatch() {
		getRepository().deleteAllInBatch();
	}

	@Override
	@Transactional
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
	public Page<E> findById(Iterable<ID> ids, Pageable pageable) {
		return getRepository().findAllById(ids, pageable);
	}

	@Override
	public List<E> findById(List<ID> ids) {
		return getRepository().findAll(ids);
	}

	@Override
	public E findById(ID id) {
		return getRepository().findOne(id);
	}

	private void setAuditInfo(E entity, LocalDateTime dataAtual) {
		if (entity instanceof AuditableBaseEntity) {
			AuditableBaseEntity<ID> audit = (AuditableBaseEntity<ID>) entity;
			if (audit.getUsuarioCriacao() == null) {
				audit.setUsuarioCriacao(ApplicationConfiguration.usuarioLogado);
			}
			if (audit.getDataCriacao() == null) {
				audit.setDataCriacao(dataAtual);
			}
			audit.setUsuarioAtualizacao(ApplicationConfiguration.usuarioLogado);
			audit.setDataAtualizacao(dataAtual);
		}
	}

	@Override
	@Transactional
	public E save(E entity) {
		setAuditInfo(entity, LocalDateTime.now());
		return getRepository().save(entity);
	}

	@Override
	@Transactional
	public List<E> save(List<E> entities) {
		LocalDateTime dataAtual = LocalDateTime.now();
		for (E entity : entities) {
			setAuditInfo(entity, dataAtual);
		}
		return getRepository().save(entities);
	}

}
