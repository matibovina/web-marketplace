package com.springboot.web.lucila.app.models.services;

import java.util.List;

import com.springboot.web.lucila.app.models.entity.ProductoVendido;

public interface IProductoVendidoService {
	
	public List<ProductoVendido> findAll();

}
