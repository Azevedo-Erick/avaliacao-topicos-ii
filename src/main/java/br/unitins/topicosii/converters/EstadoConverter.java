package br.unitins.topicosii.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.topicosii.models.Estado;
import br.unitins.topicosii.respository.EstadoRepository;

@FacesConverter(forClass = Estado.class)
public class EstadoConverter implements Converter<Estado>{
	@Override
	public Estado getAsObject(FacesContext context, UIComponent component, String value) {
		EstadoRepository repo = new EstadoRepository();
		try {
			return repo.findById(Integer.parseInt(value));
			
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Estado value) {
		return value.getId().toString();
	}
}
