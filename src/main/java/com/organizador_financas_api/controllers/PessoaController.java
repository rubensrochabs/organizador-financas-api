package com.organizador_financas_api.controllers;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@GetMapping
	public ResponseEntity<List<PessoaDto>> listar() {
		List<PessoaDto> lsPessoaDto = pessoaService.listar();
		return ResponseEntity.ok(lsPessoaDto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PessoaDto> buscarPorId(@PathVariable final Long id) {
		PessoaDto pessoaDto = pessoaService.buscarPorId(id);
		return ResponseEntity.ok(pessoaDto);
	}

	@PostMapping
	public ResponseEntity<PessoaDto> incluir(@RequestBody final PessoaDto pessoaDto) {
		PessoaDto retornoPessoaDto = pessoaService.incluir(pessoaDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retornoPessoaDto.getId()).toUri();
		return ResponseEntity.created(uri).body(retornoPessoaDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PessoaDto> atualizar(@PathVariable final Long id, @RequestBody final PessoaDto pessoaDto) {
		PessoaDto retornoPessoaDto = pessoaService.atualizar(id, pessoaDto);
		return ResponseEntity.ok().body(retornoPessoaDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable final Long id) {
		pessoaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
