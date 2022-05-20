package com.springboot.web.lucila.app.models.services;

import java.util.List;

import com.springboot.web.lucila.app.models.entity.Authority;

public interface IAuthorityService {
	
	Authority findByAuthority(String authority);

	Authority findById(Long id);

}
