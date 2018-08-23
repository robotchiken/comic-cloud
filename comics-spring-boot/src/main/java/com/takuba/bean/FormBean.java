package com.takuba.bean;

import java.util.Date;

public class FormBean {
	private Integer idUsuario;
	private String fecha;
	private Integer idComic;
	public FormBean(){
		
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getIdComic() {
		return idComic;
	}

	public void setIdComic(Integer idComic) {
		this.idComic = idComic;
	}
}
