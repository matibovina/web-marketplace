package com.springboot.web.lucila.app.models.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springboot.web.lucila.app.models.entity.Authority;
import com.springboot.web.lucila.app.models.entity.Usuario;

public interface IUserDao extends CrudRepository<Usuario, Long>{
	

	public Usuario findByUsernameAndEmail(String username, String email);
	
	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario buscarPorUsuario(String username, String email);
	
	
	//@Query(value = "INSERT INTO users_authorities (user_id, role_id) VALUES (?1, ?2)", nativeQuery = true)
	//public void addRoles(Long UserId, Long RoleId);
	
	
	
}
