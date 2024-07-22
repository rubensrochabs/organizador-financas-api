package com.organizador_financas_api.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class GastoDto {

	private Long idGasto;
	private Long idPessoa;
	private Integer idGastoCategoria;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dtEmissao;
	private BigDecimal nrValor;
	private String txDescricao;
}
