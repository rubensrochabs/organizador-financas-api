package com.organizador_financas_api.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.organizador_financas_api.model.dto.GastoEntradaDto;
import com.organizador_financas_api.model.dto.GastoSaidaDto;

@Service
public interface GastoService {

	public GastoSaidaDto buscarPorId(Long idGasto);

	public List<GastoSaidaDto> listarPorIdPessoa(Long idPessoa, LocalDate dtMin, LocalDate dtMax);

	public GastoEntradaDto incluir(GastoEntradaDto gastoDto);
	
	public GastoEntradaDto alterar(Long idGasto, GastoEntradaDto gastoDto);
	
	public void delete(Long idGasto);
}
