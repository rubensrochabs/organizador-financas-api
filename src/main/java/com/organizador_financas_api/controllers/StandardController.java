package com.organizador_financas_api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.organizador_financas_api.model.enums.HttpEnum;

public interface StandardController {

	/**
	 * Método responsável por retornar um ResponseEntity, passando um HttpStatus e
	 * um "response".<br>
	 * O parâmetro "response" é incluído na generalização "dados" de
	 * OrgFinanceiroResponse.
	 * 
	 * @param httpEnum {@link HttpEnum}
	 * @param response
	 * @return ResponseEntity
	 */
	default <T extends Object> ResponseEntity<StandardResponse<T>> retornarResponse(final HttpEnum httpEnum,
			final T response) {
		return ResponseEntity.status(httpEnum.getStatus()).body(new StandardResponse<T>(httpEnum, response));
	}

	/**
	 * Retorna um ResponseEntity sem conteúdo com sucesso.<br>
	 * Status: 204 No Content
	 * 
	 * @param void
	 * @return ResponseEntity
	 */
	default <T extends Object> ResponseEntity<StandardResponse<T>> retornarSemConteudo() {
		return retornarResponse(HttpEnum.HTTP_204, null);
	}

	/**
	 * Retorna um ResponseEntity contendo uma lista com sucesso.<br>
	 * Status: 200 Ok
	 * 
	 * @return ResponseEntity
	 */
	default <T extends Object> ResponseEntity<StandardResponse<List<T>>> retornarSucesso(final List<T> response) {
		return !response.isEmpty() ? retornarResponse(HttpEnum.HTTP_200, response) : retornarSemConteudo();
	}

	/**
	 * Retorna um ResponseEntity contendo um objeto com sucesso.<br>
	 * Status: 200 Ok
	 * 
	 * @return ResponseEntity
	 */
	default <T extends Object> ResponseEntity<StandardResponse<T>> retornarSucesso(final T response) {
		return response != null ? retornarResponse(HttpEnum.HTTP_200, response) : retornarSemConteudo();
	}

	/**
	 * Retorna um ResponseEntity contendo um objeto que foi criado com sucesso.<br>
	 * Status: 201 Created
	 * 
	 * @return ResponseEntity
	 */
	default <T extends Object> ResponseEntity<StandardResponse<T>> retornarCriado(final T response) {
		return response != null ? retornarResponse(HttpEnum.HTTP_201, response) : retornarSemConteudo();
	}

}
