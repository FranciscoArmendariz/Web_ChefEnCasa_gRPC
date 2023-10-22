package com.unla.chefEnCasa.server.Soap.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.unla.chefEnCasa.server.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MensajeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;        
    @ManyToOne
    @JoinColumn(name="autor_id")
    private Usuario autor;
    @ManyToOne
    @JoinColumn(name="receptor_id")
    private Usuario receptor;
    
    private String mensaje;
    private String asunto;
    private String respuesta;
    @Override
    public String toString() {
        return "MensajeModel [id=" + id + ", autor=" + autor + ", receptor=" + receptor + ", mensaje=" + mensaje
                + ", asunto=" + asunto + ", respuesta=" + respuesta + "]";
    }

    
  
}