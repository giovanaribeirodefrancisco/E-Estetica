package com.estetica.es.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.estetica.es.models.UsuarioModel;
import com.estetica.es.repositores.UusarioRepository;

@Service
public class UsuarioService implements IService {

    @Autowired
    private UusarioRepository usuarioRepository;

    public UsuarioService(UusarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel criarUsuario(String nome, String tipo) {
        UsuarioModel usuario = new UsuarioModel(nome, tipo);
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> buscarUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioModel atualizarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public void excluirUsuario(UsuarioModel usuario) {
        usuarioRepository.delete(usuario);
    }
}
