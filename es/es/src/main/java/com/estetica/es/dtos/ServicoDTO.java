package com.estetica.es.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class ServicoDTO {
    private Integer id;
    private String nome;
    private Set<Integer> prestadoresId;
}