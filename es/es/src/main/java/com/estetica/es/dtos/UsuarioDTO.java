package com.estetica.es.dtos;

import com.estetica.es.models.UsuarioModel.TipoUsuario;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String nome;
    private TipoUsuario tipo;
}