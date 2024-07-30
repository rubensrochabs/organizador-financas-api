package com.organizador_financas_api.exception;

import java.io.Serializable;

public class StandardErro implements Serializable {
	private static final long serialVersionUID = 2991551096988565287L;

	private String mensagem;
	private transient Object detalhe;

	public StandardErro() {
		this(null, null);
	}

	public StandardErro(String mensagem, Object detalhe) {
		super();
		this.mensagem = mensagem;
		this.detalhe = detalhe;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(Object detalhe) {
		this.detalhe = detalhe;
	}

	@Override
	public String toString() {
		return "StandardErro [mensagem=" + mensagem + ", detalhe=" + detalhe + "]";
	}
}
