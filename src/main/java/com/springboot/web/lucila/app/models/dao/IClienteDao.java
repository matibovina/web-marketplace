package com.springboot.web.lucila.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springboot.web.lucila.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

	List<Cliente> findByNombre(String nombre);
	
}
