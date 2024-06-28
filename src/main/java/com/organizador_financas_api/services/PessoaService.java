package com.organizador_financas_api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.organizador_financas_api.model.dto.PessoaDto;

@Service
public interface PessoaService {

	/**
	 * Método responsável por retornar uma lista de pessoas
	 * @param
	 * @return Lista de {@link PessoaDto}
	 */
	public List<PessoaDto> listar();

	/**
	 * Método responsável por buscar uma pessoa pelo idPessoa
	 * @param idPessoa
	 * @return {@link PessoaDto}
	 */
	public PessoaDto buscarPorId(Long idPessoa);

	/**
	 * Método responsável por salvar uma pessoa
	 * @param pessoaDto
	 * @return {@link PessoaDto}
	 */
	public PessoaDto incluir(PessoaDto pessoaDto);

	/**
	 * Método responsável por atualizar uma pessoa
	 * @param idPessoa
	 * @param pessoaDto
	 * @return {@link PessoaDto}
	 */
	public PessoaDto atualizar(Long id, PessoaDto pessoaDto);

	/**
	 * Método responsável por deletar uma pessoa pelo idPessoa
	 * @param idPessoa
	 * @return void
	 */
	public void delete(Long idPessoa);

}
