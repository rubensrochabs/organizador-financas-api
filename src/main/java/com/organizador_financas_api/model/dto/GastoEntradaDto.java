package com.organizador_financas_api.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class GastoEntradaDto {

	private Long idGasto;
	private Long idPessoa;
	private Integer idGastoCategoria;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dtEmissao;
	private BigDecimal nrValor;
	private String txDescricao;

	public GastoEntradaDto() {
		super();
	}

	public Long getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(Long idGasto) {
		this.idGasto = idGasto;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Integer getIdGastoCategoria() {
		return idGastoCategoria;
	}

	public void setIdGastoCategoria(Integer idGastoCategoria) {
		this.idGastoCategoria = idGastoCategoria;
	}

	public LocalDate getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(LocalDate dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	public BigDecimal getNrValor() {
		return nrValor;
	}

	public void setNrValor(BigDecimal nrValor) {
		this.nrValor = nrValor;
	}

	public String getTxDescricao() {
		return txDescricao;
	}

	public void setTxDescricao(String txDescricao) {
		this.txDescricao = txDescricao;
	}

	@Override
	public String toString() {
		return "GastoEntradaDto [idGasto=" + idGasto + ", idPessoa=" + idPessoa + ", idGastoCategoria=" + idGastoCategoria
				+ ", dtEmissao=" + dtEmissao + ", nrValor=" + nrValor + ", txDescricao=" + txDescricao + "]";
	}
}
