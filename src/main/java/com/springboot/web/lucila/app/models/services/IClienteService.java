package com.springboot.web.lucila.app.models.services;

import java.util.List;

import com.springboot.web.lucila.app.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente save(Cliente user);
	
	public Cliente findById(Long id);
		
	public List<Cliente> findByNombre(String nombre);
	
	public void delete(Long id);
}
