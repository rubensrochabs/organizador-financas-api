package com.organizador_financas_api.repositories.impl;

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
import com.organizador_financas_api.model.entity.Pessoa;
import com.organizador_financas_api.repositories.PessoaRepository;

@Repository
@PropertySources({ @PropertySource("classpath:query/pessoa_escrita.properties"),
		@PropertySource("classpath:query/pessoa_leitura.properties") })
public class PessoaRepositoryImpl implements PessoaRepository {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Value("${SPS.TB_PESSOA}")
	private String queryListarPessoas;

	@Value("${SPS.TB_PESSOA.WHERE.ID}")
	private String queryConsultarById;

	@Value("${SPI.TB_PESSOA}")
	private String queryPersistirPessoa;

	@Value("${SPU.TB_PESSOA.WHERE.ID}")
	private String queryUpdatePessoa;

	@Value("${SPD.TB_PESSOA.WHERE.ID}")
	private String queryDeleteById;

	@Autowired
	private JdbcUtils jdbcUtils;

	@Override
	public List<Pessoa> retornarLista() {
		log.info("Início retornarLista Pessoa");
		return jdbcUtils.recuperar(queryListarPessoas, BeanPropertyRowMapper.newInstance(Pessoa.class));
	}

	@Override
	public Optional<Pessoa> recuperar(final Long idPessoa) {
		log.info("Início retornar Pessoa || idPessoa: {}", idPessoa);
		final MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("idPessoa", idPessoa);

		return jdbcUtils
				.recuperar(queryConsultarById, parameter, BeanPropertyRowMapper.newInstance(Pessoa.class))
				.stream().findFirst();
	}

	@Override
	public Pessoa persistir(final Pessoa pessoa) {
		log.info("Início persistir Pessoa || pessoa: {}", pessoa);
		final Long idPessoa = jdbcUtils.persistir(queryPersistirPessoa, new BeanPropertySqlParameterSource(pessoa));
		pessoa.setIdPessoa(idPessoa);

		return pessoa;
	}

	@Override
	public void atualizar(final Pessoa pessoa) {
		log.info("Início atualizar Pessoa || pessoa: {}", pessoa);
		jdbcUtils.atualizar(queryUpdatePessoa, new BeanPropertySqlParameterSource(pessoa));
	}

	@Override
	public void delete(final Long idPessoa) {
		log.info("Início delete Pessoa por id || idPessoa: {}", idPessoa);
		final MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("idPessoa", idPessoa);

		jdbcUtils.delete(queryDeleteById, parameter);
	}
}
