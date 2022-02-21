package com.bolsadeideas.springboot.web.lucila.app.models.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.web.lucila.app.models.dao.IUserDao;
import com.bolsadeideas.springboot.web.lucila.app.models.entity.Cliente;
import com.bolsadeideas.springboot.web.lucila.app.models.entity.User;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDao userDao;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return (List<User>)userDao.findAll();
	}

	@Override
	public User save(User user) {
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
	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	

}
