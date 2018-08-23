package com.takuba.bean;

public class AjaxResponseBody {
	Integer numeroActualizados;
	String mensaje;
	public AjaxResponseBody(Integer numeroActualizados, String mensaje) {
		super();
		this.numeroActualizados = numeroActualizados;
		this.mensaje=mensaje;
	}
	public AjaxResponseBody(){
		
	}
	public Integer getNumeroActualizados() {
		return numeroActualizados;
	}
	public void setNumeroActualizados(Integer numeroActualizados) {
		this.numeroActualizados = numeroActualizados;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
