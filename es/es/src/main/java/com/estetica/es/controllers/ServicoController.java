package com.estetica.es.controllers;

import com.estetica.es.dtos.ServicoDTO;
import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;
import com.estetica.es.services.ServicoService;
import com.estetica.es.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ServicoDTO> criarServico(@RequestBody ServicoDTO servicoDTO) {
        // Buscar os prestadores pelo ID
        Set<UsuarioModel> prestadores = servicoDTO.getPrestadoresId().stream()
                .map(usuarioService::buscarUsuarioPorId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        try {
            ServicoModel novoServico = servicoService.criarServico(servicoDTO.getNome(), prestadores);
            ServicoDTO servicoResponse = mapToDTO(novoServico);
            return new ResponseEntity<>(servicoResponse, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> listarServicos() {
        List<ServicoModel> servicos = servicoService.listarServicos();
        List<ServicoDTO> servicosDTO = servicos.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(servicosDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> buscarServicoPorId(@PathVariable Integer id) {
        Optional<ServicoModel> servico = servicoService.buscarServicoPorId(id);
        return servico.map(value -> new ResponseEntity<>(mapToDTO(value), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> atualizarServico(@PathVariable Integer id, @RequestBody ServicoDTO servicoDTO) {
        Optional<ServicoModel> servicoOptional = servicoService.buscarServicoPorId(id);
        if (servicoOptional.isPresent()) {
            try {
                ServicoModel servicoAtualizado = servicoService.atualizarServico(mapToModel(servicoDTO));
                ServicoDTO servicoResponse = mapToDTO(servicoAtualizado);
                return new ResponseEntity<>(servicoResponse, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServico(@PathVariable Integer id) {
        Optional<ServicoModel> servico = servicoService.buscarServicoPorId(id);
        if (servico.isPresent()) {
            servicoService.excluirServico(servico.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/prestador/{id}")
    public ResponseEntity<List<ServicoDTO>> buscarServicosPorPrestador(@PathVariable Integer id) {
        Optional<UsuarioModel> prestador = usuarioService.buscarUsuarioPorId(id);
        if (prestador.isPresent()) {
            try {
                List<ServicoModel> servicos = servicoService.buscarServicosPorPrestador(prestador.get());
                List<ServicoDTO> servicosDTO = servicos.stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(servicosDTO, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ServicoDTO mapToDTO(ServicoModel servico) {
        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setId(servico.getId());
        servicoDTO.setNome(servico.getNome());
        servicoDTO.setPrestadoresId(servico.getPrestadores().stream()
                .map(UsuarioModel::getId)
                .collect(Collectors.toSet()));
        return servicoDTO;
    }

    private ServicoModel mapToModel(ServicoDTO servicoDTO) {
        ServicoModel servico = new ServicoModel();
        servico.setId(servicoDTO.getId());
        servico.setNome(servicoDTO.getNome());
        servico.setPrestadores(servicoDTO.getPrestadoresId().stream()
                .map(usuarioService::buscarUsuarioPorId)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet()));
        return servico;
    }
}
