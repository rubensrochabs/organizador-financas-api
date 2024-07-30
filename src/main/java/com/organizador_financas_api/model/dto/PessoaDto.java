package com.organizador_financas_api.model.dto;

import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(id, nmNome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaDto other = (PessoaDto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nmNome, other.nmNome);
	}

	@Override
	public String toString() {
		return "PessoaDto [id=" + id + ", nmNome=" + nmNome + "]";
	}
}
