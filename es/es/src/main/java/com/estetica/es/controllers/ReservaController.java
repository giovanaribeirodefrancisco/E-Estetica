package com.estetica.es.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estetica.es.dtos.ReservaDTO;
import com.estetica.es.models.ReservaModel;
import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;
import com.estetica.es.services.ReservaService;
import com.estetica.es.services.ServicoService;
import com.estetica.es.services.UsuarioService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ReservaDTO> criarReserva(@RequestBody ReservaDTO reservaDTO) {
        Optional<UsuarioModel> usuario = usuarioService.buscarUsuarioPorId(reservaDTO.getUsuarioId());
        Optional<ServicoModel> servico = servicoService.buscarServicoPorId(reservaDTO.getServicoId());

        if (usuario.isPresent() && servico.isPresent()) {
            try {
                ReservaModel novaReserva = reservaService.criarReserva(usuario.get(), servico.get(), reservaDTO.getDataHora());
                ReservaDTO reservaResponse = mapToDTO(novaReserva);
                return new ResponseEntity<>(reservaResponse, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas() {
        List<ReservaModel> reservas = reservaService.listarReservas();
        List<ReservaDTO> reservasDTO = reservas.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ReservaDTO>> listarReservasPorUsuario(@PathVariable Integer usuarioId) {
        Optional<UsuarioModel> usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        if (usuario.isPresent()) {
            List<ReservaModel> reservas = reservaService.buscarReservasPorUsuario(usuario.get());
            List<ReservaDTO> reservasDTO = reservas.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/servico/{servicoId}")
    public ResponseEntity<List<ReservaDTO>> listarReservasPorServico(@PathVariable Integer servicoId) {
        Optional<ServicoModel> servico = servicoService.buscarServicoPorId(servicoId);
        if (servico.isPresent()) {
            List<ReservaModel> reservas = reservaService.buscarReservasPorServico(servico.get());
            List<ReservaDTO> reservasDTO = reservas.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(reservasDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ReservaDTO mapToDTO(ReservaModel reserva) {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(reserva.getId());
        reservaDTO.setUsuarioId(reserva.getUsuario().getId());
        reservaDTO.setServicoId(reserva.getServico().getId());
        reservaDTO.setDataHora(reserva.getDataHora());
        return reservaDTO;
    }
}
