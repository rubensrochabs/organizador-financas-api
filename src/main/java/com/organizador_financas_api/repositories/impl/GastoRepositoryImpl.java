package com.organizador_financas_api.repositories.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.organizador_financas_api.jdbc.JdbcUtils;
import com.organizador_financas_api.model.entity.Gasto;
import com.organizador_financas_api.repositories.GastoRepository;

@Repository
@PropertySources({ @PropertySource("classpath:query/gasto_escrita.properties"),
		@PropertySource("classpath:query/gasto_leitura.properties") })
public class GastoRepositoryImpl implements GastoRepository {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${SPS.TB_GASTO.WHERE.ID}")
	private String queryRecuperarGastoById;

	@Value("${SPS.TB_GASTO.WHERE.ID_PESSOA.AND.DT_EMISSAO}")
	private String queryRecuperarGastoPorIdPessoa;

	@Value("${SPI.TB_GASTO}")
	private String queryPersistirGasto;

	@Value("${SPU.TB_GASTO}")
	private String queryAtualizarGasto;

	@Value("${SPD.TB_GASTO.WHERE.ID}")
	private String queryDeleteGastoById;

	@Autowired
	private JdbcUtils jdbcUtils;

	@Override
	public Optional<Gasto> recuperar(final Long idGasto) {
		logger.info("Início Recuperar Gasto por ID | idGasto: {}", idGasto);
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("idGasto", idGasto);

		return jdbcUtils.recuperar(queryRecuperarGastoById, parameter, BeanPropertyRowMapper.newInstance(Gasto.class))
				.stream().findFirst();
	}

	@Override
	public List<Gasto> recuperarLsPorIdPessoa(final Long idPessoa, final LocalDate dtMin, final LocalDate dtMax) {
		logger.info("Início Recuperar Gasto por idPessoa e intervalo de tempo | idPessoa: {}, dtMin: {}, dtMax: {}",
				idPessoa, dtMin, dtMax);
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("idPessoa", idPessoa);
		parameter.addValue("dtMin", dtMin);
		parameter.addValue("dtMax", dtMax);

		return jdbcUtils.recuperar(queryRecuperarGastoPorIdPessoa, parameter,
				BeanPropertyRowMapper.newInstance(Gasto.class));
	}

	@Override
	public Gasto persistir(final Gasto gasto) {
		logger.info("Início Persistir Gasto | gasto: {}", gasto);
		Long idGasto = jdbcUtils.persistir(queryPersistirGasto, new BeanPropertySqlParameterSource(gasto));
		gasto.setIdGasto(idGasto);

		return gasto;
	}

	@Override
	public void atualizar(final Gasto gasto) {
		logger.info("Início Atualizar Gasto | gasto: {}", gasto);
		jdbcUtils.atualizar(queryAtualizarGasto, new BeanPropertySqlParameterSource(gasto));
	}

	@Override
	public void delete(final Long idGasto) {
		logger.info("Início Deletar Gasto por ID | idGasto: {}", idGasto);
		jdbcUtils.delete(queryDeleteGastoById, new MapSqlParameterSource("idGasto", idGasto));
	}
}
