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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.lucila.app.models.entity.Producto;
import com.springboot.web.lucila.app.models.entity.Carrito;
import com.springboot.web.lucila.app.models.services.IClienteService;
import com.springboot.web.lucila.app.models.services.IProductoService;
import com.springboot.web.lucila.app.models.services.IProductoVendidoService;

@RestController
@RequestMapping("/api")
public class CarritoController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IProductoService productoService;

//	@Autowired
//	private IProductoVendidoService productoVendidoService;
	
	@GetMapping("/carrito")
	public List<Carrito> listarCarrito() {
		return (List<Carrito>) clienteService.listarCarrito();
	}
	
	@PostMapping("/carrito/{id}")
	public ResponseEntity<?> guardarProductoParaVender(BindingResult result, 
			@RequestBody Producto producto,
			@PathVariable (value = "producto_id") Long id,
			@RequestParam(name = "item_id", required = false) Long itemId,
			@RequestParam(name = "cantidad", required = false) Integer cantidad
			) {
			
			Map<String, Object> response = new HashMap<>();
			
			Producto p = productoService.findById(id);
			
			Carrito carrito = null;
					
				if(result.hasErrors()) {
					List<String> errors = result.getFieldErrors()
							.stream()
							.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
							.collect(Collectors.toList());
					
					response.put("errors", errors);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
				
				if(itemId == null || itemId == 0) {
					response.put("mensaje", "No hay ningun producto seleccionado.");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
				
				if(p.getExistencias()>=0) {
					response.put("mensaje", "No hay stock suficiente de este producto");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
				
				try {
					carrito.setProducto(p);
					carrito.setCantidad(cantidad);
					carrito.calcularImporte();
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al agregar el producto al carrito");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				response.put("mensaje", p.getNombre().concat(" agregado al carrito con exito"));
				response.put("carrito", carrito);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	

}
