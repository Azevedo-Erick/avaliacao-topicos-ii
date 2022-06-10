package br.unitins.topicosii.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.unitins.topicosii.models.Consultorio;
import br.unitins.topicosii.respository.ConsultorioRepository;

@Named
@FacesConverter(forClass = Consultorio.class)
public class ConsultorioConverter implements Converter<Consultorio> {

	@Override
	public Consultorio getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isBlank())
			return null;
		ConsultorioRepository repo = new ConsultorioRepository();
		try {
			return repo.findById(Integer.parseInt(value));

		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Consultorio value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
