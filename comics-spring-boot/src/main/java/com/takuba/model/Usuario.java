package com.takuba.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the USUARIOS database table.
 * 
 */
@Entity
@Table(name="USUARIOS")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_USUARIO")
	private int idUsuario;

	private String password;

	private String usuario;

	//bi-directional many-to-many association to Comic
	
	@ManyToMany(cascade = { CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinTable(
		name="BIBLIOTECA"
		, joinColumns={
			@JoinColumn(name="ID_USUARIO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_COMIC")
			}
		)
	private List<Comic> comics;

	//bi-directional many-to-one association to Calendario
	@OneToMany(mappedBy="usuario")
	private List<Calendario> calendarios;

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Comic> getComics() {
		return this.comics;
	}

	public void setComics(List<Comic> comics) {
		this.comics = comics;
	}

	public List<Calendario> getCalendarios() {
		return this.calendarios;
	}

	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}

	public Calendario addCalendario(Calendario calendario) {
		getCalendarios().add(calendario);
		calendario.setUsuario(this);

		return calendario;
	}

	public Calendario removeCalendario(Calendario calendario) {
		getCalendarios().remove(calendario);
		calendario.setUsuario(null);

		return calendario;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", password=" + password + ", usuario=" + usuario + ", comics="
				+ comics + ", calendarios=" + calendarios + "]";
	}

}