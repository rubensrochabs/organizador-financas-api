package com.organizador_financas_api.utils;

import java.io.IOException;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.organizador_financas_api.exception.ServicoException;

public class JsonUtils {

	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);	// Desabilitando serialização de datas como timestamps
		mapper.registerModule(new JavaTimeModule());					// Adicionando suporte para as classes do pacote 'java.time'
	}

	public JsonUtils() {
		super();
	}

	public static String paraJson(final Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new ServicoException("Erro ao tentar converter o Objeto para Json", e.getMessage());
		}
	}

	public static <T> T paraObjViaArquivoJson(String caminho, TypeReference<T> tipoObjeto) {
		try {
			return mapper.readValue(ResourceUtils.getFile(String.format("classpath:%s", caminho)), tipoObjeto);
		} catch (IOException e) {
			throw new ServicoException("Erro ao tentar converter Json", e.getMessage());
		}
	}
}
