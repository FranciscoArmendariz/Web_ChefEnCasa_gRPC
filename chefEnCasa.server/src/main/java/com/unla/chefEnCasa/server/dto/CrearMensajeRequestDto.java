package com.unla.chefEnCasa.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearMensajeRequestDto {
    private long idAutor;
    private long idReceptor;
    private String asunto;
    private String mensaje;
    private String respuesta;
}
