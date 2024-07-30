package com.organizador_financas_api.services.impl;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.organizador_financas_api.model.dto.GastoEntradaDto;
import com.organizador_financas_api.model.dto.GastoSaidaDto;
import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.dto.RelatorioGastoPorCategoriaDto;
import com.organizador_financas_api.model.dto.ValorGastoPorCategoriaDto;
import com.organizador_financas_api.model.entity.Gasto;
import com.organizador_financas_api.model.entity.Pessoa;
import com.organizador_financas_api.utils.JsonUtils;

public class GastoServiceTestObj {

	private Gasto gasto;
	private GastoSaidaDto gastoSaidaDto;
	private Pessoa pessoa;
	private PessoaDto pessoaDto;
	private List<Gasto> lsGasto;
	private List<GastoSaidaDto> lsGastoSaidaDto;
	private GastoEntradaDto gastoEntradaDto;
	private List<ValorGastoPorCategoriaDto> lsValorGastoPorCategoriaDto;
	private RelatorioGastoPorCategoriaDto relatorioGastoPorCategoriaDto;

	public GastoServiceTestObj() {
		gasto = JsonUtils.paraObjViaArquivoJson("mock/gasto.json", new TypeReference<Gasto>() {
		});
		gastoSaidaDto = JsonUtils.paraObjViaArquivoJson("mock/gastoSaidaDto.json", new TypeReference<GastoSaidaDto>() {
		});
		pessoa = JsonUtils.paraObjViaArquivoJson("mock/pessoa.json", new TypeReference<Pessoa>() {
		});
		pessoaDto = JsonUtils.paraObjViaArquivoJson("mock/pessoaDto.json", new TypeReference<PessoaDto>() {
		});
		lsGasto = JsonUtils.paraObjViaArquivoJson("mock/lsGasto.json", new TypeReference<List<Gasto>>() {
		});
		lsGastoSaidaDto = JsonUtils.paraObjViaArquivoJson("mock/lsGastoSaidaDto.json",
				new TypeReference<List<GastoSaidaDto>>() {
				});
		gastoEntradaDto = JsonUtils.paraObjViaArquivoJson("mock/gastoEntradaDto.json",
				new TypeReference<GastoEntradaDto>() {
				});
		lsValorGastoPorCategoriaDto = JsonUtils.paraObjViaArquivoJson("mock/lsValorGastoPorCategoriaDto.json",
				new TypeReference<List<ValorGastoPorCategoriaDto>>() {
				});
		relatorioGastoPorCategoriaDto = JsonUtils.paraObjViaArquivoJson("mock/relatorioGastoPorCategoriaDto.json",
				new TypeReference<RelatorioGastoPorCategoriaDto>() {
				});
	}

	public Gasto getGasto() {
		return gasto;
	}

	public GastoSaidaDto getGastoSaidaDto() {
		return gastoSaidaDto;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public PessoaDto getPessoaDto() {
		return pessoaDto;
	}

	public List<Gasto> getLsGasto() {
		return lsGasto;
	}

	public List<GastoSaidaDto> getLsGastoSaidaDto() {
		return lsGastoSaidaDto;
	}

	public GastoEntradaDto getGastoEntradaDto() {
		return gastoEntradaDto;
	}

	public List<ValorGastoPorCategoriaDto> getLsValorGastoPorCategoriaDto() {
		return lsValorGastoPorCategoriaDto;
	}

	public RelatorioGastoPorCategoriaDto getRelatorioGastoPorCategoriaDto() {
		return relatorioGastoPorCategoriaDto;
	}
}
