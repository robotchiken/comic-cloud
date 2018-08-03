package com.takuba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CARRITOCOMPRAS database table.
 * 
 */
@Entity
@Table(name="CARRITOCOMPRAS")
public class Carritocompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CARRITO")
	private int idCarrito;

	//bi-directional many-to-one association to Comic
	@ManyToOne
	@JoinColumn(name="ID_COMIC")
	private Comic comic;

	public Carritocompra() {
	}

	public int getIdCarrito() {
		return this.idCarrito;
	}

	public void setIdCarrito(int idCarrito) {
		this.idCarrito = idCarrito;
	}

	public Comic getComic() {
		return this.comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

}