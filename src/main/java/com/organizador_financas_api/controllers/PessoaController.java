package com.organizador_financas_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.dto.StandardResponse;
import com.organizador_financas_api.services.PessoaService;
import com.organizador_financas_api.utils.StandardController;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController implements StandardController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<StandardResponse<List<PessoaDto>>> listar() {
		List<PessoaDto> lsPessoaDto = pessoaService.listar();
		return retornarSucesso(lsPessoaDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<StandardResponse<PessoaDto>> buscarPorId(@PathVariable final Long id) {
		PessoaDto pessoaDto = pessoaService.buscarPorId(id);
		return retornarSucesso(pessoaDto);
	}

	@PostMapping
	public ResponseEntity<StandardResponse<PessoaDto>> incluir(@RequestBody final PessoaDto pessoaDto) {
		PessoaDto retornoPessoaDto = pessoaService.incluir(pessoaDto);
		return retornarCriado(retornoPessoaDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<StandardResponse<PessoaDto>> atualizar(@PathVariable final Long id,
			@RequestBody final PessoaDto pessoaDto) {
		PessoaDto retornoPessoaDto = pessoaService.atualizar(id, pessoaDto);
		return retornarSucesso(retornoPessoaDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<StandardResponse<Void>> delete(@PathVariable final Long id) {
		pessoaService.delete(id);
		return retornarSemConteudo();
	}
}
