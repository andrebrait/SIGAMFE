package com.sigamfe.repository;

import org.springframework.stereotype.Repository;

import com.sigamfe.model.PedidoMaterial;
import com.sigamfe.model.PedidoMaterial.PedidoMaterialPK;
import com.sigamfe.repository.base.BaseRepository;

@Repository
public interface PedidoMaterialRepository
		extends BaseRepository<PedidoMaterialPK, PedidoMaterial>, PedidoMaterialRepositoryCustom {

}
