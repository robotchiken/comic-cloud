package com.takuba.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;

/**
 * The persistent class for the COMIC database table.
 * 
 */
@Entity
public class Comic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_COMIC")
	private int idComic;

	@Column(name="EN_PUBLICACION")
	private short enPublicacion;

	private BigDecimal precio;

	private String titulo;
	@JsonIgnore
	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="comics")
	private List<Usuario> usuarios;
	@JsonIgnore
	//bi-directional many-to-one association to Calendario
	@OneToMany(mappedBy="comic")
	private List<Calendario> calendarios;
	@JsonIgnore
	//bi-directional many-to-one association to Carritocompra
	@OneToMany(mappedBy="comic")
	private List<Carritocompra> carritocompras;

	//bi-directional many-to-one association to Editorial
	@ManyToOne
	@JoinColumn(name="ID_EDITORIAL")
	private Editorial editorial;
	
	//bi-directional many-to-one association to Periodicidad
	@ManyToOne
	@JoinColumn(name="ID_PERIODICIDAD")
	private Periodicidad periodicidad;
	
	@Column(name="NUMERO_INICIAL")
	private Integer numeroInicial;
	
	@Column(name="NUMERO_FINAL")
	private Integer numeroFinal;

	public Comic() {
	}

	public int getIdComic() {
		return this.idComic;
	}

	public void setIdComic(int idComic) {
		this.idComic = idComic;
	}

	public short getEnPublicacion() {
		return this.enPublicacion;
	}

	public void setEnPublicacion(short enPublicacion) {
		this.enPublicacion = enPublicacion;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Calendario> getCalendarios() {
		return this.calendarios;
	}

	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}

	public Calendario addCalendario(Calendario calendario) {
		getCalendarios().add(calendario);
		calendario.setComic(this);

		return calendario;
	}

	public Calendario removeCalendario(Calendario calendario) {
		getCalendarios().remove(calendario);
		calendario.setComic(null);

		return calendario;
	}

	public List<Carritocompra> getCarritocompras() {
		return this.carritocompras;
	}

	public void setCarritocompras(List<Carritocompra> carritocompras) {
		this.carritocompras = carritocompras;
	}

	public Carritocompra addCarritocompra(Carritocompra carritocompra) {
		getCarritocompras().add(carritocompra);
		carritocompra.setComic(this);

		return carritocompra;
	}

	public Carritocompra removeCarritocompra(Carritocompra carritocompra) {
		getCarritocompras().remove(carritocompra);
		carritocompra.setComic(null);

		return carritocompra;
	}

	public Editorial getEditorial() {
		return this.editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public Periodicidad getPeriodicidad() {
		return this.periodicidad;
	}

	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
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

	@Override
	public String toString() {
		return "Comic [idComic=" + idComic + ", enPublicacion=" + enPublicacion + ", precio=" + precio + ", titulo="
				+ titulo + ", usuarios=" + usuarios + ", calendarios=" + calendarios + ", carritocompras="
				+ carritocompras + ", editorial=" + editorial + ", periodicidad=" + periodicidad + "]";
	}

}