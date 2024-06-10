package com.organizador_financas_api.services.impl;

import java.time.LocalDateTime;
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
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaMapper pessoaMapper;

	@Override
	public PessoaDto salvar(PessoaDto pessoaDto) {
		logger.info("[01 - Salvando Pessoa]");
		Pessoa pessoa = pessoaMapper.mapearDtoParaEntidade(pessoaDto);
		pessoa.setDtInclusao(LocalDateTime.now());
		PessoaDto retornoPessoa = pessoaMapper.mapearEntidadeParaDto(pessoaRepository.save(pessoa));

		return retornoPessoa;
	}

	@Override
	public List<PessoaDto> listar() {
		logger.info("[01 - Recuperando lista de pessoas]");
		List<PessoaDto> lsPessoaDto = pessoaRepository.findAll().stream()
				.map(pessoa -> pessoaMapper.mapearEntidadeParaDto(pessoa)).collect(Collectors.toList());

		return lsPessoaDto;
	}

	@Override
	public PessoaDto buscarPorId(Long id) {
		logger.info("[01 - Recuperando pessoa pelo idPessoa: {}]", id);
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrado! idPessoa: " + id));

		return pessoaMapper.mapearEntidadeParaDto(pessoa);
	}

	@Override
	public void delete(Long id) {
		logger.info("[01 - Recuperando pessoa pelo idPessoa: {}]", id);
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrado! idPessoa: " + id));
		
		logger.info("[02 - Exlcuindo pessoa]");
		pessoaRepository.delete(pessoa);
	}

	@Override
	public PessoaDto atualizar(Long id, PessoaDto pessoaDto) {
		logger.info("[01 - Recuperando pessoa pelo idPessoa: {}]", id);
		Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrado! idPessoa: " + id));
		
		logger.info("[02 - Atualizando pessoa id: {}]", id);
		pessoaDto.setId(id);
		Pessoa pessoaAtualizada = pessoaMapper.mapearDtoParaEntidade(pessoaDto);
		pessoaAtualizada.setDtInclusao(pessoa.getDtInclusao());
		pessoaAtualizada.setDtAlteracao(LocalDateTime.now());
		
		PessoaDto retornoPessoaDto = pessoaMapper.mapearEntidadeParaDto(pessoaRepository.save(pessoaAtualizada));
		return retornoPessoaDto;
	}

}
