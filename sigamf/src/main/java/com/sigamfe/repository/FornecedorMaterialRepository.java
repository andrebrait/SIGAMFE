package com.sigamfe.repository;

import com.sigamfe.model.FornecedorMaterial;
import com.sigamfe.model.FornecedorMaterial.FornecedorMaterialPK;
import com.sigamfe.repository.base.BaseRepository;

public interface FornecedorMaterialRepository
		extends BaseRepository<FornecedorMaterialPK, FornecedorMaterial>, FornecedorMaterialRepositoryCustom {

}
