package com.organizador_financas_api.exception;

public class OrganizadorFinanceiroException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final StandardErro erro;

	public OrganizadorFinanceiroException() {
		super();
		this.erro = new StandardErro();
	}

	public OrganizadorFinanceiroException(String mensagem, Object detalhe) {
		super(mensagem);
		this.erro = new StandardErro(mensagem, detalhe);
	}

	public OrganizadorFinanceiroException(String mensagem) {
		super(mensagem);
		this.erro = new StandardErro(mensagem, null);
	}

	public StandardErro getErro() {
		return erro;
	}

	@Override
	public String toString() {
		return "OrganizadorFinanceiroException [erro=" + erro + "]";
	}
}
