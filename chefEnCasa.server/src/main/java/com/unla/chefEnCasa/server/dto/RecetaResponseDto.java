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
public class RecetaResponseDto {
	

	private long id;
	private String titulo;
	private String descripcion;
	private String categoria;
	private Integer tiempoAprox;
	private float promedio;
	private List<Ingrediente> ingredientes;
	private List<Paso> pasos;
	private List<Foto> fotos;
	private List<Comentario> comentarios;


}
