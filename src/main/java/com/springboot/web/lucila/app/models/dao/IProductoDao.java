package com.springboot.web.lucila.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.web.lucila.app.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
	public Producto findbyNombre(String nombre);

}
