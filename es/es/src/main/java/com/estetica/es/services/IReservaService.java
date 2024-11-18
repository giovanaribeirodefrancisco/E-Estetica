package com.estetica.es.services;

import com.estetica.es.models.ReservaModel;
import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;

import java.time.LocalDateTime;
import java.util.List;

public interface IReservaService {
    
    public ReservaModel criarReserva(UsuarioModel usuario, ServicoModel servico, LocalDateTime dataHora);
    public List<ReservaModel> buscarReservasPorUsuario(UsuarioModel usuario);
    public List<ReservaModel> buscarReservasPorServico(ServicoModel servico);
    public void cancelarReserva(ReservaModel reserva);


}
