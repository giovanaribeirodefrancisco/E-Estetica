package com.estetica.es.services;

import com.estetica.es.models.ServicoModel;
import com.estetica.es.models.UsuarioModel;
import com.estetica.es.repositores.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ServicoService implements IServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public ServicoModel criarServico(String nome, Set<UsuarioModel> prestadores) {
        ServicoModel servico = new ServicoModel();
        servico.setNome(nome);
        servico.setPrestadores(prestadores);
        return servicoRepository.save(servico);
    }

    public List<ServicoModel> listarServicos() {
        return servicoRepository.findAll();
    }

    public Optional<ServicoModel> buscarServicoPorId(Integer id) {
        return servicoRepository.findById(id);
    }

    public ServicoModel atualizarServico(ServicoModel servico) {
        return servicoRepository.save(servico);
    }

    public void excluirServico(ServicoModel servico) {
        servicoRepository.delete(servico);
    }

    public List<ServicoModel> buscarServicosPorPrestador(UsuarioModel prestador) {
        return servicoRepository.findByPrestadoresContaining(prestador);
    }
}
