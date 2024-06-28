package com.organizador_financas_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.organizador_financas_api.model.entity.Pessoa;

@Repository
public interface PessoaRepository {
	
	public List<Pessoa> retornarLista();
	
	public Optional<Pessoa> recuperar(Long idPessoa);

	public Pessoa persistir(Pessoa pessoa);
	
	public void atualizar(Pessoa pessoa);
	
	public void delete(Long idPessoa);
}
