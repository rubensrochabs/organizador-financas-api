package com.organizador_financas_api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizador_financas_api.exception.NaoEncontradoException;
import com.organizador_financas_api.exception.OrganizadorFinanceiroException;
import com.organizador_financas_api.exception.ServicoException;
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
	public List<PessoaDto> listar() {
		try {
			logger.info("[1.0] - Recuperando lista de pessoas");
			List<PessoaDto> lsPessoaDto = pessoaRepository.retornarLista().stream()
					.map(pessoa -> pessoaMapper.mapearEntidadeParaDto(pessoa)).collect(Collectors.toList());

			return lsPessoaDto;
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	@Override
	public PessoaDto buscarPorId(final Long id) {
		try {
			logger.info("[1.0] - Recuperando pessoa | idPessoa: {}", id);
			Pessoa pessoa = recuperarPessoa(id);

			return pessoaMapper.mapearEntidadeParaDto(pessoa);
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	@Override
	public PessoaDto incluir(final PessoaDto pessoaDto) {
		try {
			logger.info("[1.0] - Incluindo Pessoa");
			Pessoa pessoa = pessoaMapper.mapearDtoParaEntidade(pessoaDto);
			PessoaDto retornoPessoa = pessoaMapper.mapearEntidadeParaDto(pessoaRepository.persistir(pessoa));

			return retornoPessoa;
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	@Override
	public PessoaDto atualizar(final Long id, final PessoaDto pessoaDto) {
		try {
			logger.info("[1.0] - Validando existência de Pessoa | idPessoa: {}", id);
			recuperarPessoa(id);

			logger.info("[2.0] - Atualizando Pessoa | PessoaDto: {}]", pessoaDto);
			pessoaDto.setId(id);
			final Pessoa pessoaAtualizada = pessoaMapper.mapearDtoParaEntidade(pessoaDto);
			pessoaRepository.atualizar(pessoaAtualizada);

			return pessoaMapper.mapearEntidadeParaDto(pessoaAtualizada);
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	@Override
	public void delete(final Long id) {
		try {
			logger.info("[1.0] - Validando existência de Pessoa | idPessoa: {}", id);
			recuperarPessoa(id);

			logger.info("[2.0] - Exlcuindo pessoa");
			pessoaRepository.delete(id);
		} catch (OrganizadorFinanceiroException e) {
			logger.error(e.toString());
			throw e;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new ServicoException(e.getMessage());
		}
	}

	private Pessoa recuperarPessoa(final Long id) {
		return pessoaRepository.recuperar(id)
				.orElseThrow(() -> new NaoEncontradoException("Pessoa não encontrada! | idPessoa: " + id));
	}
}
