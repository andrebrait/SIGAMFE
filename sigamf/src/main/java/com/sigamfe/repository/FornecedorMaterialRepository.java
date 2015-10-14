package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.FornecedorMaterial;
import com.sigamfe.model.FornecedorMaterial.FornecedorMaterialPK;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface FornecedorMaterialRepository
		extends BaseRepository<FornecedorMaterialPK, FornecedorMaterial>, FornecedorMaterialRepositoryCustom {

}
