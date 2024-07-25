package com.organizador_financas_api.mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.dto.RelatorioGastoPorCategoriaDto;
import com.organizador_financas_api.model.dto.ValorGastoPorCategoriaDto;

@Component
public class RelatorioMapper {

	public RelatorioGastoPorCategoriaDto mapear(final PessoaDto pessoaDto, final LocalDate dtMin, final LocalDate dtMax,
			final BigDecimal vltGastoPeriodo, final List<ValorGastoPorCategoriaDto> lsVlGastoPorCategoria) {
		RelatorioGastoPorCategoriaDto relatorio = new RelatorioGastoPorCategoriaDto();

		relatorio.setIdPessoa(pessoaDto.getId());
		relatorio.setNmPessoa(pessoaDto.getNmNome());
		relatorio.setDtMinPeriodo(dtMin);
		relatorio.setDtMaxPeriodo(dtMax);
		relatorio.setVlTotalGasto(vltGastoPeriodo);
		relatorio.setLsGastoPorCategoria(lsVlGastoPorCategoria);

		return relatorio;
	}

}
