package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.System;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface SystemRepository extends BaseRepository<Byte, System>, SystemRepositoryCustom {

}
