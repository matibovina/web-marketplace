package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.web.lucila.app.models.dao.IProductoVendidoDao;
import com.springboot.web.lucila.app.models.entity.ProductoVendido;

@Service
public class ProductoVendidoServiceImpl implements IProductoVendidoService {
	
	private IProductoVendidoDao productoVendidoDao;

	@Override
	public List<ProductoVendido> findAll() {
		return (List<ProductoVendido>)productoVendidoDao.findAll();
	}

}
