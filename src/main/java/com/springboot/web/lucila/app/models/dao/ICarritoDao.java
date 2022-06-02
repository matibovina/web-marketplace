package com.springboot.web.lucila.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.web.lucila.app.models.entity.Carrito;
import com.springboot.web.lucila.app.models.entity.Producto;

public interface ICarritoDao extends CrudRepository<Carrito, Long>{
	
	
	@Query(value = "SELECT * FROM carrito c where c.cliente_id = :cliente_id", nativeQuery = true)
	public List<Carrito> ListarCarritoCliente(@Param(value = "cliente_id") Long cliente_id);

}
