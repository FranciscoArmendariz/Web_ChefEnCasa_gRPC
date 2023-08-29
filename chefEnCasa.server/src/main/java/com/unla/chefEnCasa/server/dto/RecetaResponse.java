package com.unla.chefEnCasa.server.dto;

import java.util.List;

import com.unla.chefEnCasa.server.entity.Ingrediente;
import com.unla.chefEnCasa.server.entity.Paso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaResponse {
	

	private long id;
	private String titulo;
	private String descripcion;
	private String categoria;
	private Integer tiempoAprox;
	private List<Ingrediente> ingredientes;
	private List<Paso> pasos;


}
