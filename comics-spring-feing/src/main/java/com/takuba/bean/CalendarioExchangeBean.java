package com.takuba.bean;

import java.util.Date;

public class CalendarioExchangeBean {
	private CalendarioPk id;
	private Date fechaPublicar;
	
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
}
