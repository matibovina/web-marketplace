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
import com.springboot.web.lucila.app.models.entity.Cliente;
import com.springboot.web.lucila.app.models.services.ICarritoService;
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

	@Autowired
	private ICarritoService carritoService;

//	@Autowired
//	private IProductoVendidoService productoVendidoService;
	
	/*
	 *Muestra los items en el carrito del cliente
	 * 
	 */
	@GetMapping({"/carrito/{cliente-id}", "/carrito"})
	public List<Carrito> listarCarrito(@Valid @PathVariable(value = "cliente-id") Long clienteId) {
		return (List<Carrito>) carritoService.listarCarrito(clienteId);
	}
	/*
	 * Guarda el producto (tipo cantidad etc) en el carrito del cliente
	 */
	
	@PostMapping("/carrito/{cliente-id}")
	public ResponseEntity<?> guardarProductoParaVender(BindingResult result, 
			@RequestBody Producto producto,
			@PathVariable (value = "cliente_id") Long id,
			@RequestParam(name = "cantidad", required = false) Integer cantidad
			) {
			
			Map<String, Object> response = new HashMap<>();
			
			Producto p = null;
			
			Carrito carrito = null;
			
			Cliente cliente = null; 
			
					
				if(result.hasErrors()) {
					List<String> errors = result.getFieldErrors()
							.stream()
							.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
							.collect(Collectors.toList());
					
					response.put("errors", errors);
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
				
				try {
					p = new Producto();
					p = productoService.findById(producto.getId());
					cliente = clienteService.findById(id);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				if(p.getExistencias()>=0) {
					response.put("mensaje", "No hay stock suficiente de este producto");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				}
				
				try {
					carrito = new Carrito();
					carrito.setCantidad(cantidad);
					carrito.setCliente(cliente);
					carrito.setProducto(p);
					carritoService.saveCarrito(carrito);
				} catch (DataAccessException e) {
					response.put("mensaje", "Error al agregar el producto al carrito");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				response.put("mensaje", producto.getNombre().concat(" agregado al carrito con exito"));
				response.put("carrito", carrito);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	
	

}
