package com.springboot.web.lucila.app.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.web.lucila.app.models.dao.IAuthorityDao;
import com.springboot.web.lucila.app.models.entity.Authority;

@Service
public class AutorityServiceImpl implements IAuthorityService {
	
	@Autowired
	private IAuthorityDao authorityDao;
	
	@Override
	public Authority findByAuthority(String authority) {
		// TODO Auto-generated method stub
		return (Authority)authorityDao.findAll();
	}
	
	
	

	@Override
	public Authority findById(Long id) {
		return authorityDao.findById(id).orElse(null);
	} 

}
