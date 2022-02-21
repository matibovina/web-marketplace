package com.bolsadeideas.springboot.web.lucila.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.web.lucila.app.models.entity.User;
import com.bolsadeideas.springboot.web.lucila.app.models.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping(value="/listar")
	public List<User> mostrarUsuarios(Model model ) {
		return userService.findAll();
	}
	
	@PostMapping("/users")
	public User save(@Valid @RequestBody User user) {
		
		System.out.println(user.getUsername());
		
		User newUser = userService.save(user);
		
	
		//	newUser = userService.save(user)
		return newUser;
	}
	
	@PutMapping("users/update/{id}")
	public @ResponseBody User update(@PathVariable Long id, @Valid @RequestBody User user ) {
		
		User userActual = userService.findById(id);
		User updatedUser = null;
		
		try {
			userActual.setUsername(user.getUsername());
			userActual.setEmail(user.getEmail());
			userActual.setPassword(user.getPassword());
			userActual.setEnabled(user.getEnabled());
			
			updatedUser = userService.save(userActual);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return updatedUser;
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			userService.delete(id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			response.put("mensaje", "Error al eliminar el cliente en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido eliminado con exito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	
	
}
