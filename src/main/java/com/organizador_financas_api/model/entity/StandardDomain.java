package com.organizador_financas_api.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Todas as entidades que representam uma tabela no banco de dados devem
 * extender esta classe
 */
@MappedSuperclass
public abstract class StandardDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "dt_inclusao", updatable = false)
	private LocalDateTime dataInclusao;

	@Column(name = "dt_alteracao")
	private LocalDateTime dataAlteracao;

	public StandardDomain() {
		super();
		this.dataInclusao = LocalDateTime.now();
		this.dataAlteracao = LocalDateTime.now();
	}

	public LocalDateTime getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDateTime dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public String toString() {
		return "StandardDomain [dataInclusao=" + dataInclusao + ", dataAlteracao=" + dataAlteracao + "]";
	}
}
