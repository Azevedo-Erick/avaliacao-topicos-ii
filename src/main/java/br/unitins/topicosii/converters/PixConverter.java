package br.unitins.topicosii.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.topicosii.models.Pix;
import br.unitins.topicosii.respository.PixRepository;
@FacesConverter(forClass = Pix.class)
public class PixConverter implements Converter<Pix>{
	@Override
	public Pix getAsObject(FacesContext context, UIComponent component, String value) {
		PixRepository repo = new PixRepository();
		try {
			return repo.findById(Integer.parseInt(value));
			
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Pix value) {
		return value.getId().toString();
	}
}
