package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.web.lucila.app.models.dao.ICarritoDao;
import com.springboot.web.lucila.app.models.entity.Carrito;

@Service
public class CarritoServiceImpl implements ICarritoService {
	
	
	@Autowired
	private ICarritoDao carritoDao;

	@Override
	public Carrito saveCarrito(Carrito carrito) {
		return carritoDao.save(carrito);
	}

	@Override
	public void deleteCarrito(Long id) {
		carritoDao.deleteById(id);
	}

	@Override
	public List<Carrito> listarCarrito(Long id) {
		
		return carritoDao.ListarCarritoCliente(id);
	}
	
	
}
