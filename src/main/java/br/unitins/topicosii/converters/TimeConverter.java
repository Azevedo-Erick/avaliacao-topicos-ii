package br.unitins.topicosii.converters;

import java.time.LocalTime;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalTime.class)
public class TimeConverter implements Converter<LocalTime>{
	@Override
	public LocalTime getAsObject(FacesContext context, UIComponent component, String value) {
		
		return LocalTime.parse(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, LocalTime value) {
		return value.toString();
	}
}
