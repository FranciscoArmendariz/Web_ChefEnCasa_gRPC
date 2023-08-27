package com.unla.chefEnCasa.server.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
	
	@NotBlank(message = "el usuario no debe estar vacio")
	@Size(max = 250, message = "el usuario no debe tener más de {max} caracteres")
	private String usuario;
	@NotBlank(message = "la clave no debe estar vacia")
	@Size(max = 250, message = "la clave no debe tener más de {max} caracteres")
	private String clave;

}
