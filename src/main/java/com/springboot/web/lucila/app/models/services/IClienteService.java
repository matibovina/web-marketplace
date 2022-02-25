package com.springboot.web.lucila.app.models.services;

import java.util.List;

import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.entity.Recibo;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente save(Cliente user);
	
	public Cliente findById(Long id);
		
	public List<Cliente> findByNombre(String nombre);
	
	public void delete(Long id);
	
	public Recibo saveRecibo(Recibo recibo);
	
	public Recibo findReciboById(Long id);
	
	public void deleteRecibo();
	
	public List<Recibo> listarRecibos();
	
	
}
