package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.ImagemMaterial;
import com.sigamfe.model.Material;
import com.sigamfe.repository.ImagemMaterialRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ImagemMaterialBusinessImpl extends AbstractBusiness<Integer, ImagemMaterial>
		implements ImagemMaterialBusiness {

	private static final long serialVersionUID = -7724751921781615043L;

	@Getter
	@Autowired
	private ImagemMaterialRepository repository;

	@Override
	public ImagemMaterial findByMaterial(Material material) {
		return repository.findByMaterial(material);
	}
}
