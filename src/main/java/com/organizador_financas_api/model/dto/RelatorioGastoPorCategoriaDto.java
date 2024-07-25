package com.organizador_financas_api.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RelatorioGastoPorCategoriaDto {

	private Long idPessoa;
	private String nmPessoa;
	private LocalDate dtMinPeriodo;
	private LocalDate dtMaxPeriodo;
	private BigDecimal vlTotalGasto;
	private List<ValorGastoPorCategoriaDto> lsGastoPorCategoria;

	public RelatorioGastoPorCategoriaDto() {
		super();
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNmPessoa() {
		return nmPessoa;
	}

	public void setNmPessoa(String nmPessoa) {
		this.nmPessoa = nmPessoa;
	}

	public LocalDate getDtMinPeriodo() {
		return dtMinPeriodo;
	}

	public void setDtMinPeriodo(LocalDate dtMinPeriodo) {
		this.dtMinPeriodo = dtMinPeriodo;
	}

	public LocalDate getDtMaxPeriodo() {
		return dtMaxPeriodo;
	}

	public void setDtMaxPeriodo(LocalDate dtMaxPeriodo) {
		this.dtMaxPeriodo = dtMaxPeriodo;
	}

	public BigDecimal getVlTotalGasto() {
		return vlTotalGasto;
	}

	public void setVlTotalGasto(BigDecimal vlTotalGasto) {
		this.vlTotalGasto = vlTotalGasto;
	}

	public List<ValorGastoPorCategoriaDto> getLsGastoPorCategoria() {
		return lsGastoPorCategoria;
	}

	public void setLsGastoPorCategoria(List<ValorGastoPorCategoriaDto> lsGastoPorCategoria) {
		this.lsGastoPorCategoria = lsGastoPorCategoria;
	}

	@Override
	public String toString() {
		return "RelatorioGastoPorCategoriaDto [idPessoa=" + idPessoa + ", nmPessoa=" + nmPessoa + ", dtMinPeriodo="
				+ dtMinPeriodo + ", dtMaxPeriodo=" + dtMaxPeriodo + ", vlTotalGasto=" + vlTotalGasto
				+ ", lsGastoPorCategoria=" + lsGastoPorCategoria + "]";
	}
}
