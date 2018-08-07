package com.takuba.bean;

import java.math.BigDecimal;

public class ComicExchangeBean {
	private static final long serialVersionUID = 1L;

	private int idComic;

	private short enPublicacion;

	private BigDecimal precio;

	private String titulo;

	//private Editorial editorial;
	
	//private Periodicidad periodicidad;
	
	private Integer numeroInicial;
	
	
	private Integer numeroFinal;
	
	public ComicExchangeBean() {
	}

	public int getIdComic() {
		return idComic;
	}

	public void setIdComic(int idComic) {
		this.idComic = idComic;
	}

	public short getEnPublicacion() {
		return enPublicacion;
	}

	public void setEnPublicacion(short enPublicacion) {
		this.enPublicacion = enPublicacion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getNumeroInicial() {
		return numeroInicial;
	}

	public void setNumeroInicial(Integer numeroInicial) {
		this.numeroInicial = numeroInicial;
	}

	public Integer getNumeroFinal() {
		return numeroFinal;
	}

	public void setNumeroFinal(Integer numeroFinal) {
		this.numeroFinal = numeroFinal;
	}
}
