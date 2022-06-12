package br.unitins.topicosii.models;

public enum TIPOPIX {
	TELEFONE(1,"Telefone"), CPF(2,"CPF"),CNPJ(3,"CNPJ"),CHAVEALEATORIA(4,"Chave aleatória"),EMAIL(5, "Email");
	private int id;
	private String label;
	
	TIPOPIX(int id, String label) {
		this.id=id;
		this.label=label;
	}
	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static TIPOPIX valueOf(int id) {
		for (TIPOPIX tipoPix : TIPOPIX.values()) {
			if (tipoPix.getId() == id)
				return tipoPix;
		}
		return null;
	}
}
