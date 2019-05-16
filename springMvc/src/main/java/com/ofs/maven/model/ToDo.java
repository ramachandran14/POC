package com.ofs.maven.model;

public class ToDo {
	
	private int id;
	private String tasks;
	private String status;
	private String due_date;
	
	public void setId(int id) {
		this.id  = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setTasks(String tasks) {
		this.tasks = tasks;
	}
	
	public String getTasks() {
		return tasks;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
	public String getDue_date() {
		return due_date;
	}
}
