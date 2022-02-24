package com.springboot.web.lucila.app.models.entity;

import java.io.Serializable;
import java.util.Date;

public class Recibo  implements Serializable{

	
	private Long id;
	
	private Date fecha;
	
	private String descripcion;
	
	private String observaciones;
	
	private Cliente cliente;
	
	private ItemRecibo itemRecibo;
	
	private Float total;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public ItemRecibo getItemRecibo() {
		return itemRecibo;
	}

	public void setItemRecibo(ItemRecibo itemRecibo) {
		this.itemRecibo = itemRecibo;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
