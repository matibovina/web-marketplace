package com.springboot.web.lucila.app.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.web.lucila.app.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

	@Query(nativeQuery = true, value = "SELECT id_cliente, nombre, apellido, email, telefono, fecha_nacimiento, create_at, user_id FROM clientes")
	List<Cliente> listaClientes();

	List<Cliente> findByNombre(String nombre);

}
