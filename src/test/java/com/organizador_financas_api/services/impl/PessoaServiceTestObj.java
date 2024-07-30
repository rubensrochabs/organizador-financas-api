package com.organizador_financas_api.services.impl;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.entity.Pessoa;
import com.organizador_financas_api.utils.JsonUtils;

public class PessoaServiceTestObj {

	private Pessoa pessoa;
	private PessoaDto pessoaDto;
	private List<Pessoa> lsPessoa;
	private List<PessoaDto> lsPessoaDto;

	public PessoaServiceTestObj() {
		pessoa = JsonUtils.paraObjViaArquivoJson("mock/pessoa.json", new TypeReference<Pessoa>() {
		});
		pessoaDto = JsonUtils.paraObjViaArquivoJson("mock/pessoaDto.json", new TypeReference<PessoaDto>() {
		});
		lsPessoa = JsonUtils.paraObjViaArquivoJson("mock/lsPessoa.json", new TypeReference<List<Pessoa>>() {
		});
		lsPessoaDto = JsonUtils.paraObjViaArquivoJson("mock/lsPessoaDto.json", new TypeReference<List<PessoaDto>>() {
		});
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public PessoaDto getPessoaDto() {
		return pessoaDto;
	}

	public List<Pessoa> getLsPessoa() {
		return lsPessoa;
	}

	public List<PessoaDto> getLsPessoaDto() {
		return lsPessoaDto;
	}
}
