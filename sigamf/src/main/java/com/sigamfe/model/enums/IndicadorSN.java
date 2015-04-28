package com.sigamfe.model.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.Getter;

import com.sigamfe.exception.SIGAMFException;

/**
 * The Enum IndicadorSN.
 */
public enum IndicadorSN {

	SIM("S", "Sim"), NAO("N", "Não");

	@Getter
	private String codigo;

	@Getter
	private String label;

	private IndicadorSN(String codigo, String label) {
		this.codigo = codigo;
		this.label = label;
	}

	@Converter(autoApply = true)
	public static class IndicadorSNConverter implements AttributeConverter<IndicadorSN, String> {

		@Override
		public String convertToDatabaseColumn(IndicadorSN attribute) {
			return attribute.getCodigo();
		}

		@Override
		public IndicadorSN convertToEntityAttribute(String dbData) {
			switch (dbData) {
			case "S":
				return SIM;
			case "N":
				return NAO;
			default:
				throw new SIGAMFException(new IllegalArgumentException(dbData + "não é um valor conhecido"));
			}
		}

	}

}
