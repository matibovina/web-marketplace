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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.lucila.app.models.entity.Producto;
import com.springboot.web.lucila.app.models.services.IProductoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@GetMapping("/productos")
	public List<Producto> listarProductos() {
		return (List<Producto>) productoService.findAll();
	}
	
	@GetMapping("/productos/{id}")
	public Producto findProducto(@PathVariable Long id) {
		return productoService.findById(id);
	}

	@PostMapping("/productos")
	public ResponseEntity<?> createProducto(@Valid @RequestBody Producto producto, BindingResult result) {

		Map<String, Object> response = new HashMap<>();

		Producto nuevoProducto = null;

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			nuevoProducto = productoService.saveProducto(producto);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar el producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El producto fue creado con exito");
		response.put("producto", nuevoProducto);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping(value = "/productos/{id}")
	public ResponseEntity<?> updateProducto(@Valid @RequestBody Producto producto, BindingResult result,
			@PathVariable Long id) {

		Producto productoActual = productoService.findById(id);

		Producto productoActualizado = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (productoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el producto ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			productoActual.setNombre(producto.getNombre());
			productoActual.setCodigo(producto.getCodigo());
			productoActual.setIsDisponible(producto.getIsDisponible());
			productoActual.setExistencias(producto.getExistencias());
			productoActual.setPrecio(producto.getPrecio());

			productoActualizado = productoService.saveProducto(productoActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("producto", productoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> deleteProducto(@Valid @PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			productoService.deleteById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar al producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Producto eliminado con exito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

}
