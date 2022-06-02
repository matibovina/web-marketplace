package com.springboot.web.lucila.app.models.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.web.lucila.app.models.dao.IAuthorityDao;
import com.springboot.web.lucila.app.models.dao.IUserDao;
import com.springboot.web.lucila.app.models.entity.Authority;
import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.entity.Usuario;

@Service
public class UserService implements IUserService, UserDetailsService {
	
	protected EntityManager em;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserDao userDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IAuthorityDao authorityDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = userDao.findByUsername(username);

		if (usuario == null) {
			logger.error("Error en el login: el usuario " + username + " no existe");
			throw new UsernameNotFoundException("Error en el login: el usuario " + username + " no existe");
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true,
				authorities);
	}

	@Override
	public Usuario save(Usuario user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) userDao.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}
//	@Transactional
//	@Override
//	public List<Authority> insertIntoRoles(Long UserId, Long RoleId) {
//		
//		return userDao.insertIntoRoles(UserId, RoleId);
//	}

	@Override
	@Query
	public void addRoles(Usuario user, Authority role) {
		user.addRole(role);
	} 
	
	

}
