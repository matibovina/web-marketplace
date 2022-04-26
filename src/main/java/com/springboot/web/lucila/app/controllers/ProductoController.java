package com.springboot.web.lucila.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.naming.LimitExceededException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.web.lucila.app.models.entity.Producto;
import com.springboot.web.lucila.app.models.services.IProductoService;
import com.springboot.web.lucila.app.models.services.IUploadFileService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ProductoController {

	private final Logger log = LoggerFactory.getLogger(ProductoController.class);

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IUploadFileService uploadService;

	@GetMapping("/productos")
	public List<Producto> listarProductos() {
		return (List<Producto>) productoService.findAll();
	}

	@GetMapping("/productos/page/{page}")
	public Page<Producto> listarClientesPageable(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return productoService.findAll(pageable);
	}

	@GetMapping("/productos/{id}")
	public Producto findProducto(@PathVariable Long id) {
		return productoService.findById(id);
	}
	
	@Secured("ROLE_ADMIN")
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
	
	@Secured("ROLE_ADMIN")
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
			response.put("mensaje", " Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("producto", productoActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> deleteProducto(@Valid @PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			Producto producto = productoService.findById(id);

			String nombreFotoAnterior = producto.getImagen();

			uploadService.eliminiar(nombreFotoAnterior);

			productoService.deleteById(id);

		} catch (DataAccessException e) {

			response.put("mensaje", "Error al eliminar al producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Producto eliminado con exito");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/productos/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

		Map<String, Object> response = new HashMap<>();

		Producto producto = productoService.findById(id);

		if (!archivo.isEmpty()) {

			String nombreArchivo = null;

			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen:");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreFotoAnterior = producto.getImagen();

			uploadService.eliminiar(nombreFotoAnterior);

			producto.setImagen(nombreArchivo);

			productoService.saveProducto(producto);

			response.put("producto", producto);
			response.put("mensaje", "Imagen subida correctamente: ".concat(nombreArchivo));
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("upload/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> mostrarImagen(@PathVariable String nombreImagen) {

		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreImagen);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 			recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/productos/uploads")
	public ResponseEntity<?> uploadImages(@RequestParam("archivos") MultipartFile[] archivos,
			@RequestParam("id") Long id) throws LimitExceededException {

		Map<String, Object> response = new HashMap<>();
		List<Path> rutaArchivo = new ArrayList<>();
		Producto producto = productoService.findById(id);

		String[] nombreArchivo = new String[archivos.length];

		if (!(archivos.length == 0)) {

			for (int i = 0; i < archivos.length; i++) {

				nombreArchivo[i] = UUID.randomUUID().toString().concat("_")
						.concat(archivos[i].getOriginalFilename().replace(" ", "_"));
				rutaArchivo.add(Paths.get("uploads").resolve(nombreArchivo[i]).toAbsolutePath());

				try {
					Files.copy(archivos[i].getInputStream(), rutaArchivo.get(i));
				} catch (IOException e) {
					response.put("mensaje", "Error al subir la imagen: ".concat(nombreArchivo[i]));
					response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}

			}

			producto.setImagenes(nombreArchivo);

			productoService.saveProducto(producto);

			response.put("producto", producto);
			response.put("mensaje", "Imagen subida correctamente");
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("uploads/img")
	public ResponseEntity<Resource> mostrarImagenes(@PathVariable String nombreImagen[]) {

		List<Path> rutaArchivo = new ArrayList<>();
		List<Resource> recursos = new ArrayList<>();
		HttpHeaders cabecera = new HttpHeaders();

		for (int i = 0; i < nombreImagen.length; i++) {
			rutaArchivo.add(Paths.get("uploads").resolve(nombreImagen[i]).toAbsolutePath());
			try {
				recursos.add(new UrlResource(rutaArchivo.get(i).toUri()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			if (!recursos.get(i).exists() && !recursos.get(i).isReadable()) {
				throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreImagen[i]);
			}

			cabecera.add(HttpHeaders.CONTENT_DISPOSITION,
					"attachment; filename=\"" + recursos.get(i).getFilename() + "\"");

		}

		return new ResponseEntity<Resource>((Resource) recursos, cabecera, HttpStatus.OK);
	}

}
