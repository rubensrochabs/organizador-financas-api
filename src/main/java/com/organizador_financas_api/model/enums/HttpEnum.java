package com.organizador_financas_api.model.enums;

import org.springframework.http.HttpStatus;

public enum HttpEnum {

	HTTP_200(HttpStatus.OK, "Operação realizada com sucesso."),
	HTTP_201(HttpStatus.CREATED, "Recurso criado com sucesso."),
	HTTP_204(HttpStatus.OK, "Operação realizada com sucesso, mas não retornou conteúdo."),
	
	HTTP_400(HttpStatus.BAD_REQUEST, "Operação inválida."),

	HTTP_500(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno ao realizar a operação.");

	private HttpStatus status;
	private String mensagem;

	private HttpEnum(HttpStatus status, String mensagem) {
		this.status = status;
		this.mensagem = mensagem;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMensagem() {
		return mensagem;
	}
}
