package com.unla.chefEnCasa.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FotoIncompleta {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String url;
	
    @ManyToOne
    @JoinColumn(name = "receta_incompleta_id")
	@JsonBackReference
    private RecetaIncompleta recetaIncompleta;
    
}
