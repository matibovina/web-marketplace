package com.springboot.web.lucila.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.hql.internal.ast.tree.BooleanLiteralNode;

@Entity
@Table(name = "productos")
public class Producto implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String codigo;
		
	private int existencias;
	
	private Boolean isDisponible;
	
	private String imagen;
	
	@Size(max = 200)
	private String imagenes[];
	
	private float precio;
	
	public Producto() {}
	
	public Producto(Long id, String nombre, String codigo, int existencias, Boolean isDisponible,
			String imagen, float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.existencias = existencias;
		this.isDisponible = isDisponible;
		this.imagen = imagen;
		this.precio = precio;
	}

	public Producto(Long id, String nombre, String codigo, int existencias, Boolean isDisponible, String imagen,
			String[] imagenes, float precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.codigo = codigo;
		this.existencias = existencias;
		this.isDisponible = isDisponible;
		this.imagen = imagen;
		this.imagenes = new String[4];
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setExistencias(int existencias) {
		this.existencias = existencias;
	}
	

	public Boolean getIsDisponible() {
		return isDisponible;
	}

	public void setIsDisponible(Boolean isDisponible) {
		this.isDisponible = isDisponible;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	 public void restarExistencia(Float existencias) {
	        this.existencias -= existencias;
	    }
	 
	 public void productoDisponible() {
		 
		 if(existencias <= 0) {
			 isDisponible = false;
		 } else {
			 isDisponible = true;
		}
		 
	 }
	
	public String[] getImagenes() {
		return imagenes;
	}

	public void setImagenes(String imagenes[]) {
		this.imagenes = imagenes;
	}

	private static final long serialVersionUID = 1L;

	
}
