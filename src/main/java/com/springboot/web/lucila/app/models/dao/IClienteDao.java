package com.springboot.web.lucila.app.models.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;

import com.springboot.web.lucila.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{
	
	@Query(nativeQuery = true, value = "SELECT id_cliente, nombre, apellido, email, telefono, fecha_nacimiento, create_at, user_id FROM clientes")
	List<Cliente> listaClientes();
	

	List<Cliente> findByNombre(String nombre);

	
}
