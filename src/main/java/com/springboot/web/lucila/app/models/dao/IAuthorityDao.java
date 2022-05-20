package com.springboot.web.lucila.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.web.lucila.app.models.entity.Authority;
import com.springboot.web.lucila.app.models.entity.Cliente;

public interface IAuthorityDao extends CrudRepository<Authority, Long>{
	
	
	
}
