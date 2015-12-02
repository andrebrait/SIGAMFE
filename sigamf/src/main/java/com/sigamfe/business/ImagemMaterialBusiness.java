package com.sigamfe.business;

import com.sigamfe.business.base.BaseBusiness;
import com.sigamfe.model.ImagemMaterial;
import com.sigamfe.model.Material;

public interface ImagemMaterialBusiness extends BaseBusiness<Integer, ImagemMaterial> {

	ImagemMaterial findByMaterial(Material material);

}
