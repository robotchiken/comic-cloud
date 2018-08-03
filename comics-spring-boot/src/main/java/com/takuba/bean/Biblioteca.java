package com.takuba.bean;

import org.springframework.stereotype.Component;

@Component
public class Biblioteca {
	private Integer idUsuario;
	private Integer idComic;
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdComic() {
		return idComic;
	}
	public void setIdComic(Integer idComic) {
		this.idComic = idComic;
	}
}
