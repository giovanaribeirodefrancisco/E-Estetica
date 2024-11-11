package com.estetica.es.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estetica.es.models.ReservaModel;
import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;
import com.estetica.es.repositores.ReservaRepository;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaModel criarReserva(UsuarioModel usuario, ServicoModel servico, LocalDateTime dataHora) {
        // Verifique se o usuário é do tipo CONTRATANTE
        if (usuario.getTipo() != UsuarioModel.TipoUsuario.CONTRATANTE) {
            throw new IllegalArgumentException("Apenas usuários do tipo CONTRATANTE podem criar reservas.");
        }

        // Verifique se já existe uma reserva no mesmo horário para o mesmo serviço
        Optional<ReservaModel> conflitingReserva = reservaRepository.findByServicoAndDataHoraBetween(
                servico, dataHora.minusMinutes(1), dataHora.plusMinutes(1));
        if (conflitingReserva.isPresent()) {
            throw new IllegalArgumentException("Já existe uma reserva para este serviço no horário informado.");
        }

        ReservaModel reserva = new ReservaModel();
        reserva.setUsuario(usuario);
        reserva.setServico(servico);
        reserva.setDataHora(dataHora);
        return reservaRepository.save(reserva);
    }

    public List<ReservaModel> buscarReservasPorUsuario(UsuarioModel usuario) {
        return reservaRepository.findByUsuario(usuario);
    }

    public List<ReservaModel> buscarReservasPorServico(ServicoModel servico) {
        return reservaRepository.findByServico(servico);
    }

    public void cancelarReserva(ReservaModel reserva) {
        reservaRepository.delete(reserva);
    }

    public List<ReservaModel> listarReservas() {
        return reservaRepository.findAll();
    }
}
