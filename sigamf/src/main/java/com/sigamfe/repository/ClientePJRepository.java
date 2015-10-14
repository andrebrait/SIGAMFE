package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.ClientePJ;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface ClientePJRepository extends BaseRepository<Integer, ClientePJ>, ClientePJRepositoryCustom {

}
