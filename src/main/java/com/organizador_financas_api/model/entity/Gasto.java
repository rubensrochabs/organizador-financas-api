package com.organizador_financas_api.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_gasto")
@Data
@NoArgsConstructor
public class Gasto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGasto;
	private Long idPessoa;
	private Integer idGastoCategoria;
	private LocalDate dtEmissao;
	private BigDecimal nrValor;
	private String txDescricao;
}