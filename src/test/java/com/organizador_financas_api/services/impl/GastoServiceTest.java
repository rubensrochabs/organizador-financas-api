package com.organizador_financas_api.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import com.organizador_financas_api.mappers.GastoMapper;
import com.organizador_financas_api.mappers.RelatorioMapper;
import com.organizador_financas_api.model.dto.GastoEntradaDto;
import com.organizador_financas_api.model.dto.GastoSaidaDto;
import com.organizador_financas_api.model.dto.PessoaDto;
import com.organizador_financas_api.model.dto.RelatorioGastoPorCategoriaDto;
import com.organizador_financas_api.model.entity.Gasto;
import com.organizador_financas_api.repositories.GastoRepository;
import com.organizador_financas_api.services.PessoaService;

@ExtendWith(MockitoExtension.class)
public class GastoServiceTest {

	@InjectMocks
	private GastoServiceTestObj testObj;

	@InjectMocks
	private GastoServiceImpl gastoService;

	@Mock
	private PessoaService pessoaService;

	@Mock
	private GastoRepository gastoRepository;

	@Spy
	private GastoMapper gastoMapper;

	@Spy
	private RelatorioMapper relatorioMapper;

	@Test
	public void buscarPorId_Sucesso() {
		// Cenário
		when(gastoRepository.recuperar(anyLong())).thenReturn(Optional.of(testObj.getGasto()));
		when(pessoaService.buscarPorId(anyLong())).thenReturn(testObj.getPessoaDto());

		// Ação
		GastoSaidaDto gastoSaidaDto = gastoService.buscarPorId(13L);

		// Validação
		verify(gastoRepository, times(1)).recuperar(anyLong());
		verify(pessoaService, times(1)).buscarPorId(anyLong());
		verify(gastoMapper, times(1)).mapear(any(Gasto.class), anyString());
		assertEquals(testObj.getGastoSaidaDto(), gastoSaidaDto);
	}

	@Test
	public void buscarPorId_NaoEncontraGasto() {
		// Cenário
		when(gastoRepository.recuperar(anyLong())).thenReturn(Optional.empty());

		// Ação e validação
		assertThrows(NaoEncontradoException.class, () -> {
			gastoService.buscarPorId(13L);
		});

		verify(gastoRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void buscarPorId_CapturaRuntimeException() {
		// Cenário
		when(gastoRepository.recuperar(anyLong())).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			gastoService.buscarPorId(13L);
		});

		verify(gastoRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void buscarPorId_NaoEncontraPessoa() {
		// Cenário
		when(gastoRepository.recuperar(anyLong())).thenReturn(Optional.of(testObj.getGasto()));
		when(pessoaService.buscarPorId(anyLong())).thenThrow(
				new NaoEncontradoException("Pessoa não encontrada! | idPessoa: " + testObj.getPessoaDto().getId()));

		// Ação e validação
		assertThrows(NaoEncontradoException.class, () -> {
			gastoService.buscarPorId(13L);
		});

		verify(gastoRepository, times(1)).recuperar(anyLong());
		verify(pessoaService, times(1)).buscarPorId(anyLong());
	}

	@Test
	public void listarPorIdPessoa_Sucesso() {
		// Cenário
		when(gastoRepository.recuperarLsPorIdPessoa(anyLong(), any(LocalDate.class), any(LocalDate.class)))
				.thenReturn(testObj.getLsGasto());
		when(pessoaService.buscarPorId(anyLong())).thenReturn(testObj.getPessoaDto());

		// Ação
		List<GastoSaidaDto> lsGastoSaidaDto = gastoService.listarPorIdPessoa(1L, LocalDate.of(2023, 07, 01),
				LocalDate.of(2023, 07, 31));

		// Validação
		verify(gastoRepository, times(1)).recuperarLsPorIdPessoa(anyLong(), any(LocalDate.class), any(LocalDate.class));
		verify(pessoaService, times(1)).buscarPorId(anyLong());
		verify(gastoMapper, atLeastOnce()).mapear(any(Gasto.class), anyString());
		assertEquals(testObj.getLsGastoSaidaDto(), lsGastoSaidaDto);
	}

	@Test
	public void listarPorIdPessoa_NaoEncontraPessoa() {
		// Cenário
		when(gastoRepository.recuperarLsPorIdPessoa(anyLong(), any(LocalDate.class), any(LocalDate.class)))
				.thenReturn(testObj.getLsGasto());
		when(pessoaService.buscarPorId(anyLong())).thenThrow(
				new NaoEncontradoException("Pessoa não encontrada! | idPessoa: " + testObj.getPessoaDto().getId()));

		// Ação e validação
		assertThrows(NaoEncontradoException.class, () -> {
			gastoService.listarPorIdPessoa(1L, LocalDate.of(2023, 07, 01), LocalDate.of(2023, 07, 31));
		});
	}

	@Test
	public void listarPorIdPessoa_CapturaRuntimeException() {
		// Cenário
		when(gastoRepository.recuperarLsPorIdPessoa(anyLong(), any(LocalDate.class), any(LocalDate.class)))
				.thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			gastoService.listarPorIdPessoa(1L, LocalDate.of(2023, 07, 01), LocalDate.of(2023, 07, 31));
		});

		verify(gastoRepository, times(1)).recuperarLsPorIdPessoa(anyLong(), any(LocalDate.class), any(LocalDate.class));
	}

	@Test
	public void incluir_Sucesso() {
		// Cenário
		Long idGasto = 50L;
		GastoEntradaDto gastoEntradaDto = testObj.getGastoEntradaDto();
		gastoEntradaDto.setIdGasto(idGasto);
		when(gastoRepository.persistir(any(Gasto.class))).thenReturn(idGasto);

		// Ação
		GastoEntradaDto retornoGasto = gastoService.incluir(testObj.getGastoEntradaDto());

		// Validação
		verify(gastoRepository, times(1)).persistir(any(Gasto.class));
		assertEquals(gastoEntradaDto, retornoGasto);
	}

	@Test
	public void incluir_CapturaRuntimeException() {
		// Cenário
		when(gastoRepository.persistir(any(Gasto.class))).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			gastoService.incluir(testObj.getGastoEntradaDto());
		});

		verify(gastoRepository, times(1)).persistir(any(Gasto.class));
	}

	@Test
	public void alterar_Sucesso() {
		// Cenário
		Long idGasto = 50L;
		GastoEntradaDto gastoEntradaDto = testObj.getGastoEntradaDto();
		gastoEntradaDto.setIdGasto(idGasto);
		when(gastoRepository.recuperar(anyLong())).thenReturn(Optional.of(testObj.getGasto()));
		doNothing().when(gastoRepository).atualizar(any(Gasto.class));

		// Ação
		GastoEntradaDto retornoGasto = gastoService.alterar(13L, testObj.getGastoEntradaDto());

		// Validação
		verify(gastoRepository, times(1)).recuperar(anyLong());
		verify(gastoRepository, times(1)).atualizar(any(Gasto.class));
		assertEquals(gastoEntradaDto, retornoGasto);
	}

	@Test
	public void alterar_NaoEncontraGasto() {
		// Cenário
		when(gastoRepository.recuperar(anyLong())).thenReturn(Optional.empty());

		// Ação e validação
		assertThrows(NaoEncontradoException.class, () -> {
			gastoService.alterar(13L, testObj.getGastoEntradaDto());
		});

		verify(gastoRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void alterar_CapturaRuntimeException() {
		// Cenário
		when(gastoRepository.recuperar(anyLong())).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			gastoService.alterar(13L, testObj.getGastoEntradaDto());
		});

		verify(gastoRepository, times(1)).recuperar(anyLong());
	}

	@Test
	public void delete_Sucesso() {
		// Cenário
		doNothing().when(gastoRepository).delete(anyLong());

		// Ação
		gastoService.delete(13L);

		// Validação
		verify(gastoRepository, times(1)).delete(anyLong());
	}

	@Test
	public void delete_CapturaRuntimeException() {
		// Cenário
		doThrow(new RuntimeException("exception_test")).when(gastoRepository).delete(anyLong());

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			gastoService.delete(13L);
		});

		verify(gastoRepository, times(1)).delete(anyLong());
	}

	@Test
	public void gerarRelatorioGastoPorCategoria_Sucesso() {
		// Cenário
		when(pessoaService.buscarPorId(anyLong())).thenReturn(testObj.getPessoaDto());
		when(gastoRepository.recuperarLsVlGastoPorCategoria(anyLong(), any(LocalDate.class), any(LocalDate.class)))
				.thenReturn(testObj.getLsValorGastoPorCategoriaDto());

		// Ação
		RelatorioGastoPorCategoriaDto relatorio = gastoService.gerarRelatorioGastoPorCategoria(1L,
				LocalDate.of(2023, 07, 01), LocalDate.of(2023, 07, 31));

		// Validação
		verify(pessoaService, times(1)).buscarPorId(anyLong());
		verify(gastoRepository, times(1)).recuperarLsVlGastoPorCategoria(anyLong(), any(LocalDate.class),
				any(LocalDate.class));
		verify(relatorioMapper, times(1)).mapear(any(PessoaDto.class), any(LocalDate.class), any(LocalDate.class),
				any(BigDecimal.class), anyList());
		assertEquals(testObj.getRelatorioGastoPorCategoriaDto(), relatorio);
	}

	@Test
	public void gerarRelatorioGastoPorCategoria_NaoEncontraPessoa() {
		// Cenário
		when(pessoaService.buscarPorId(anyLong())).thenThrow(
				new NaoEncontradoException("Pessoa não encontrada! | idPessoa: " + testObj.getPessoaDto().getId()));

		// Ação e validação
		assertThrows(NaoEncontradoException.class, () -> {
			gastoService.gerarRelatorioGastoPorCategoria(1L, LocalDate.of(2023, 07, 01), LocalDate.of(2023, 07, 31));
		});

		verify(pessoaService, times(1)).buscarPorId(anyLong());
	}

	@Test
	public void gerarRelatorioGastoPorCategoria_CapturaRuntimeException() {
		// Cenário
		when(pessoaService.buscarPorId(anyLong())).thenThrow(new RuntimeException("exception_test"));

		// Ação e validação
		assertThrows(ServicoException.class, () -> {
			gastoService.gerarRelatorioGastoPorCategoria(1L, LocalDate.of(2023, 07, 01), LocalDate.of(2023, 07, 31));
		});

		verify(pessoaService, times(1)).buscarPorId(anyLong());
	}
}