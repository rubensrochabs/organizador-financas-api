package com.organizador_financas_api.exception;

public class ValidacaoException extends OrganizadorFinanceiroException {
	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "Operação inválida.";

	public ValidacaoException() {
		super(MENSAGEM_PADRAO);
	}

	public ValidacaoException(Object detalhe) {
		super(MENSAGEM_PADRAO, detalhe);
	}

	public ValidacaoException(String mensagem, Object detalhe) {
		super(mensagem, detalhe);
	}

	public ValidacaoException(String mensagem) {
		super(mensagem);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [erro=" + getErro() + "]";
	}
}
