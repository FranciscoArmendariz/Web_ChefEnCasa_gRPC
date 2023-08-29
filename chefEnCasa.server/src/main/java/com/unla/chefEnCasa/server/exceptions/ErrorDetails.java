package com.unla.chefEnCasa.server.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	
	private Date tiempo;
	private String resultado;
	private String desde;

}
