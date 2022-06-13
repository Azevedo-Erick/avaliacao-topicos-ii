package br.unitins.topicosii.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.unitins.topicosii.models.Cidade;
import br.unitins.topicosii.respository.CidadeRepository;

@Named
@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter<Cidade>{
	@Override
	public Cidade getAsObject(FacesContext context, UIComponent component, String value) {
		CidadeRepository repo = new CidadeRepository();
		try {
			return repo.findById(Integer.parseInt(value));
			
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Cidade value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}
}
