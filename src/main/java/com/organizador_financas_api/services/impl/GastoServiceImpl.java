package com.organizador_financas_api.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizador_financas_api.exception.NaoEncontradoException;
import com.organizador_financas_api.exception.OrganizadorFinanceiroException;
import com.organizador_financas_api.exception.ServicoException;
import com.organizador_financas_api.mappers.GastoMapper;
import com.organizador_financas_api.mappers.RelatorioMapper;
import com.organizador_financas_api.model.dto.GastoEntradaDto;
import com.organizador_financas_api.model.dto.GastoSaidaDto;
import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.dto.RelatorioGastoPorCategoriaDto;
import com.organizador_financas_api.model.dto.ValorGastoPorCategoriaDto;
import com.organizador_financas_api.model.entity.Gasto;
import com.organizador_financas_api.model.enums.GastoCategoriaEnum;
import com.organizador_financas_api.repositories.GastoRepository;
import com.organizador_financas_api.services.GastoService;
import com.organizador_financas_api.services.PessoaService;

@Service
public class GastoServiceImpl implements GastoService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private GastoRepository gastoRepository;

	@Autowired
	private GastoMapper gastoMapper;

	@Autowired
	private RelatorioMapper relatorioMapper;

	@Override
	public GastoSaidaDto buscarPorId(final Long idGasto) {
		try {
			logger.info("[01 - Recuperando Gasto | idGasto: {}]", idGasto);
			Gasto gasto = recuperarGasto(idGasto);

			logger.info("[02 - Recuperando Pessoa | idPessoa: {}]", gasto.getIdPessoa());
			PessoaDto pessoaDto = pessoaService.buscarPorId(gasto.getIdPessoa());

			logger.info("[03 - Mapeando resposta]");
			return gastoMapper.mapear(gasto, pessoaDto.getNmNome());
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (RuntimeException e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	private Gasto recuperarGasto(final Long idGasto) {
		return gastoRepository.recuperar(idGasto)
				.orElseThrow(() -> new NaoEncontradoException("Gasto não encontrado! | idGasto: " + idGasto));
	}

	@Override
	public List<GastoSaidaDto> listarPorIdPessoa(final Long idPessoa, final LocalDate dtMin, final LocalDate dtMax) {
		try {
			logger.info("[01 - Listando gastos por Pessoa | idPessoa: {}]", idPessoa);
			List<Gasto> lsGastos = gastoRepository.recuperarLsPorIdPessoa(idPessoa, dtMin, dtMax);

			logger.info("[02 - Recuperando Pessoa | idPessoa: {}]", idPessoa);
			PessoaDto pessoaDto = pessoaService.buscarPorId(idPessoa);

			logger.info("[03 - Mapeando resposta]");
			List<GastoSaidaDto> lsGastoDto = lsGastos.stream()
					.map(gasto -> gastoMapper.mapear(gasto, pessoaDto.getNmNome())).collect(Collectors.toList());

			return lsGastoDto;
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (RuntimeException e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	@Override
	public GastoEntradaDto incluir(final GastoEntradaDto gastoDto) {
		try {
			logger.info("[01 - Incluindo Gasto]");
			Long idGasto = gastoRepository.persistir(gastoMapper.mapear(gastoDto));

			logger.info("[02 - Mapeando resposta]");
			gastoDto.setIdGasto(idGasto);
			return gastoDto;
		} catch (RuntimeException e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	@Override
	public GastoEntradaDto alterar(final Long idGasto, final GastoEntradaDto gastoDto) {
		try {
			logger.info("[01 - Validando se existe Gasto | idGasto: {}]", idGasto);
			recuperarGasto(idGasto);

			logger.info("[02 - Atualizando Gasto | idGasto: {}]", idGasto);
			gastoDto.setIdGasto(idGasto);
			gastoRepository.atualizar(gastoMapper.mapear(gastoDto));

			return gastoDto;
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (RuntimeException e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	@Override
	public void delete(final Long idGasto) {
		try {
			logger.info("[01 - Excluindo Gasto | idGasto: {}]", idGasto);
			gastoRepository.delete(idGasto);
		} catch (RuntimeException e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	@Override
	public RelatorioGastoPorCategoriaDto gerarRelatorioGastoPorCategoria(final Long idPessoa, final LocalDate dtMin,
			final LocalDate dtMax) {
		try {
			logger.info("[1.0] - Recuperando Pessoa");
			final PessoaDto pessoaDto = pessoaService.buscarPorId(idPessoa);

			logger.info("[2.0] - Recuperando valores gastos por categoria");
			final List<ValorGastoPorCategoriaDto> lsVlGastoPorCategoria = gastoRepository
					.recuperarLsVlGastoPorCategoria(idPessoa, dtMin, dtMax);

			logger.info("[3.0] - Calculando valor total gasto no período");
			BigDecimal vltGastoPeriodo = lsVlGastoPorCategoria.stream().map(vlGasto -> vlGasto.getVltGasto())
					.reduce(BigDecimal.ZERO, BigDecimal::add);

			logger.info("[4.0] - Preenchendo lista de valores gastos por categoria");
			preenchendoLsVlGastoPorCategoria(lsVlGastoPorCategoria, vltGastoPeriodo);

			logger.info("[5.0] - Preenchendo Relatório");
			final RelatorioGastoPorCategoriaDto relatorio = relatorioMapper.mapear(pessoaDto, dtMin, dtMax,
					vltGastoPeriodo, lsVlGastoPorCategoria);

			return relatorio;
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (RuntimeException e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	private void preenchendoLsVlGastoPorCategoria(List<ValorGastoPorCategoriaDto> lsVlGastoPorCategoria,
			BigDecimal vltGastoPeriodo) {
		logger.info("[4.1] - Preenchendo categorias em que houve gastos");
		preenchendoCategoriasComGastos(lsVlGastoPorCategoria, vltGastoPeriodo);

		logger.info("[4.2] - Preenchendo categorias em que não houve gastos");
		for (GastoCategoriaEnum categoria : GastoCategoriaEnum.values()) {
			if (lsVlGastoPorCategoria.stream()
					.noneMatch(vlGasto -> categoria.getCodigo().compareTo(vlGasto.getIdGastoCategoria()) == 0)) {
				lsVlGastoPorCategoria.add(new ValorGastoPorCategoriaDto(categoria));
			}
		}

		logger.info("[4.3] - Ordenando lista de gastos por categoria");
		lsVlGastoPorCategoria.sort((a, b) -> a.getIdGastoCategoria().compareTo(b.getIdGastoCategoria()));
	}

	private void preenchendoCategoriasComGastos(List<ValorGastoPorCategoriaDto> lsVlGastoPorCategoria,
			BigDecimal vltGastoPeriodo) {
		logger.info("[4.1.1] - Setando nome de cada categoria");
		for (ValorGastoPorCategoriaDto vlGastoPorCategoria : lsVlGastoPorCategoria) {
			for (GastoCategoriaEnum categoria : GastoCategoriaEnum.values()) {
				if (categoria.getCodigo().compareTo(vlGastoPorCategoria.getIdGastoCategoria()) == 0) {
					vlGastoPorCategoria.setNmGastoCategoria(
							GastoCategoriaEnum.getNmNomeByCodigo(vlGastoPorCategoria.getIdGastoCategoria()));
				}
			}
		}

		logger.info("[4.1.2] - Calculando gasto percentual de cada categoria");
		List<BigDecimal> lsPercentuais = new ArrayList<>();
		BigDecimal somaPercentuais = BigDecimal.ZERO;

		for (ValorGastoPorCategoriaDto vlGastoPorCategoria : lsVlGastoPorCategoria) {
			BigDecimal percentual = vlGastoPorCategoria.getVltGasto().multiply(BigDecimal.valueOf(100L))
					.divide(vltGastoPeriodo, 2, RoundingMode.HALF_UP);

			lsPercentuais.add(percentual);
			somaPercentuais = somaPercentuais.add(percentual);
		}

		logger.info("[4.1.3] - Arredondando percentuais");
		BigDecimal diferenca = BigDecimal.valueOf(100L).subtract(somaPercentuais);

		for (int i = 0; i < lsPercentuais.size(); i++) {
			if (BigDecimal.ZERO.compareTo(diferenca) == 0)
				break;

			BigDecimal ajuste = diferenca.signum() == 1 ? BigDecimal.valueOf(0.01) : BigDecimal.valueOf(-0.01);
			lsPercentuais.set(i, lsPercentuais.get(i).add(ajuste));
			diferenca = diferenca.subtract(ajuste);
		}

		logger.info("[4.1.4] - Setando percentuais arredondados");
		for (int i = 0; i < lsVlGastoPorCategoria.size(); i++) {
			lsVlGastoPorCategoria.get(i).setPercentualGasto(lsPercentuais.get(i));
		}
	}
}
