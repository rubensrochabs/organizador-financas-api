package com.organizador_financas_api.mappers;

import org.springframework.stereotype.Component;

import com.organizador_financas_api.model.dto.GastoEntradaDto;
import com.organizador_financas_api.model.dto.GastoSaidaDto;
import com.organizador_financas_api.model.entity.Gasto;
import com.organizador_financas_api.model.enums.GastoCategoriaEnum;

@Component
public class GastoMapper {

	public Gasto mapear(final GastoEntradaDto dto) {
		Gasto entidade = new Gasto();

		entidade.setIdGasto(dto.getIdGasto());
		entidade.setIdPessoa(dto.getIdPessoa());
		entidade.setIdGastoCategoria(dto.getIdGastoCategoria());
		entidade.setDtEmissao(dto.getDtEmissao());
		entidade.setNrValor(dto.getNrValor());
		entidade.setTxDescricao(dto.getTxDescricao());

		return entidade;
	}

	public GastoEntradaDto mapear(final Gasto entidade) {
		GastoEntradaDto dto = new GastoEntradaDto();

		dto.setIdGasto(entidade.getIdGasto());
		dto.setIdPessoa(entidade.getIdPessoa());
		dto.setIdGastoCategoria(entidade.getIdGastoCategoria());
		dto.setDtEmissao(entidade.getDtEmissao());
		dto.setNrValor(entidade.getNrValor());
		dto.setTxDescricao(entidade.getTxDescricao());

		return dto;
	}

	public GastoSaidaDto mapear(final Gasto entidade, final String nmPessoa) {
		GastoSaidaDto dto = new GastoSaidaDto();

		dto.setIdGasto(entidade.getIdGasto());
		dto.setIdPessoa(entidade.getIdPessoa());
		dto.setNmPessoa(nmPessoa);
		dto.setIdGastoCategoria(entidade.getIdGastoCategoria());
		dto.setNmGastoCategoria(GastoCategoriaEnum.getByCodigo(entidade.getIdGastoCategoria()).getNmNome());
		dto.setDtEmissao(entidade.getDtEmissao());
		dto.setNrValor(entidade.getNrValor());
		dto.setTxDescricao(entidade.getTxDescricao());

		return dto;
	}
}
