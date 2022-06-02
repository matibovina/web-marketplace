package com.springboot.web.lucila.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.web.lucila.app.models.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findbyNombre(String nombre);

}
