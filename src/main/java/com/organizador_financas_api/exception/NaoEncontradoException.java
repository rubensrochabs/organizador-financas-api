package com.organizador_financas_api.exception;

public class NaoEncontradoException extends OrganizadorFinanceiroException {
	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "O recurso não foi encontrado.";

	public NaoEncontradoException() {
		super(MENSAGEM_PADRAO);
	}

	public NaoEncontradoException(Object detalhe) {
		super(MENSAGEM_PADRAO, detalhe);
	}

	public NaoEncontradoException(String mensagem, Object detalhe) {
		super(mensagem, detalhe);
	}

	public NaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	@Override
	public String toString() {
		return this.getClass().getName() + " [erro=" + getErro() + "]";
	}
}
