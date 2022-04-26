package com.springboot.web.lucila.app.models.services;

import java.util.List;

import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.entity.Usuario;

public interface IUserService {
	
	public Usuario findByUsername(String username);
	
	public List<Usuario> findAll();
	
	public Usuario save(Usuario user);
	
	public Usuario findById(Long id);
		
	public List<Cliente> findByNombre(String nombre);
	
	public void delete(Long id);
	
	public void saveCliente(Cliente cliente);

}
