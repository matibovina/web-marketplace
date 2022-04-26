package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.springboot.web.lucila.app.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public Page<Producto> findAll(Pageable pageable);

	
	public Producto saveProducto(Producto producto);
	
	public Producto findById(Long id);
	
	public void deleteById(Long id);
	
	public List<Producto> findbyNombre(String nombre);
	
	

}
