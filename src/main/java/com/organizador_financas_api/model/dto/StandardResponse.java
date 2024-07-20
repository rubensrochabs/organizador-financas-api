package com.organizador_financas_api.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.organizador_financas_api.model.enums.HttpEnum;

public class StandardResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalDateTime timestamp;
	private Integer nrStatus;
	private String txMensagem;
	private T dados;

	public StandardResponse() {
		super();
	}

	public StandardResponse(final HttpEnum status, final T dados) {
		super();
		this.timestamp = LocalDateTime.now();
		this.nrStatus = status.getStatus().value();
		this.txMensagem = status.getMensagem();
		this.dados = dados;
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
