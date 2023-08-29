package com.unla.chefEnCasa.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
	
	private long id;
	private String nombre;
	private String usuario;
	private String email;
	private String rol;

}
