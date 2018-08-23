package com.takuba.bean;

import java.util.List;

public class DeleteForm {
	private Integer idUsuario;
	private List<FormBean>events;

	public DeleteForm(){
		
	}
	public List<FormBean> getEvents() {
		return events;
	}

	public void setEvents(List<FormBean> events) {
		this.events = events;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
}
