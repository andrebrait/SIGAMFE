package com.sigamfe.repository;

import com.sigamfe.model.PedidoMaterial;
import com.sigamfe.model.PedidoMaterial.PedidoMaterialPK;
import com.sigamfe.repository.base.BaseRepository;

public interface PedidoMaterialRepository
		extends BaseRepository<PedidoMaterialPK, PedidoMaterial>, PedidoMaterialRepositoryCustom {

}
