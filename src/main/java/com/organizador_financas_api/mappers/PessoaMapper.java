package com.organizador_financas_api.mappers;

import org.springframework.stereotype.Component;

import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.entity.Pessoa;

@Component
public class PessoaMapper {

	public Pessoa mapear(final PessoaDto dto) {
		Pessoa entidade = new Pessoa();
		entidade.setIdPessoa(dto.getId());
		entidade.setNmNome(dto.getNmNome());
		
		return entidade;
	}
	
	public PessoaDto mapear(final Pessoa entidade) {
		PessoaDto dto = new PessoaDto();
		dto.setId(entidade.getIdPessoa());
		dto.setNmNome(entidade.getNmNome());
		
		return dto;
	}
}
