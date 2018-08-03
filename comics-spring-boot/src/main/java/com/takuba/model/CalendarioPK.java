package com.takuba.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CALENDARIO database table.
 * 
 */
@Embeddable
public class CalendarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_COMIC", insertable=false, updatable=false)
	private int idComic;

	@Column(name="ID_USUARIO", insertable=false, updatable=false)
	private int idUsuario;

	public CalendarioPK() {
	}
	public int getIdComic() {
		return this.idComic;
	}
	public void setIdComic(int idComic) {
		this.idComic = idComic;
	}
	public int getIdUsuario() {
		return this.idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CalendarioPK)) {
			return false;
		}
		CalendarioPK castOther = (CalendarioPK)other;
		return 
			(this.idComic == castOther.idComic)
			&& (this.idUsuario == castOther.idUsuario);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idComic;
		hash = hash * prime + this.idUsuario;
		
		return hash;
	}
	@Override
	public String toString() {
		return "CalendarioPK [idComic=" + idComic + ", idUsuario=" + idUsuario + "]";
	}
}