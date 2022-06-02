package com.springboot.web.lucila.app.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.services.IClienteService;

// Controlador para CRUD de Cliente

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/clientes")
	public List<Cliente> listarClientes() {
		return (List<Cliente>) clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> listarClientesPageable(@PathVariable Integer page ) {
		Pageable pageable = PageRequest.of(page, 3);
		return clienteService.findAll(pageable);
	}
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> showCliente(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		Cliente cliente= null;

		try {
			
			cliente = clienteService.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al consultar la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);		
			}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);		

	}
	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping(value = {"/clientes", "/user/{id}"})
	public ResponseEntity<?> createCliente(
			@Valid @RequestBody Cliente cliente, BindingResult result) {

		Cliente nuevoCliente = null;
	//	Usuario user = userService.findById(user_id);

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			nuevoCliente = clienteService.save(cliente);
			nuevoCliente.setCreateAt(new Date());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	/*	if (user != null) {
			user.setCliente(nuevoCliente);
		} */
		response.put("cliente", nuevoCliente);
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PutMapping(value = {"/clientes/{id}"})
	public ResponseEntity<?> updateCliente(
			@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {

		Cliente clienteActual = clienteService.findById(id);

		Cliente clienteActualizado = null;

		//Usuario user = userService.findById(id);

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setFechaNacimiento(cliente.getFechaNacimiento());

			clienteActualizado = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	/*	if (user != null) {
			user.setCliente(clienteActualizado);
		} */

		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteCliente(@Valid @PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			clienteService.deleteClienteById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar al cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Cliente eliminado con exito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
