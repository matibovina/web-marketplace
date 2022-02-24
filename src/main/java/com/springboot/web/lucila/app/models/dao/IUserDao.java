package com.springboot.web.lucila.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.web.lucila.app.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long>{
	
	
	
}
