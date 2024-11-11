package com.estetica.es.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReservaDTO {
    private Integer id;
    private Integer usuarioId;
    private Integer servicoId;
    private LocalDateTime dataHora;
    
}
