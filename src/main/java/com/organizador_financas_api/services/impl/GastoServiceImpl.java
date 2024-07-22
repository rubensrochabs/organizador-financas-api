package com.organizador_financas_api.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizador_financas_api.mappers.GastoMapper;
import com.organizador_financas_api.model.dto.GastoDto;
import com.organizador_financas_api.model.entity.Gasto;
import com.organizador_financas_api.repositories.GastoRepository;
import com.organizador_financas_api.services.GastoService;

@Service
public class GastoServiceImpl implements GastoService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GastoRepository gastoRepository;

	@Autowired
	private GastoMapper gastoMapper;

	@Override
	public GastoDto buscarPorId(final Long idGasto) {
		logger.info("[01 - Recuperando Gasto | idGasto: {}]", idGasto);
		Gasto gasto = recuperarGasto(idGasto);

		return gastoMapper.mapear(gasto);
	}

	@Override
	public GastoDto incluir(final GastoDto gastoDto) {
		logger.info("[01 - Incluindo Gasto]");
		Gasto retornoPessoa = gastoRepository.persistir(gastoMapper.mapear(gastoDto));

		return gastoMapper.mapear(retornoPessoa);
	}

	@Override
	public GastoDto alterar(final Long idGasto, final GastoDto gastoDto) {
		logger.info("[01 - Validando se existe Gasto | idGasto: {}]", idGasto);
		buscarPorId(idGasto);
		
		logger.info("[02 - Alterando Gasto | idGasto: {}]", idGasto);
		gastoDto.setIdGasto(idGasto);
		gastoRepository.atualizar(gastoMapper.mapear(gastoDto));
		
		return gastoDto;
	}

	@Override
	public void delete(final Long idGasto) {
		logger.info("[01 - Excluindo Gasto | idGasto: {}]", idGasto);
		gastoRepository.delete(idGasto);
	}

	private Gasto recuperarGasto(final Long idGasto) {
		return gastoRepository.recuperar(idGasto)
				.orElseThrow(() -> new RuntimeException("Gasto não encontrado! | idGasto: " + idGasto));
	}
}
