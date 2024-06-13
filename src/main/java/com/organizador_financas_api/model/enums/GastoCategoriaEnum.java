package com.organizador_financas_api.model.enums;

public enum GastoCategoriaEnum {

	CONTA(1),
	ALIMENTAÇÃO(2),
	TRANSPORTE(3),
	SAÚDE(4),
	LAZER(5),
	PETS(6),
	OUTROS(7);

	private Integer codigo;

	private GastoCategoriaEnum(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}
}
