package com.unla.chefEnCasa.server.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {
	
	private long id;
	@NotBlank(message = "el nombre no debe estar vacio")
	@Size(max = 250, message = "el nombre no debe tener más de {max} caracteres")
	private String nombre;
	@NotBlank(message = "el usuario no debe estar vacio")
	@Size(max = 250, message = "el usuario no debe tener más de {max} caracteres")
	private String usuario;
	@NotBlank(message = "el email no debe estar vacio")
	@Size(max = 250, message = "el email no debe tener más de {max} caracteres")
	@Email
	private String email;
	@NotBlank(message = "la clave no debe estar vacia")
	@Size(max = 250, message = "la clave no debe tener más de {max} caracteres")
	private String clave;
	@NotBlank(message = "la clave no debe estar vacia")
	@Size(max = 250, message = "la clave no debe tener más de {max} caracteres")
	@Pattern(regexp = "USUARIO|ADMIN", message = "Solo existen los roles 'USUARIO' y 'ADMIN'")
	private String rol;

}
