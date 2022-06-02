package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.web.lucila.app.models.entity.Carrito;
import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.entity.Recibo;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public Cliente save(Cliente user);
	
	public Cliente findById(Long id);
		
	public List<Cliente> findByNombre(String nombre);
	
	public void deleteClienteById(Long id);
	
	public Recibo saveRecibo(Recibo recibo);
	
	public Recibo findReciboById(Long id);
	
	public void deleteReciboById(Long id);
	
	public List<Recibo> listarRecibos();
	
	public List<Carrito> listarCarrito();
	
	public Carrito saveProductoParaVender(Carrito productoCarrito);
	
	public void deleteItemCarrito(Long id);
	
	public void vaciarCarritoById(Long id);
	
	List<Cliente> findAllClientes();

	
}
