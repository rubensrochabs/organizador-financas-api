package com.organizador_financas_api.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.organizador_financas_api.exception.NaoEncontradoException;
import com.organizador_financas_api.exception.ServicoException;
import com.organizador_financas_api.mappers.PessoaMapper;
import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.entity.Pessoa;
import com.organizador_financas_api.repositories.PessoaRepository;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

	@InjectMocks
	private PessoaServiceTestObj testObj;

	@InjectMocks
	private PessoaServiceImpl pessoaService;

	@Mock
	private PessoaRepository pessoaRepository;

	@Spy
	private PessoaMapper pessoaMapper;

	@Test
	public void listar_Sucesso() {
		// Cenário
		when(pessoaRepository.retornarLista()).thenReturn(testObj.getLsPessoa());

		// Ação
		List<PessoaDto> lsPessoaDto = pessoaService.listar();

		// Validação
		verify(pessoaRepository, times(1)).retornarLista();
		verify(pessoaMapper, atLeastOnce()).mapear(any(Pessoa.class));
		assertEquals(testObj.getLsPessoaDto(), lsPessoaDto);
	}

	@Test
	public void listar_CapturaRuntimeException() {
		// Cenário
		when(pessoaRepository.retornarLista()).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			pessoaService.listar();
		});

		verify(pessoaRepository, times(1)).retornarLista();
	}

	@Test
	public void buscarPorId_Sucesso() {
		// Cenário
		when(pessoaRepository.recuperar(anyLong())).thenReturn(Optional.of(testObj.getPessoa()));

		// Ação
		PessoaDto pessoaDto = pessoaService.buscarPorId(1L);

		// Validação
		verify(pessoaRepository, times(1)).recuperar(anyLong());
		verify(pessoaMapper, times(1)).mapear(any(Pessoa.class));
		assertEquals(testObj.getPessoaDto(), pessoaDto);
	}

	@Test
	public void buscarPorId_NaoEncontraPessoa() {
		// Cenário
		when(pessoaRepository.recuperar(anyLong())).thenReturn(Optional.empty());

		// Ação e validação
		assertThrows(NaoEncontradoException.class, () -> {
			pessoaService.buscarPorId(1L);
		});

		verify(pessoaRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void buscarPorId_CapturaRuntimeException() {
		// Cenário
		when(pessoaRepository.recuperar(anyLong())).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			pessoaService.buscarPorId(1L);
		});

		verify(pessoaRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void incluir_Sucesso() {
		// Cenário
		Long idPessoa = 50L;
		PessoaDto pessoaDto = testObj.getPessoaDto();
		pessoaDto.setId(idPessoa);
		when(pessoaRepository.persistir(any(Pessoa.class))).thenReturn(idPessoa);

		// Ação
		PessoaDto retornoPessoaDto = pessoaService.incluir(testObj.getPessoaDto());

		// Validação
		verify(pessoaRepository, times(1)).persistir(any(Pessoa.class));
		verify(pessoaMapper, times(1)).mapear(any(PessoaDto.class));
		verify(pessoaMapper, times(1)).mapear(any(Pessoa.class));
		assertEquals(pessoaDto, retornoPessoaDto);
	}

	@Test
	public void incluir_CapturaRuntimeException() {
		// Cenário
		when(pessoaRepository.persistir(any(Pessoa.class))).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			pessoaService.incluir(testObj.getPessoaDto());
		});

		verify(pessoaRepository, times(1)).persistir(any(Pessoa.class));
	}

	@Test
	public void alterar_Sucesso() {
		// Cenário
		Long idPessoa = 1L;
		PessoaDto pessoaDto = testObj.getPessoaDto();
		pessoaDto.setId(idPessoa);
		when(pessoaRepository.recuperar(anyLong())).thenReturn(Optional.of(testObj.getPessoa()));
		doNothing().when(pessoaRepository).atualizar(any(Pessoa.class));

		// Ação
		PessoaDto retornoPessoaDto = pessoaService.atualizar(idPessoa, testObj.getPessoaDto());

		// Validação
		verify(pessoaRepository, times(1)).recuperar(anyLong());
		verify(pessoaRepository, times(1)).atualizar(any(Pessoa.class));
		assertEquals(pessoaDto, retornoPessoaDto);
	}

	@Test
	public void alterar_NaoEncontraPessoa() {
		// Cenário
		Long idPessoa = 1L;
		when(pessoaRepository.recuperar(anyLong())).thenReturn(Optional.empty());

		// Ação e validação
		assertThrows(NaoEncontradoException.class, () -> {
			pessoaService.atualizar(idPessoa, testObj.getPessoaDto());
		});

		verify(pessoaRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void alterar_CapturaRuntimeException() {
		// Cenário
		Long idPessoa = 1L;
		when(pessoaRepository.recuperar(anyLong())).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			pessoaService.atualizar(idPessoa, testObj.getPessoaDto());
		});

		verify(pessoaRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void delete_Sucesso() {
		// Cenário
		when(pessoaRepository.recuperar(anyLong())).thenReturn(Optional.of(testObj.getPessoa()));
		doNothing().when(pessoaRepository).delete(anyLong());

		// Ação
		pessoaService.delete(1L);

		// Validação
		verify(pessoaRepository, times(1)).recuperar(anyLong());
		verify(pessoaRepository, times(1)).delete(anyLong());
	}

	@Test
	public void delete_NaoEncontraPessoa() {
		// Cenário
		when(pessoaRepository.recuperar(anyLong())).thenReturn(Optional.empty());

		// Ação e validação
		assertThrows(NaoEncontradoException.class, () -> {
			pessoaService.delete(1L);
		});

		verify(pessoaRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void delete_CapturaRuntimeException() {
		// Cenário
		when(pessoaRepository.recuperar(anyLong())).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			pessoaService.delete(1L);
		});

		verify(pessoaRepository, times(1)).recuperar(anyLong());
	}
}
