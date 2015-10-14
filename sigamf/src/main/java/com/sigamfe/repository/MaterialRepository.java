package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.Material;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface MaterialRepository extends BaseRepository<Integer, Material>, MaterialRepositoryCustom {

}
