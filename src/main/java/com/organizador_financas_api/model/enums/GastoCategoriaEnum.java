package com.organizador_financas_api.model.enums;

public enum GastoCategoriaEnum {

	CONTA		(1, "Conta"),
	ALIMENTACAO	(2, "Alimentação"),
	TRANSPORTE	(3, "Transporte"),
	SAUDE		(4, "Saúde"),
	LAZER		(5, "Lazer"),
	PETS		(6, "Pets"),
	OUTROS		(7, "Outros");

	private Integer codigo;
	private String nmNome;

	private GastoCategoriaEnum(final Integer codigo, final String nmNome) {
		this.codigo = codigo;
		this.nmNome = nmNome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNmNome() {
		return nmNome;
	}

	public static GastoCategoriaEnum getByCodigo(final Integer codigo) {
		for(GastoCategoriaEnum categoria : values()) {
			if(categoria.getCodigo().equals(codigo)) return categoria;
		}

		throw new IllegalArgumentException(
				String.format("Código de categoria de gasto inválido! | código: %d", codigo));

//		return Stream.of(values())
//			.filter(categoria -> categoria.getCodigo().equals(codigo))
//			.findFirst()
//			.orElseThrow(() -> new IllegalArgumentException(
//				String.format("Código de categoria de gasto inválido! | código: %d", codigo)));
	}
	
	public static String getNmNomeByCodigo(final Integer codigo) {
		return getByCodigo(codigo).getNmNome();
	}
}
