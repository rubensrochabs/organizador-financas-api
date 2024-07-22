package com.organizador_financas_api.mappers;

import org.springframework.stereotype.Component;

import com.organizador_financas_api.model.dto.GastoDto;
import com.organizador_financas_api.model.entity.Gasto;

@Component
public class GastoMapper {

	public Gasto mapear(final GastoDto dto) {
		Gasto entidade = new Gasto();

		entidade.setIdGasto(dto.getIdGasto());
		entidade.setIdPessoa(dto.getIdPessoa());
		entidade.setIdGastoCategoria(dto.getIdGastoCategoria());
		entidade.setDtEmissao(dto.getDtEmissao());
		entidade.setNrValor(dto.getNrValor());
		entidade.setTxDescricao(dto.getTxDescricao());

		return entidade;
	}

	public GastoDto mapear(final Gasto entidade) {
		GastoDto dto = new GastoDto();

		dto.setIdGasto(entidade.getIdGasto());
		dto.setIdPessoa(entidade.getIdPessoa());
		dto.setIdGastoCategoria(entidade.getIdGastoCategoria());
		dto.setDtEmissao(entidade.getDtEmissao());
		dto.setNrValor(entidade.getNrValor());
		dto.setTxDescricao(entidade.getTxDescricao());

		return dto;
	}
}
