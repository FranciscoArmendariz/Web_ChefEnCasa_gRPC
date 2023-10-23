package com.unla.chefEnCasa.server.Soap.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.mapping.List;

import com.unla.chefEnCasa.server.entity.Receta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RecetarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;   
    
    private String nombre;
    private long idUsuario;
    
    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "recetas_seguidas", joinColumns = @JoinColumn(name = "recetario_id"), inverseJoinColumns = @JoinColumn(name = "receta_id"))
    private Set<Receta> recetas;
}