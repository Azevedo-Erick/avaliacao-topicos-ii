package br.unitins.topicosii.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento extends DefaultEntity{
	private float total;
	@OneToMany
	private List<RegistroPagavel> registros;
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<RegistroPagavel> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroPagavel> registros) {
		this.registros = registros;
	}
	
	
}
