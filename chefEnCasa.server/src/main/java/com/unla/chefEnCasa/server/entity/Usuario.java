package com.unla.chefEnCasa.server.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "usuario" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	
	private String nombre;
	private String usuario;
	private String email;
	private String clave;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recetas_favoritas", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "receta_id"))
	private List<Receta> recetasFavoritas;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "recetas_creadas", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "receta_id"))
	private List<Receta> recetasCreadas;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_seguidos", joinColumns = @JoinColumn(name = "seguidor_id"), inverseJoinColumns = @JoinColumn(name = "seguido_id"))
	private List<Usuario> usuariosSeguidos;

}
