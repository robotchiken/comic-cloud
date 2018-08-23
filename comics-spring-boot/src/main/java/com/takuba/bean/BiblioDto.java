package com.takuba.bean;

import java.util.Date;

public class BiblioDto {
	private Integer idcomic;
	private String titulo;
	private String periodicidad;
	private String editorial;
	private String faltantes;
	private Date fechaPublicar;
	
	public BiblioDto(){
		
	}
	
	
	public BiblioDto(Integer idcomic, String titulo, String periodicidad, String editorial, String faltantes,
			Date fechaPublicar) {
		this.idcomic = idcomic;
		this.titulo = titulo;
		this.periodicidad = periodicidad;
		this.editorial = editorial;
		this.faltantes = faltantes;
		this.fechaPublicar = fechaPublicar;
	}


	public Integer getIdcomic() {
		return idcomic;
	}
	public void setIdcomic(Integer idcomic) {
		this.idcomic = idcomic;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public String getFaltantes() {
		return faltantes;
	}
	public void setFaltantes(String faltantes) {
		this.faltantes = faltantes;
	}
	public Date getFechaPublicar() {
		return fechaPublicar;
	}
	public void setFechaPublicar(Date fechaPublicar) {
		this.fechaPublicar = fechaPublicar;
	}
	
}
