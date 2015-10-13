package com.sigamfe.model.enums.converter.base;

import java.util.Optional;

import javax.persistence.AttributeConverter;

import com.sigamfe.exception.EnumException;
import com.sigamfe.model.enums.interfaces.PersistentEnum;

/**
 * Classe PersistentEnumConverter. Converte um enum EntregaPedido em uma String
 * para persistência no Banco de Dados e vice-versa.
 */
public abstract class PersistentEnumConverter<E extends Enum<E> & PersistentEnum>
		implements AttributeConverter<E, String> {

	private Class<E> clazz;

	/**
	 * Construtor dummy para instanciar um novo PersistentEnumConverter
	 */
	public PersistentEnumConverter() {

	}

	/**
	 * Instancia um novo PersistentEnumConverter.
	 *
	 * @param clazz
	 *            A classe do Enum a ser convertido.
	 */
	protected PersistentEnumConverter(Class<E> clazz) {
		this.clazz = clazz;
	}

	@Override
	public String convertToDatabaseColumn(E attribute) {
		return Optional.ofNullable(attribute).map(E::getCodigo).orElse(null);
	}

	@Override
	public E convertToEntityAttribute(String dbData) {
		for (E ind : clazz.getEnumConstants()) {
			if (ind.getCodigo().equals(dbData)) {
				return ind;
			}
		}
		throw new EnumException(dbData);
	}
}