package com.organizador_financas_api.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_gasto")
public class Gasto extends StandardDomain {
	private static final long serialVersionUID = -5104440885014201432L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gasto")
	private Long idGasto;

	@Column(name = "id_pessoa")
	private Long idPessoa;

	@Column(name = "id_gasto_categoria")
	private Integer idGastoCategoria;

	@Column(name = "dt_emissao")
	private LocalDate dtEmissao;

	@Column(name = "nr_valor")
	private BigDecimal nrValor;

	@Column(name = "tx_descricao")
	private String txDescricao;

	public Gasto() {
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
		return "Gasto [idGasto=" + idGasto + ", idPessoa=" + idPessoa + ", idGastoCategoria=" + idGastoCategoria
				+ ", dtEmissao=" + dtEmissao + ", nrValor=" + nrValor + ", txDescricao=" + txDescricao + "]";
	}
}
