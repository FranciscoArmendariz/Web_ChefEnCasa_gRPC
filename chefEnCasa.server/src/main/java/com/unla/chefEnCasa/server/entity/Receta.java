package com.unla.chefEnCasa.server.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String titulo;
	private String descripcion;
	private String categoria;
	private int tiempoAprox;
	
	@OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
	private List<Foto> fotos;
	@OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
	private List<Ingrediente> ingredientes;
	@OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
	private List<Paso> pasos;

}
