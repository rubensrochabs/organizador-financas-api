package com.organizador_financas_api.model.dto;

public class PessoaDto {

	private Long id;
	private String nmNome;

	public PessoaDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNmNome() {
		return nmNome;
	}

	public void setNmNome(String nmNome) {
		this.nmNome = nmNome;
	}

	@Override
	public String toString() {
		return "PessoaDto [id=" + id + ", nmNome=" + nmNome + "]";
	}
}
