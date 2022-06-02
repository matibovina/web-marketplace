package com.springboot.web.lucila.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.lucila.app.models.entity.Authority;
import com.springboot.web.lucila.app.models.entity.Carrito;
import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.entity.Usuario;
import com.springboot.web.lucila.app.models.services.IAuthorityService;
import com.springboot.web.lucila.app.models.services.IClienteService;
import com.springboot.web.lucila.app.models.services.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IAuthorityService authorityService;

	@GetMapping("/user")
	public List<Usuario> mostrarUsuarios() {
		return userService.findAll();
	}

	// @Secured({ "ROLE_ADMIN", "ROLE_USER" })
	/*
	 * 
	 * Metodo que crea a un usuario (que viene desde el lado del cliente), agrega
	 * roles y permisos y crea un cliente para ese usuario.
	 * 
	 */
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createUser(@Valid @RequestBody Usuario user, BindingResult result) {

		Usuario usuario = null;
		Cliente cliente = null;
		Authority roles = null;
		Carrito carrito = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			roles = authorityService.findById((long) 1);
			System.out.println(roles.getAuthority());
			userService.addRoles(user, roles);
			usuario = userService.save(user);
			cliente = new Cliente(usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(), usuario);
//			cliente.setUser(usuario);
//			cliente.setNombre(usuario.getNombre());
//			cliente.setApellido(usuario.getApellidos());
//			cliente.setEmail(usuario.getEmail());
			cliente = clienteService.save(cliente);
			usuario.setCliente(cliente);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos.");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getLocalizedMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println(user.getUsername());

		response.put("mensaje", "El usuario fue creado con exito");
		response.put("user", usuario);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PutMapping("user/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody Usuario user, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Usuario userActual = userService.findById(id);
		Usuario updatedUser = null;

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (userActual == null) {
			response.put("mensaje",
					"Error: el usuario con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			userActual.setEmail(user.getEmail());
			userActual.setPassword(user.getPassword());
			userActual.setEnabled(user.isEnabled());
			updatedUser = userService.save(userActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos.");
			response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getLocalizedMessage())));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado con exito!");
		response.put("user", updatedUser);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/user/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			userService.delete(id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			response.put("mensaje", "Error al eliminar el usuario de la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido eliminado con exito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
