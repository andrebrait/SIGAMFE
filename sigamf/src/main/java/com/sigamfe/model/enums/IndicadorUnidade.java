package com.sigamfe.model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.Getter;

import com.sigamfe.exception.SIGAMFException;

/**
 * The Enum IndicadorUnidade.
 */
public enum IndicadorUnidade {

	UNIDADE("UN", "Unidade"), DUZIA("DZ", "Dúzia");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private IndicadorUnidade(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

	@Converter(autoApply = true)
	public static class IndicadorUnidadeConverter implements AttributeConverter<IndicadorUnidade, String> {

		@Override
		public String convertToDatabaseColumn(IndicadorUnidade attribute) {
			return attribute.getCodigo();
		}

		@Override
		public IndicadorUnidade convertToEntityAttribute(String dbData) {
			switch (dbData) {
			case "UN":
				return UNIDADE;
			case "DZ":
				return DUZIA;
			default:
				throw new SIGAMFException(new IllegalArgumentException(dbData + "não é um valor conhecido"));
			}
		}

	}
}
