package com.estetica.es.models;

import com.estetica.es.models.UsuarioModel.TipoUsuario;

public interface IUsuario {
    public Integer getId();
    public void setId(Integer id);
    public String getNome();
    public void setNome(String nome);
    public void setTipo(TipoUsuario tipo);
    public TipoUsuario getTipo();
}


