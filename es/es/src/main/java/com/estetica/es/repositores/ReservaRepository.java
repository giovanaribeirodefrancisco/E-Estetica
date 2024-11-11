package com.estetica.es.repositores;

import com.estetica.es.models.ReservaModel;
import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Integer> {

    List<ReservaModel> findByUsuario(UsuarioModel usuario);
    List<ReservaModel> findByServico(ServicoModel servico);
    Optional<ReservaModel> findByServicoAndDataHoraBetween(ServicoModel servico, LocalDateTime inicio, LocalDateTime fim);

    
}
