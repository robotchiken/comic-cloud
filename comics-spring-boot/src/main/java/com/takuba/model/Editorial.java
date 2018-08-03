package com.takuba.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the EDITORIAL database table.
 * 
 */
@Entity
public class Editorial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EDITORIAL")
	private int idEditorial;

	private String nombre;
	@JsonIgnore
	//bi-directional many-to-one association to Comic
	@OneToMany(mappedBy="editorial")
	private List<Comic> comics;

	public Editorial() {
	}

	public int getIdEditorial() {
		return this.idEditorial;
	}

	public void setIdEditorial(int idEditorial) {
		this.idEditorial = idEditorial;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Comic> getComics() {
		return this.comics;
	}

	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}

	public Comic addComic(Comic comic) {
		getComics().add(comic);
		comic.setEditorial(this);

		return comic;
	}

	public Comic removeComic(Comic comic) {
		getComics().remove(comic);
		comic.setEditorial(null);

		return comic;
	}

}