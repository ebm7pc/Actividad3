package org.tourofheroes.exception;

public class DataNotFound extends Exception {

	private String mensajeUsuario;
	
	public DataNotFound(String mensajeUsuario){
		this.mensajeUsuario = mensajeUsuario;
	}
}
