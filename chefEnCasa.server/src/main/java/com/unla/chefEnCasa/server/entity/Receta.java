package com.unla.chefEnCasa.server.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Receta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String titulo;
	private String descripcion;
	private String categoria;
	private int tiempoAprox;
	private int puntaje;
	private int cantidadCalificaciones;
	private float promedio;
	private boolean activa;

	@OneToMany(mappedBy = "receta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<Foto> fotos;
	@OneToMany(mappedBy = "receta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<Ingrediente> ingredientes;
	@OneToMany(mappedBy = "receta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<Paso> pasos;

	@OneToMany(mappedBy = "receta", cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<Comentario> comentarios;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receta other = (Receta) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Ingrediente getIngrediente(int index) {
		return ingredientes.get(index);
	}
	public Foto getFoto(int index) {
		return fotos.get(index);
	}
	public Paso getPaso(int index){
		return pasos.get(index);
	}

	@Override
	public String toString() {
		return "Receta [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", categoria=" + categoria
				+ ", tiempoAprox=" + tiempoAprox + ", fotos=" + fotos + ", ingredientes=" + ingredientes + ", pasos="
				+ pasos + "]";
	}

}
