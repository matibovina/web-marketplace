package com.bolsadeideas.springboot.web.lucila.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.web.lucila.app.models.entity.User;

public interface IUserDao extends CrudRepository<User, Long>{

}
