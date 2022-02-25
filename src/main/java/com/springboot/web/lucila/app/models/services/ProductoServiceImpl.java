package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.springboot.web.lucila.app.models.dao.IProductoDao;
import com.springboot.web.lucila.app.models.entity.Producto;

public class ProductoServiceImpl implements IProductoService {

	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> listarProductos() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional
	public void saveProducto(Producto producto) {
		productoDao.save(producto);
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
	public Producto findByNombre(String nombre) {
		return productoDao.findbyNombre(nombre);
	}

}
