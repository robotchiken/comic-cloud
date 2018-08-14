package com.takuba.bean;

import java.util.Date;

public class CalendarioExchangeBean {
	private CalendarioPk id;
	private Date fechaPublicar;
	private ComicExchangeBean comic;
	public CalendarioExchangeBean(){
		
	}

	public CalendarioPk getId() {
		return id;
	}

	public void setId(CalendarioPk id) {
		this.id = id;
	}

	public Date getFechaPublicar() {
		return fechaPublicar;
	}

	public void setFechaPublicar(Date fechaPublicar) {
		this.fechaPublicar = fechaPublicar;
	}

	public ComicExchangeBean getComic() {
		return comic;
	}

	public void setComic(ComicExchangeBean comic) {
		this.comic = comic;
	}
}
