package com.organizador_financas_api.controllers;

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

import com.organizador_financas_api.model.dto.GastoEntradaDto;
import com.organizador_financas_api.model.dto.GastoSaidaDto;
import com.organizador_financas_api.model.dto.StandardResponse;
import com.organizador_financas_api.services.GastoService;
import com.organizador_financas_api.utils.StandardController;

@RestController
@RequestMapping(value = "/gasto")
public class GastoController implements StandardController {

	@Autowired
	private GastoService gastoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<StandardResponse<GastoSaidaDto>> buscarGastoById(@PathVariable final Long id) {
		GastoSaidaDto gastoSaidaDto = gastoService.buscarPorId(id);
		return retornarSucesso(gastoSaidaDto);
	}
	}

	@PostMapping
	public ResponseEntity<StandardResponse<GastoEntradaDto>> incluirGasto(@RequestBody final GastoEntradaDto gastoDto) {
		GastoEntradaDto retornoGastoDto = gastoService.incluir(gastoDto);
		return retornarCriado(retornoGastoDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<StandardResponse<GastoEntradaDto>> atualizarGasto(@PathVariable final Long id,
			@RequestBody final GastoEntradaDto gastoDto) {
		GastoEntradaDto retornoGastoDto = gastoService.alterar(id, gastoDto);
		return retornarSucesso(retornoGastoDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<StandardResponse<Void>> deletarGasto(@PathVariable final Long id) {
		gastoService.delete(id);
		return retornarSemConteudo();
	}
}
