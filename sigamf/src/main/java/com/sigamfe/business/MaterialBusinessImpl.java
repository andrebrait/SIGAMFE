package com.sigamfe.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sigamfe.business.base.AbstractBusiness;
import com.sigamfe.model.ImagemMaterial;
import com.sigamfe.model.Material;
import com.sigamfe.repository.MaterialRepository;

import lombok.Getter;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MaterialBusinessImpl extends AbstractBusiness<Integer, Material> implements MaterialBusiness {

	private static final long serialVersionUID = 6468880826031183852L;

	@Getter
	@Autowired
	private MaterialRepository repository;

	@Getter
	@Autowired
	private ImagemMaterialBusiness imagemMaterialBusiness;

	@Override
	public Material save(Material entity) {
		ImagemMaterial imagem = entity.getImagem();
		entity.setImagem(null);
		Material returnEntity = super.save(entity);
		if (imagem != null) {
			imagem.setMaterial(returnEntity);
			entity.setImagem(imagemMaterialBusiness.save(imagem));
		}
		return entity;
	}

	@Override
	public Material findByCodigo(Integer codigo) {
		return repository.findByCodigo(codigo);
	}

	@Override
	public Material findByDescricao(String codigo) {
		return repository.findByDescricao(codigo);
	}
}
