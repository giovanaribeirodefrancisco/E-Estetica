package com.estetica.es;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;
import com.estetica.es.repositores.ServicoRepository;
import com.estetica.es.services.ServicoService;
import com.estetica.es.services.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ServicoTest {
    @Mock
    private ServicoRepository servicoRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private ServicoService servicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarServico_Sucesso() {
        // Arrange
        String nomeServico = "Corte de Cabelo";
        
        // Criando prestadores válidos
        UsuarioModel prestador1 = new UsuarioModel();
        prestador1.setTipo(UsuarioModel.TipoUsuario.PRESTADOR);
        
        UsuarioModel prestador2 = new UsuarioModel();
        prestador2.setTipo(UsuarioModel.TipoUsuario.PRESTADOR);
        
        Set<UsuarioModel> prestadores = new HashSet<>(Arrays.asList(prestador1, prestador2));

        // Configurando o mock para salvar o serviço
        ServicoModel servicoSalvo = new ServicoModel();
        servicoSalvo.setNome(nomeServico);
        servicoSalvo.setPrestadores(prestadores);
        when(servicoRepository.save(any(ServicoModel.class))).thenReturn(servicoSalvo);

        // Act
        ServicoModel resultado = servicoService.criarServico(nomeServico, prestadores);

        // Assert
        assertNotNull(resultado);
        assertEquals(nomeServico, resultado.getNome());
        assertEquals(prestadores, resultado.getPrestadores());

        // Verificando chamada do método save
        verify(servicoRepository).save(any(ServicoModel.class));
    }

    @Test
    void testCriarServico_PrestadorInvalido() {
        // Arrange
        String nomeServico = "Corte de Cabelo";
        
        // Criando um prestador inválido
        UsuarioModel prestadorValido = new UsuarioModel();
        prestadorValido.setTipo(UsuarioModel.TipoUsuario.PRESTADOR);
        
        UsuarioModel prestadorInvalido = new UsuarioModel();
        prestadorInvalido.setTipo(UsuarioModel.TipoUsuario.CONTRATANTE);
        
        Set<UsuarioModel> prestadores = new HashSet<>(Arrays.asList(prestadorValido, prestadorInvalido));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            servicoService.criarServico(nomeServico, prestadores);
        });
    }

    @Test
    void testListarServicos() {
        // Arrange
        ServicoModel servico1 = new ServicoModel();
        servico1.setNome("Corte de Cabelo");
        
        ServicoModel servico2 = new ServicoModel();
        servico2.setNome("Manicure");

        List<ServicoModel> servicosEsperados = Arrays.asList(servico1, servico2);

        when(servicoRepository.findAll()).thenReturn(servicosEsperados);

        // Act
        List<ServicoModel> servicosEncontrados = servicoService.listarServicos();

        // Assert
        assertEquals(2, servicosEncontrados.size());
        assertEquals(servicosEsperados, servicosEncontrados);
    }

    @Test
    void testBuscarServicoPorId() {
        // Arrange
        Integer id = 1;
        ServicoModel servico = new ServicoModel();
        servico.setId(id);
        servico.setNome("Corte de Cabelo");

        when(servicoRepository.findById(id)).thenReturn(Optional.of(servico));

        // Act
        Optional<ServicoModel> servicoEncontrado = servicoService.buscarServicoPorId(id);

        // Assert
        assertTrue(servicoEncontrado.isPresent());
        assertEquals(servico, servicoEncontrado.get());
    }

    @Test
    void testAtualizarServico_Sucesso() {
        // Arrange
        ServicoModel servico = new ServicoModel();
        servico.setId(1);
        servico.setNome("Corte de Cabelo");

        // Criando prestadores válidos
        UsuarioModel prestador1 = new UsuarioModel();
        prestador1.setTipo(UsuarioModel.TipoUsuario.PRESTADOR);
        
        UsuarioModel prestador2 = new UsuarioModel();
        prestador2.setTipo(UsuarioModel.TipoUsuario.PRESTADOR);
        
        Set<UsuarioModel> prestadores = new HashSet<>(Arrays.asList(prestador1, prestador2));
        servico.setPrestadores(prestadores);

        when(servicoRepository.save(servico)).thenReturn(servico);

        // Act
        ServicoModel servicoAtualizado = servicoService.atualizarServico(servico);

        // Assert
        assertNotNull(servicoAtualizado);
        assertEquals(servico, servicoAtualizado);
        verify(servicoRepository).save(servico);
    }

    @Test
    void testAtualizarServico_PrestadorInvalido() {
        // Arrange
        ServicoModel servico = new ServicoModel();
        servico.setId(1);
        servico.setNome("Corte de Cabelo");

        // Criando um prestador inválido
        UsuarioModel prestadorValido = new UsuarioModel();
        prestadorValido.setTipo(UsuarioModel.TipoUsuario.PRESTADOR);
        
        UsuarioModel prestadorInvalido = new UsuarioModel();
        prestadorInvalido.setTipo(UsuarioModel.TipoUsuario.CONTRATANTE);
        
        Set<UsuarioModel> prestadores = new HashSet<>(Arrays.asList(prestadorValido, prestadorInvalido));
        servico.setPrestadores(prestadores);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            servicoService.atualizarServico(servico);
        });
    }

    @Test
    void testExcluirServico() {
        // Arrange
        ServicoModel servico = new ServicoModel();
        servico.setId(1);
        servico.setNome("Corte de Cabelo");

        // Act
        servicoService.excluirServico(servico);

        // Assert
        verify(servicoRepository).delete(servico);
    }

    @Test
    void testBuscarServicosPorPrestador_Sucesso() {
        // Arrange
        UsuarioModel prestador = new UsuarioModel();
        prestador.setTipo(UsuarioModel.TipoUsuario.PRESTADOR);
        prestador.setId(1);

        ServicoModel servico1 = new ServicoModel();
        ServicoModel servico2 = new ServicoModel();

        List<ServicoModel> servicosEsperados = Arrays.asList(servico1, servico2);

        when(servicoRepository.findByPrestadoresContaining(prestador)).thenReturn(servicosEsperados);

        // Act
        List<ServicoModel> servicosEncontrados = servicoService.buscarServicosPorPrestador(prestador);

        // Assert
        assertEquals(2, servicosEncontrados.size());
        assertEquals(servicosEsperados, servicosEncontrados);
    }

    @Test
    void testBuscarServicosPorPrestador_UsuarioInvalido() {
        // Arrange
        UsuarioModel usuarioInvalido = new UsuarioModel();
        usuarioInvalido.setTipo(UsuarioModel.TipoUsuario.CONTRATANTE);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            servicoService.buscarServicosPorPrestador(usuarioInvalido);
        });
    }
}
