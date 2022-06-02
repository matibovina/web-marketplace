package com.springboot.web.lucila.app.models.services;

import java.util.List;

import com.springboot.web.lucila.app.models.entity.Carrito;

public interface ICarritoService {
	

	public Carrito saveCarrito(Carrito carrito);
	
	public void deleteCarrito(Long id);
	
	public List<Carrito> listarCarrito(Long id);

}
