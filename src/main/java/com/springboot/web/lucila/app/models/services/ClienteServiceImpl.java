package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.springboot.web.lucila.app.models.dao.IClienteDao;
import com.springboot.web.lucila.app.models.dao.IReciboDao;
import com.springboot.web.lucila.app.models.dao.ICarritoDao;
import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.entity.Carrito;
import com.springboot.web.lucila.app.models.entity.Recibo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private IReciboDao reciboDao;

	@Autowired
	private ICarritoDao iCarritoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	public List<Cliente> findAllClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>)clienteDao.listaClientes();
	}

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findByNombre(String nombre) {
		return clienteDao.findByNombre(nombre);
	}

	@Override
	@Transactional
	public void deleteClienteById(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional
	public Recibo saveRecibo(Recibo recibo) {
		return reciboDao.save(recibo);
	}

	@Override
	@Transactional(readOnly = true)
	public Recibo findReciboById(Long id) {
		// TODO Auto-generated method stub
		return reciboDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteReciboById(Long id) {
		reciboDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Recibo> listarRecibos() {
		return (List<Recibo>) reciboDao.findAll();
	}

	@Override
	public List<Carrito> listarCarrito() {
		
		return (List<Carrito>)iCarritoDao.findAll();
	}


	@Override
	public Carrito saveProductoParaVender(Carrito productoCarrito) {
		
		return iCarritoDao.save(productoCarrito);
	}

	@Override
	public void deleteItemCarrito(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void vaciarCarritoById(Long id) {
		// TODO Auto-generated method stub
		
	}


}
