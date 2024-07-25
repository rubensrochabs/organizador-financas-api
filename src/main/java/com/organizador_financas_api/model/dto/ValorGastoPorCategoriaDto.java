package com.organizador_financas_api.model.dto;

import java.math.BigDecimal;

import com.organizador_financas_api.model.enums.GastoCategoriaEnum;

public class ValorGastoPorCategoriaDto {

	private Integer idGastoCategoria;
	private String nmGastoCategoria;
	private BigDecimal vltGasto;
	private BigDecimal percentualGasto;

	public ValorGastoPorCategoriaDto() {
		super();
	}
	
	public ValorGastoPorCategoriaDto(final GastoCategoriaEnum categoria) {
		super();
		this.idGastoCategoria = categoria.getCodigo();
		this.nmGastoCategoria = categoria.getNmNome();
		this.vltGasto = BigDecimal.ZERO;
		this.percentualGasto = BigDecimal.ZERO;
	}

	public Integer getIdGastoCategoria() {
		return idGastoCategoria;
	}

	public void setIdGastoCategoria(Integer idGastoCategoria) {
		this.idGastoCategoria = idGastoCategoria;
	}

	public String getNmGastoCategoria() {
		return nmGastoCategoria;
	}

	public void setNmGastoCategoria(String nmGastoCategoria) {
		this.nmGastoCategoria = nmGastoCategoria;
	}

	public BigDecimal getVltGasto() {
		return vltGasto;
	}

	public void setVltGasto(BigDecimal vltGasto) {
		this.vltGasto = vltGasto;
	}

	public BigDecimal getPercentualGasto() {
		return percentualGasto;
	}

	public void setPercentualGasto(BigDecimal percentualGasto) {
		this.percentualGasto = percentualGasto;
	}

	@Override
	public String toString() {
		return "ValorGastoPorCategoria [idGastoCategoria=" + idGastoCategoria + ", nmGastoCategoria=" + nmGastoCategoria
				+ ", vltGasto=" + vltGasto + ", percentualGasto=" + percentualGasto + "]";
	}
}
