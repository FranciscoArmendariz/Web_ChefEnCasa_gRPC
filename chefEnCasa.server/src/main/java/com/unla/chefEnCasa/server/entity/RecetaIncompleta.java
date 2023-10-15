package com.unla.chefEnCasa.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecetaIncompleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private String descripcion;
    private String categoria;
	private int tiempoAprox;

    @ManyToOne
    @JoinColumn(name = "borrador_id")
    private Borrador borrador;
    

    //@OneToMany(mappedBy = "receta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	//private List<Foto> fotos;
	//@OneToMany(mappedBy = "receta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	//private List<Ingrediente> ingredientes;
	//@OneToMany(mappedBy = "receta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	//private List<Paso> pasos;
    
}
