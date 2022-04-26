package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.web.lucila.app.models.dao.IClienteDao;
import com.springboot.web.lucila.app.models.dao.IUserDao;
import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.entity.Usuario;

@Service
public class UserServiceImpl  {

/*	@Autowired
	private IUserDao userDao;

	@Autowired
	private IClienteDao clienteDao;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) userDao.findAll();
	}

	@Override
	public Usuario save(Usuario user) {
		return userDao.save(user);
	}

	@Override
	public List<Cliente> findByNombre(String nombre) {

		return null;
	}

	@Override
	public void delete(Long id) {

		userDao.deleteById(id);

	}

	@Override
	public Usuario findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	@Override
	public void saveCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		 clienteDao.save(cliente);
	}

	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
