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
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String estado; // NUEVO | RESULTA
    private String motivo; // "CONTENIDO INAPROPIADO" | "INGREDIENTES PROHIBIDOS" | "PELIGROSO PARA LA SALUD"
    
    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;
}
