package com.takuba.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the PERIODICIDAD database table.
 * 
 */
@Entity
public class Periodicidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERIODICIDAD")
	private int idPeriodicidad;

	private String descripcion;

	private int tiempo;
	@JsonIgnore
	//bi-directional many-to-one association to Comic
	@OneToMany(mappedBy="periodicidad")
	private List<Comic> comics;

	public Periodicidad() {
	}

	public int getIdPeriodicidad() {
		return this.idPeriodicidad;
	}

	public void setIdPeriodicidad(int idPeriodicidad) {
		this.idPeriodicidad = idPeriodicidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public List<Comic> getComics() {
		return this.comics;
	}

	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}

	public Comic addComic(Comic comic) {
		getComics().add(comic);
		comic.setPeriodicidad(this);

		return comic;
	}

	public Comic removeComic(Comic comic) {
		getComics().remove(comic);
		comic.setPeriodicidad(null);

		return comic;
	}

	@Override
	public String toString() {
		return "Periodicidad [idPeriodicidad=" + idPeriodicidad + ", descripcion=" + descripcion + ", tiempo=" + tiempo
				+ ", comics=" + comics + "]";
	}

}