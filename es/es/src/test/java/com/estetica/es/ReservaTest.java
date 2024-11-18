package com.estetica.es;

import com.estetica.es.models.ReservaModel;
import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;
import com.estetica.es.repositores.ReservaRepository;
import com.estetica.es.services.ReservaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = EsApplicationTests.class)
@ComponentScan(basePackages = "com.estetica.es.es")
public class ReservaTest {

    @MockBean
    private ReservaRepository reservaRepository;

    @Autowired
    private ReservaService reservaService;

    @Test
    void testCriarReserva_Sucesso() {
        // Arrange
        UsuarioModel usuario = new UsuarioModel();
        usuario.setTipo(UsuarioModel.TipoUsuario.CONTRATANTE);

        ServicoModel servico = new ServicoModel();

        LocalDateTime dataHora = LocalDateTime.now();

        // Configurando o mock para não encontrar reservas conflitantes
        when(reservaRepository.findByServicoAndDataHoraBetween(
            eq(servico), 
            any(LocalDateTime.class), 
            any(LocalDateTime.class)
        )).thenReturn(Optional.empty());

        // Configurando o mock para salvar a reserva
        ReservaModel reservaSalva = new ReservaModel();
        reservaSalva.setUsuario(usuario);
        reservaSalva.setServico(servico);
        reservaSalva.setDataHora(dataHora);
        when(reservaRepository.save(any(ReservaModel.class))).thenReturn(reservaSalva);

        // Act
        ReservaModel resultado = reservaService.criarReserva(usuario, servico, dataHora);

        // Assert
        assertNotNull(resultado);
        assertEquals(usuario, resultado.getUsuario());
        assertEquals(servico, resultado.getServico());
        assertEquals(dataHora, resultado.getDataHora());

        // Verificando chamadas de método
        verify(reservaRepository).findByServicoAndDataHoraBetween(
            eq(servico), 
            any(LocalDateTime.class), 
            any(LocalDateTime.class)
        );
        verify(reservaRepository).save(any(ReservaModel.class));
    }

    @Test
    void testCriarReserva_UsuarioNaoContratante() {
        // Arrange
        UsuarioModel usuario = new UsuarioModel();
        usuario.setTipo(UsuarioModel.TipoUsuario.PRESTADOR);

        ServicoModel servico = new ServicoModel();
        LocalDateTime dataHora = LocalDateTime.now();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            reservaService.criarReserva(usuario, servico, dataHora);
        });
    }

    @Test
    void testCriarReserva_HorarioConflitante() {
        // Arrange
        UsuarioModel usuario = new UsuarioModel();
        usuario.setTipo(UsuarioModel.TipoUsuario.CONTRATANTE);

        ServicoModel servico = new ServicoModel();
        LocalDateTime dataHora = LocalDateTime.now();

        // Simulando uma reserva conflitante existente
        ReservaModel reservaExistente = new ReservaModel();
        when(reservaRepository.findByServicoAndDataHoraBetween(
            eq(servico), 
            any(LocalDateTime.class), 
            any(LocalDateTime.class)
        )).thenReturn(Optional.of(reservaExistente));

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            reservaService.criarReserva(usuario, servico, dataHora);
        });
    }

    @Test
    void testBuscarReservasPorUsuario() {
        // Arrange
        UsuarioModel usuario = new UsuarioModel();
        ReservaModel reserva1 = new ReservaModel();
        ReservaModel reserva2 = new ReservaModel();

        List<ReservaModel> reservasEsperadas = Arrays.asList(reserva1, reserva2);

        when(reservaRepository.findByUsuario(usuario)).thenReturn(reservasEsperadas);

        // Act
        List<ReservaModel> reservasEncontradas = reservaService.buscarReservasPorUsuario(usuario);

        // Assert
        assertEquals(2, reservasEncontradas.size());
        assertEquals(reservasEsperadas, reservasEncontradas);
    }

    @Test
    void testBuscarReservasPorServico() {
        // Arrange
        ServicoModel servico = new ServicoModel();
        ReservaModel reserva1 = new ReservaModel();
        ReservaModel reserva2 = new ReservaModel();

        List<ReservaModel> reservasEsperadas = Arrays.asList(reserva1, reserva2);

        when(reservaRepository.findByServico(servico)).thenReturn(reservasEsperadas);

        // Act
        List<ReservaModel> reservasEncontradas = reservaService.buscarReservasPorServico(servico);

        // Assert
        assertEquals(2, reservasEncontradas.size());
        assertEquals(reservasEsperadas, reservasEncontradas);
    }

    @Test
    void testCancelarReserva() {
        // Arrange
        ReservaModel reserva = new ReservaModel();

        // Act
        reservaService.cancelarReserva(reserva);

        // Assert
        verify(reservaRepository).delete(reserva);
    }

    @Test
    void testListarReservas() {
        // Arrange
        ReservaModel reserva1 = new ReservaModel();
        ReservaModel reserva2 = new ReservaModel();

        List<ReservaModel> reservasEsperadas = Arrays.asList(reserva1, reserva2);

        when(reservaRepository.findAll()).thenReturn(reservasEsperadas);

        // Act
        List<ReservaModel> reservasEncontradas = reservaService.listarReservas();

        // Assert
        assertEquals(2, reservasEncontradas.size());
        assertEquals(reservasEsperadas, reservasEncontradas);
    }
}