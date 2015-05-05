package com.sigamfe.repository.base;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sigamfe.model.base.BaseEntity;

/**
 * The Interface BaseRepository.
 *
 * @param <E>
 *            O tipo da Entidade
 * @param <ID>
 *            O tipo do campo ID da entidade
 */
@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable> extends JpaRepository<E, ID> {

	/**
	 * Returns a {@link List} of entities sorted by the given option.
	 *
	 * @param pageable
	 * @return a page of entities
	 */
	List<E> findAllById(Iterable<ID> ids, Sort sort);

	/**
	 * Returns a {@link Page} of entities meeting the paging restriction
	 * provided in the {@code Pageable} object, filtered by the given ids.
	 *
	 * @param pageable
	 * @return a page of entities
	 */
	Page<E> findAllById(Iterable<ID> ids, Pageable pageable);

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	Optional<E> findById(ID id);

}
