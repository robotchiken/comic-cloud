package com.takuba.bean;

public class EventSearch {
	private String start;
	private String end;
	private Integer userid;
	public EventSearch(){
		
	}
	public EventSearch(String start, String end, Integer userid) {
		super();
		this.start = start;
		this.end = end;
		this.userid = userid;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
}
