package com.sigamfe.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sigamfe.model.Usuario;
import com.sigamfe.model.Usuario_;
import com.sigamfe.model.enums.IndicadorSN;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

	@Autowired
	private EntityManager em;

	@Override
	public List<Usuario> findAllAtivos() {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		Metamodel metamodel = em.getMetamodel();
		EntityType<Usuario> metaType = metamodel.entity(Usuario.class);
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);

		Root<Usuario> root = criteriaQuery.from(metaType);

		criteriaQuery.where(criteriaBuilder.equal(root.get(Usuario_.ativo), IndicadorSN.SIM));

		TypedQuery<Usuario> query = em.createQuery(criteriaQuery);

		return query.getResultList();
	}

}
