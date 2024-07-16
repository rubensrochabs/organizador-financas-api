package com.organizador_financas_api.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.organizador_financas_api.model.dto.StandardResponse;

public interface StandardController {

	/**
	 * Método responsável por retornar um ResponseEntity, passando um HttpStatus e
	 * um "response".<br>
	 * O parâmetro "response" é incluído na generalização "dados" de
	 * OrgFinanceiroResponse.
	 * 
	 * @param status   {@link HttpStatus}
	 * @param response
	 * @return
	 */
	default <T extends Object> ResponseEntity<StandardResponse<T>> retornarResponse(final HttpStatus status,
			final T response) {
		return ResponseEntity.status(status).body(new StandardResponse<T>(status, response));
	}

	/**
	 * Retorna um ResponseEntity sem conteúdo com sucesso.<br>
	 * Status: 204 No Content
	 * 
	 * @param void
	 * @return
	 */
	default <T extends Object> ResponseEntity<StandardResponse<T>> retornarSemConteudo() {
		return retornarResponse(HttpStatus.NO_CONTENT, null);
	}

	/**
	 * Retorna um ResponseEntity contendo uma lista com sucesso.<br>
	 * Status: 200 Ok
	 * 
	 * @param void
	 * @return
	 */
	default <T extends Object> ResponseEntity<StandardResponse<List<T>>> retornarSucesso(final List<T> response) {
		return !response.isEmpty() ? retornarResponse(HttpStatus.OK, response) : retornarSemConteudo();
	}

	/**
	 * Retorna um ResponseEntity contendo um objeto com sucesso.<br>
	 * Status: 200 Ok
	 * 
	 * @param void
	 * @return
	 */
	default <T extends Object> ResponseEntity<StandardResponse<T>> retornarSucesso(final T response) {
		return response != null ? retornarResponse(HttpStatus.OK, response) : retornarSemConteudo();
	}

	/**
	 * Retorna um ResponseEntity contendo um objeto que foi criado com sucesso.<br>
	 * Status: 201 Created
	 * 
	 * @param void
	 * @return
	 */
	default <T extends Object> ResponseEntity<StandardResponse<T>> retornarCriado(final T response) {
		return response != null ? retornarResponse(HttpStatus.CREATED, response) : retornarSemConteudo();
	}

}
