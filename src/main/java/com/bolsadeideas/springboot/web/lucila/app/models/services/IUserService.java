package com.bolsadeideas.springboot.web.lucila.app.models.services;

import java.util.List;

import com.bolsadeideas.springboot.web.lucila.app.models.entity.Cliente;
import com.bolsadeideas.springboot.web.lucila.app.models.entity.User;

public interface IUserService {
	
	public List<User> findAll();
	
	public User save(User user);
	
	public User findById(Long id);
		
	public List<Cliente> findByNombre(String nombre);
	
	public void delete(Long id);

}
