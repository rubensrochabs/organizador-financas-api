package com.organizador_financas_api.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.organizador_financas_api.model.dto.ValorGastoPorCategoriaDto;
import com.organizador_financas_api.model.entity.Gasto;

@Repository
public interface GastoRepository {

	public Optional<Gasto> recuperar(Long id);

	public List<Gasto> recuperarLsPorIdPessoa(Long idPessoa, LocalDate dtMin, LocalDate dtMax);

	public List<ValorGastoPorCategoriaDto> recuperarLsVlGastoPorCategoria(Long idPessoa, LocalDate dtMin, LocalDate dtMax);

	public Long persistir(Gasto gasto);
	
	public void atualizar(Gasto gasto);
	
	public void delete(Long idGasto);
}
