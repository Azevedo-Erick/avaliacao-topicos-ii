package br.unitins.topicosii.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;



@Entity
public class Pix extends Pagamento{
	private String chavePix;
	@Enumerated(EnumType.ORDINAL)
	private TIPOPIX tipopix;
	public String getChavePix() {
		return chavePix;
	}
	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}
	public TIPOPIX getTipopix() {
		return tipopix;
	}
	public void setTipopix(TIPOPIX tipopix) {
		this.tipopix = tipopix;
	}
	
	
}
