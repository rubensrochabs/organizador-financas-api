package com.organizador_financas_api.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

@Component
public class JdbcUtils {

	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public <T> List<T> recuperar(final String query, final RowMapper<T> rowMapper) {
		return namedParameterJdbcTemplate.query(query, rowMapper);
	}

	public <T> List<T> recuperar(final String query, final SqlParameterSource parameter, final RowMapper<T> rowMapper) {
		return namedParameterJdbcTemplate.query(query, parameter, rowMapper);
	}

	public Long persistir(final String query, final BeanPropertySqlParameterSource parameter) {
		final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(query, parameter, keyHolder);
		return recuperarId(keyHolder);
	}

	private Long recuperarId(final GeneratedKeyHolder keyHolder) {
		final Map<String, Object> map = keyHolder.getKeys();
		final Object obj = map.get("GENERATED_KEY");
		return Objects.nonNull(obj) ? keyHolder.getKey().longValue() : null;
	}

	public void atualizar(final String query, final SqlParameterSource parameter) {
		namedParameterJdbcTemplate.update(query, parameter);
	}

	public void delete(final String query, final SqlParameterSource parameter) {
		atualizar(query, parameter);
	}
}
