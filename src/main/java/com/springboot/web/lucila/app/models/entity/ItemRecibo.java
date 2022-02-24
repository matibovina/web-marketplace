package com.springboot.web.lucila.app.models.entity;

import java.io.Serializable;

public class ItemRecibo implements Serializable {

	private Long id;
	
	private Long numeroRecibo;

	private Producto producto;

	private int cantidad;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Long getNumeroRecibo() {
		return numeroRecibo;
	}

	public void setNumeroRecibo(Long numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
	}


	private static final long serialVersionUID = 1L;

}
