package com.organizador_financas_api.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class StandardResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime timestamp;
	private Integer nrStatus;
	private transient T dados;
	private String txMensagem;

	public StandardResponse() {
		super();
	}

	public StandardResponse(final HttpStatus status, final T dados) {
		super();
		this.timestamp = LocalDateTime.now();
		this.nrStatus = status.value();
		this.dados = dados;
		this.txMensagem = ""; // TODO: Apresentar mensagem amigável de acordo com status.
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getNrStatus() {
		return nrStatus;
	}

	public void setNrStatus(Integer nrStatus) {
		this.nrStatus = nrStatus;
	}

	public T getDados() {
		return dados;
	}

	public void setDados(T dados) {
		this.dados = dados;
	}

	public String getTxMensagem() {
		return txMensagem;
	}

	public void setTxMensagem(String txMensagem) {
		this.txMensagem = txMensagem;
	}

	@Override
	public String toString() {
		return "StandardResponse [timestamp=" + timestamp + ", nrStatus=" + nrStatus + ", dados=" + dados
				+ ", txMensagem=" + txMensagem + "]";
	}
}
