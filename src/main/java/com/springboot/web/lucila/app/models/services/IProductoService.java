package com.springboot.web.lucila.app.models.services;

import java.util.List;

import com.springboot.web.lucila.app.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> listarProductos();
	
	public void saveProducto(Producto producto);
	
	public Producto findById(Long id);
	
	public void deleteById(Long id);
	
	public Producto findByNombre(String nombre);
	
	

}
