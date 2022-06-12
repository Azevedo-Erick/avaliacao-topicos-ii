package br.unitins.topicosii.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.topicosii.models.TIPOPIX;

@Converter(autoApply = true)
public class TipoPixConverter implements AttributeConverter<TIPOPIX, Integer>{
	@Override
	public Integer convertToDatabaseColumn(TIPOPIX tipoPix) {
		return tipoPix == null ? null : tipoPix.getId();
	}

	@Override
	public TIPOPIX convertToEntityAttribute(Integer id) {
		return TIPOPIX.valueOf(id);
	}
}
