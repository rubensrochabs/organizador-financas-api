package com.organizador_financas_api.jdbc;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

@Component
public class JdbcUtils {

	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public Long persistir(final String query, final BeanPropertySqlParameterSource parameters) {
		final GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(query, parameters, keyHolder);
		return recuperarId(keyHolder);
	}

	private Long recuperarId(final GeneratedKeyHolder keyHolder) {
		final Map<String, Object> map = keyHolder.getKeys();
		final Object obj = map.get("GENERATED_KEYS");
		return Objects.nonNull(obj) ? keyHolder.getKey().longValue() : null;
	}
}
