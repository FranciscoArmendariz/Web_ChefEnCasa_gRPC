package com.unla.chefEnCasa.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    

    @OneToMany(mappedBy = "recetaIncompleta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<FotoIncompleta> fotos = new ArrayList<>();
	@OneToMany(mappedBy = "recetaIncompleta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<IngredienteIncompleto> ingredientes = new ArrayList<>();;
	@OneToMany(mappedBy = "recetaIncompleta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<PasoIncompleto> pasos = new ArrayList<>();;


    public IngredienteIncompleto getIngrediente(int index) {
		return ingredientes.get(index);
	}
	public FotoIncompleta getFoto(int index) {
		return fotos.get(index);
	}
	public PasoIncompleto getPaso(int index){
		return pasos.get(index);
	}
    
}
