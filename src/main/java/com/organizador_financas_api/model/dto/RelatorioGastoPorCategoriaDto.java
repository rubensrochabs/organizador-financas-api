package com.organizador_financas_api.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(dtMaxPeriodo, dtMinPeriodo, idPessoa, lsGastoPorCategoria, nmPessoa, vlTotalGasto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatorioGastoPorCategoriaDto other = (RelatorioGastoPorCategoriaDto) obj;
		return Objects.equals(dtMaxPeriodo, other.dtMaxPeriodo) && Objects.equals(dtMinPeriodo, other.dtMinPeriodo)
				&& Objects.equals(idPessoa, other.idPessoa)
				&& Objects.equals(lsGastoPorCategoria, other.lsGastoPorCategoria)
				&& Objects.equals(nmPessoa, other.nmPessoa) && Objects.equals(vlTotalGasto, other.vlTotalGasto);
	}

	@Override
	public String toString() {
		return "RelatorioGastoPorCategoriaDto [idPessoa=" + idPessoa + ", nmPessoa=" + nmPessoa + ", dtMinPeriodo="
				+ dtMinPeriodo + ", dtMaxPeriodo=" + dtMaxPeriodo + ", vlTotalGasto=" + vlTotalGasto
				+ ", lsGastoPorCategoria=" + lsGastoPorCategoria + "]";
	}
}
