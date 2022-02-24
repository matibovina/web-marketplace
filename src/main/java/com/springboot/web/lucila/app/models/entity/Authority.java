package com.springboot.web.lucila.app.models.entity;

import java.io.Serializable;

public class Authority implements Serializable{


	private Long id;
	
	private User user;
	
	private String user_role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	
	private static final long serialVersionUID = 1L;

	
}
