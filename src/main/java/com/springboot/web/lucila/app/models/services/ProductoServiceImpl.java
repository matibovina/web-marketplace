package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.web.lucila.app.models.dao.IProductoDao;
import com.springboot.web.lucila.app.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional
	public Producto saveProducto(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findbyNombre(String nombre) {
		return productoDao.findbyNombre(nombre);
	} 

}
