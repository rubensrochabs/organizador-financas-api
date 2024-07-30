package com.organizador_financas_api.exception;

public class ServicoException extends OrganizadorFinanceiroException {
	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "Erro interno ao realizar operação.";

	public ServicoException() {
		super(MENSAGEM_PADRAO);
	}

	public ServicoException(Object detalhe) {
		super(MENSAGEM_PADRAO, detalhe);
	}

	public ServicoException(String mensagem, Object detalhe) {
		super(mensagem, detalhe);
	}

	public ServicoException(String mensagem) {
		super(mensagem);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [erro=" + getErro() + "]";
	}
}
