package com.unla.chefEnCasa.server.dto;

import java.util.List;

import com.unla.chefEnCasa.server.entity.Comentario;
import com.unla.chefEnCasa.server.entity.Foto;
import com.unla.chefEnCasa.server.entity.Ingrediente;
import com.unla.chefEnCasa.server.entity.Paso;

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
