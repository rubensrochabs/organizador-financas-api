package com.organizador_financas_api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pessoa")
public class Pessoa extends StandardDomain {
	private static final long serialVersionUID = 7173950161701440088L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long idPessoa;

	@Column(name = "nm_nome")
	private String nmNome;

	public Pessoa() {
		super();
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNmNome() {
		return nmNome;
	}

	public void setNmNome(String nmNome) {
		this.nmNome = nmNome;
	}

	@Override
	public String toString() {
		return "Pessoa [idPessoa=" + idPessoa + ", nmNome=" + nmNome + "]";
	}
}
