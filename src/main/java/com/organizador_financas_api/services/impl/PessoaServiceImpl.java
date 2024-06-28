package com.organizador_financas_api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizador_financas_api.mappers.PessoaMapper;
import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.entity.Pessoa;
import com.organizador_financas_api.repositories.PessoaRepository;
import com.organizador_financas_api.services.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaMapper pessoaMapper;

	@Override
	public List<PessoaDto> listar() {
		log.info("[01 - Recuperando lista de pessoas]");
		List<PessoaDto> lsPessoaDto = pessoaRepository.retornarLista().stream()
				.map(pessoa -> pessoaMapper.mapearEntidadeParaDto(pessoa)).collect(Collectors.toList());

		return lsPessoaDto;
	}

	@Override
	public PessoaDto buscarPorId(final Long id) {
		log.info("[01 - Recuperando pessoa | idPessoa: {}]", id);
		Pessoa pessoa = recuperarPessoa(id);

		return pessoaMapper.mapearEntidadeParaDto(pessoa);
	}

	@Override
	public PessoaDto incluir(final PessoaDto pessoaDto) {
		log.info("[01 - Incluindo Pessoa]");
		Pessoa pessoa = pessoaMapper.mapearDtoParaEntidade(pessoaDto);
		PessoaDto retornoPessoa = pessoaMapper.mapearEntidadeParaDto(pessoaRepository.persistir(pessoa));

		return retornoPessoa;
	}

	@Override
	public PessoaDto atualizar(final Long id, final PessoaDto pessoaDto) {
		log.info("[01 - Validando existência de Pessoa | idPessoa: {}]", id);
		recuperarPessoa(id);

		log.info("[02 - Atualizando Pessoa | PessoaDto: {}]", pessoaDto);
		pessoaDto.setId(id);
		final Pessoa pessoaAtualizada = pessoaMapper.mapearDtoParaEntidade(pessoaDto);
		pessoaRepository.atualizar(pessoaAtualizada);

		return pessoaMapper.mapearEntidadeParaDto(pessoaAtualizada);
	}

	@Override
	public void delete(final Long id) {
		log.info("[01 - Validando existência de Pessoa | idPessoa: {}]", id);
		recuperarPessoa(id);

		log.info("[02 - Exlcuindo pessoa]");
		pessoaRepository.delete(id);
	}

	private Pessoa recuperarPessoa(final Long id) {
		return pessoaRepository.recuperar(id)
				.orElseThrow(() -> new RuntimeException("Pessoa não encontrado! | idPessoa: " + id));
	}
}
