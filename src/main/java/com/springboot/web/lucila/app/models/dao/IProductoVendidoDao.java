package com.springboot.web.lucila.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.springboot.web.lucila.app.models.entity.ProductoVendido;

public interface IProductoVendidoDao extends CrudRepository<ProductoVendido, Long>{

}
