package com.springboot.web.lucila.app.models.services;

import java.util.List;
import java.util.Set;

import com.springboot.web.lucila.app.models.entity.Authority;
import com.springboot.web.lucila.app.models.entity.Usuario;

public interface IUserService {
	
	public Usuario findByUsername(String username);
	
	public List<Usuario> findAll();
	
	public Usuario save(Usuario user);
	
	public Usuario findById(Long id);
			
	public void delete(Long id);
	
	public void addRoles(Usuario user, Authority role);
	
}
