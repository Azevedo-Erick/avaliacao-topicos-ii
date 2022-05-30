package br.unitins.topicosii.models;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class DefaultEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*
	 * @Version private Integer version;
	 * 
	 * public Integer getVersion() { return version; }
	 * 
	 * public void setVersion(Integer version) { this.version = version; }
	 */
	private LocalDateTime dataCadastro;

	private LocalDateTime dataAlteracao;

	@PrePersist
	private void gerarDataCadastro() {
		dataCadastro = LocalDateTime.now();
	}

	@PreUpdate
	private void gerarDataAlteracao() {
		dataAlteracao = LocalDateTime.now();
	}
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
