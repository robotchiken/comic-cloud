package com.takuba.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the CALENDARIO database table.
 * 
 */
@Entity
public class Calendario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CalendarioPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_PUBLICAR")
	private Date fechaPublicar;

	//bi-directional many-to-one association to Comic
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_COMIC",referencedColumnName="ID_COMIC",updatable=false,insertable=false)
	private Comic comic;

	//bi-directional many-to-one association to Usuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_USUARIO",referencedColumnName="ID_USUARIO",updatable=false,insertable=false)
	private Usuario usuario;
	
	private Integer numero;

	public Calendario() {
	}

	public CalendarioPK getId() {
		return this.id;
	}

	public void setId(CalendarioPK id) {
		this.id = id;
	}

	public Date getFechaPublicar() {
		return this.fechaPublicar;
	}

	public void setFechaPublicar(Date fechaPublicar) {
		this.fechaPublicar = fechaPublicar;
	}

	public Comic getComic() {
		return this.comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Calendario [id=" + id + ", fechaPublicar=" + fechaPublicar + ", comic=" + comic + ", usuario=" + usuario
				+ "]";
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}