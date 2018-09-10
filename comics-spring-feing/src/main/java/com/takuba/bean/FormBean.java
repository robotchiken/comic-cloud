package com.takuba.bean;

public class FormBean {
	private Integer idUsuario;
	private String fecha;
	private Integer idComic;
	private String titulo;
	private Integer numero;
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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
