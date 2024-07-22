package com.organizador_financas_api.services;

import org.springframework.stereotype.Service;

import com.organizador_financas_api.model.dto.GastoDto;

@Service
public interface GastoService {

	public GastoDto buscarPorId(Long idGasto);

	public GastoDto incluir(GastoDto gastoDto);
	
	public GastoDto alterar(Long idGasto, GastoDto gastoDto);
	
	public void delete(Long idGasto);
}
