package com.estetica.es.services;

import java.util.List;


import com.estetica.es.models.UsuarioModel;
import java.util.Optional;

public interface IService {

    public UsuarioModel criarUsuario(String nome, String tipo);
    public List<UsuarioModel> listarUsuarios();
    public Optional<UsuarioModel> buscarUsuarioPorId(Integer id);
    public UsuarioModel atualizarUsuario(UsuarioModel usuario);
    public void excluirUsuario(UsuarioModel usuario);

}