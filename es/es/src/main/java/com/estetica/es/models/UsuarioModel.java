package com.estetica.es.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioModel implements IUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;

    public enum TipoUsuario {
        CONTRATANTE, PRESTADOR
    }

    public UsuarioModel() {
    }
    public UsuarioModel(String nome, TipoUsuario tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
    public UsuarioModel(Integer id, String nome, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String getNome() {
        return nome;
    }
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setTipo(TipoUsuario tipo){
        this.tipo = tipo;
    }

    @Override
    public TipoUsuario getTipo(){
        return tipo;
    }


}
