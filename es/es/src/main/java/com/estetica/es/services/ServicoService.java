package com.estetica.es.services;

import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;
import com.estetica.es.repositores.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicoService implements IServicoService {

    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private UsuarioService usuarioService;

    public ServicoModel criarServico(String nome, Set<UsuarioModel> prestadores) {
        // Verificar se todos os prestadores são do tipo PRESTADOR
        Set<UsuarioModel> validPrestadores = prestadores.stream()
            .filter(usuario -> usuario.getTipo() == UsuarioModel.TipoUsuario.PRESTADOR)
            .collect(Collectors.toSet());

        if (validPrestadores.size() != prestadores.size()) {
            throw new IllegalArgumentException("Apenas usuários do tipo PRESTADOR podem ser adicionados como prestadores de um serviço.");
        }

        ServicoModel servico = new ServicoModel();
        servico.setNome(nome);
        servico.setPrestadores(validPrestadores);
        return servicoRepository.save(servico);
    }

    public List<ServicoModel> listarServicos() {
        return servicoRepository.findAll();
    }

    public Optional<ServicoModel> buscarServicoPorId(Integer id) {
        return servicoRepository.findById(id);
    }

    public ServicoModel atualizarServico(ServicoModel servico) {
        // Verificar se todos os prestadores são do tipo PRESTADOR
        Set<UsuarioModel> validPrestadores = servico.getPrestadores().stream()
            .filter(usuario -> usuario.getTipo() == UsuarioModel.TipoUsuario.PRESTADOR)
            .collect(Collectors.toSet());

        if (validPrestadores.size() != servico.getPrestadores().size()) {
            throw new IllegalArgumentException("Apenas usuários do tipo PRESTADOR podem ser adicionados como prestadores de um serviço.");
        }

        return servicoRepository.save(servico);
    }

    public void excluirServico(ServicoModel servico) {
        servicoRepository.delete(servico);
    }

    public List<ServicoModel> buscarServicosPorPrestador(UsuarioModel prestador) {
        // Verificar se o usuário é do tipo PRESTADOR
        if (prestador.getTipo() != UsuarioModel.TipoUsuario.PRESTADOR) {
            throw new IllegalArgumentException("Apenas usuários do tipo PRESTADOR podem ter seus serviços listados.");
        }

        return servicoRepository.findByPrestadoresContaining(prestador);
    }
}
