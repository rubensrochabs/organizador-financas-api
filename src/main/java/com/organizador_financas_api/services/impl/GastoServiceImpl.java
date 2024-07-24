package com.organizador_financas_api.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizador_financas_api.mappers.GastoMapper;
import com.organizador_financas_api.model.dto.GastoEntradaDto;
import com.organizador_financas_api.model.dto.GastoSaidaDto;
import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.dto.ValorGastoPorCategoriaDto;
import com.organizador_financas_api.model.entity.Gasto;
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

	@Override
	public GastoSaidaDto buscarPorId(final Long idGasto) {
		logger.info("[01 - Recuperando Gasto | idGasto: {}]", idGasto);
		Gasto gasto = recuperarGasto(idGasto);

		logger.info("[02 - Recuperando Pessoa | idPessoa: {}]", gasto.getIdPessoa());
		PessoaDto pessoaDto = recuperarPessoaById(gasto.getIdPessoa());

		logger.info("[03 - Mapeando resposta]");
		return gastoMapper.mapear(gasto, pessoaDto.getNmNome());
	}

	@Override
	public List<GastoSaidaDto> listarPorIdPessoa(final Long idPessoa, final LocalDate dtMin, final LocalDate dtMax) {
		logger.info("[01 - Listando gastos por Pessoa | idPessoa: {}]", idPessoa);
		List<Gasto> lsGastos = gastoRepository.recuperarLsPorIdPessoa(idPessoa, dtMin, dtMax);

		logger.info("[02 - Recuperando Pessoa | idPessoa: {}]", idPessoa);
		PessoaDto pessoaDto = recuperarPessoaById(idPessoa);

		logger.info("[03 - Mapeando resposta]");
		List<GastoSaidaDto> lsGastoDto = lsGastos.stream()
				.map(gasto -> gastoMapper.mapear(gasto, pessoaDto.getNmNome())).collect(Collectors.toList());

		return lsGastoDto;
	}

	@Override
	public GastoEntradaDto incluir(final GastoEntradaDto gastoDto) {
		logger.info("[01 - Incluindo Gasto]");
		Gasto retornoPessoa = gastoRepository.persistir(gastoMapper.mapear(gastoDto));

		logger.info("[02 - Mapeando resposta]");
		return gastoMapper.mapear(retornoPessoa);
	}

	@Override
	public GastoEntradaDto alterar(final Long idGasto, final GastoEntradaDto gastoDto) {
		logger.info("[01 - Validando se existe Gasto | idGasto: {}]", idGasto);
		buscarPorId(idGasto);

		logger.info("[02 - Atualizando Gasto | idGasto: {}]", idGasto);
		gastoDto.setIdGasto(idGasto);
		gastoRepository.atualizar(gastoMapper.mapear(gastoDto));

		return gastoDto;
	}

	@Override
	public void delete(final Long idGasto) {
		logger.info("[01 - Excluindo Gasto | idGasto: {}]", idGasto);
		gastoRepository.delete(idGasto);
	}

	private PessoaDto recuperarPessoaById(final Long idPessoa) {
		return pessoaService.buscarPorId(idPessoa);
	}

	private Gasto recuperarGasto(final Long idGasto) {
		return gastoRepository.recuperar(idGasto)
				.orElseThrow(() -> new RuntimeException("Gasto não encontrado! | idGasto: " + idGasto));
	}
}
