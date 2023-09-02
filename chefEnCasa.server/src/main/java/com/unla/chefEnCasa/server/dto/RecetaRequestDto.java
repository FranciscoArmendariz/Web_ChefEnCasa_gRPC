package com.unla.chefEnCasa.server.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.unla.chefEnCasa.server.entity.Foto;
import com.unla.chefEnCasa.server.entity.Ingrediente;
import com.unla.chefEnCasa.server.entity.Paso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetaRequestDto {
	
	@NotBlank(message = "el titulo no debe estar vacio")
	@Size(max = 250, message = "el titulo no debe tener más de {max} caracteres")
	private String titulo;
	@NotBlank(message = "la descripcion no debe estar vacia")
	@Size(max = 250, message = "la descripcion no debe tener más de {max} caracteres")
	private String descripcion;
	@NotBlank(message = "la categoria no debe estar vacia")
	@Size(max = 250, message = "la categoria no debe tener más de {max} caracteres")
	private String categoria;
	@NotNull(message = "el tiempoAprox no debe estar vacio")
	private Integer tiempoAprox;
	private List<Ingrediente> ingredientes;
	private List<Paso> pasos;
	private List<Foto> fotos;

}
