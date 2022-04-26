package com.springboot.web.lucila.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.web.lucila.app.models.entity.Usuario;

public interface IUserDao extends CrudRepository<Usuario, Long>{
	

	public Usuario findByUsernameAndEmail(String username, String email);
	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario buscarPorUsuario(String username, String email);

	
}
