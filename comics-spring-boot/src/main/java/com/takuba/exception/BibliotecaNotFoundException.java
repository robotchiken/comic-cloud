package com.takuba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BibliotecaNotFoundException extends RuntimeException {
	public BibliotecaNotFoundException(String mensaje){
		super(mensaje);
	}
}
