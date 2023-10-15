package com.unla.chefEnCasa.server.dto;

import java.util.List;

import com.unla.chefEnCasa.server.entity.RecetaIncompleta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearRecetasDto {

    private List<RecetaIncompleta> borradores;
    private long idBorrador;
    
}
