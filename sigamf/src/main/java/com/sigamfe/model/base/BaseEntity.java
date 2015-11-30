package com.sigamfe.model.base;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.google.common.base.Objects;

import javafx.beans.property.Property;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;

/**
 * A Interface BaseEntity. Todas as entidades do sistema DEVEM implementar esta
 * interface.
 *
 * @param <ID>
 *            O tipo do ID da entidade
 */
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Map<String, ? extends Property<?>> propertyMap = createPropertyMap();

	private HashMap<String, ? extends Property<?>> createPropertyMap() {
		HashMap<String, Property<?>> map = new HashMap<>();
		try {
			for (PropertyDescriptor pd : Introspector.getBeanInfo(getClass(), BaseEntity.class)
					.getPropertyDescriptors()) {
				Method m = pd.getReadMethod();
				if (m != null && StringUtils.startsWith(m.getName(), "get")) {
					String propertyName = m.getName().substring(3, 4).toLowerCase() + m.getName().substring(4);
					if (m.getReturnType().equals(String.class)) {
						map.put(propertyName,
								new JavaBeanStringPropertyBuilder().bean(this).name(propertyName).build());
					} else {
						map.put(propertyName,
								new JavaBeanObjectPropertyBuilder<>().bean(this).name(propertyName).build());
					}
				}
			}
		} catch (IntrospectionException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		return map;
	}

	/**
	 * On field change.
	 *
	 * @param <T>
	 *            the generic type
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	protected <T> void onFieldChange(String name, T value) {
		Property<T> prop = getPropertyByName(name);
		if (prop != null) {
			try {
				Object old = PropertyUtils.getProperty(this, name);
				if (!Objects.equal(value, old)) {
					FieldUtils.writeField(this, name, value, true);
					prop.setValue(value);
				}
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Gets the property by name.
	 *
	 * @param name
	 *            the name
	 * @return the property by name
	 */
	@SuppressWarnings("unchecked")
	public <T> Property<T> getPropertyByName(String name) {
		return (Property<T>) propertyMap.get(name);
	}

	/**
	 * Gets the property value.
	 *
	 * @param <T>
	 *            the generic type
	 * @param name
	 *            the name
	 * @return the property value
	 */
	public <T> T getPropertyValue(String name) {
		Property<T> property = getPropertyByName(name);
		return property != null ? property.getValue() : null;
	}

	/**
	 * Retorna o ID da entidade
	 *
	 * @return o id
	 */
	public abstract ID getId();

	/**
	 * Atribui o ID à entidade.
	 *
	 * @param id
	 *            O novo ID.
	 */
	public abstract void setId(ID id);

	/**
	 * Retorna a versão da entidade
	 *
	 * @return A versão
	 */
	public abstract Long getVersion();

	/**
	 * Atribui a versão à entidade
	 *
	 * @param version
	 *            a nova versão
	 */
	public abstract void setVersion(Long version);

	/**
	 * Copy properties.
	 *
	 * @param destination
	 *            the destination
	 */
	public void copyProperties(BaseEntity<?> destination) {
		try {
			PropertyUtils.copyProperties(destination, this);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
