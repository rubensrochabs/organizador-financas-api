package com.organizador_financas_api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.organizador_financas_api.controllers.StandardResponse;
import com.organizador_financas_api.model.enums.HttpEnum;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ServicoException.class)
	public ResponseEntity<StandardResponse<StandardErro>> handleException(final ServicoException e,
			final WebRequest request) {
		return errorResponse(HttpEnum.HTTP_500, e.getErro());
	}

//	@ExceptionHandler(ValidacaoException.class)
//	public ResponseEntity<StandardResponse<StandardErro>> handleException(final ValidacaoException e,
//			final WebRequest request) {
//		return errorResponse(HttpEnum.HTTP_400, e.getErro());
//	}

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<StandardResponse<StandardErro>> handleException(final NaoEncontradoException e,
			final WebRequest request) {
		return errorResponse(HttpEnum.HTTP_204, e.getErro());
	}

	private ResponseEntity<StandardResponse<StandardErro>> errorResponse(final HttpEnum httpEnum,
			final StandardErro erro) {
		return ResponseEntity.status(httpEnum.getStatus()).body(new StandardResponse<>(httpEnum, erro));
	}
}
