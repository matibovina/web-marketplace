package com.springboot.web.lucila.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PrePersist;

public class Recibo implements Serializable {

	private Long id;

	private Date fecha;

	private String descripcion;

	private String observaciones;

	private Cliente cliente;

	private List<ItemRecibo> itemRecibo;

	public Recibo() {
		this.itemRecibo = new ArrayList<ItemRecibo>();
	}

	@PrePersist
	public void prePersist() {
		fecha = new Date();
	}

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

	public List<ItemRecibo> getItemRecibo() {
		return itemRecibo;
	}

	public void setItemRecibo(List<ItemRecibo> itemRecibo) {
		this.itemRecibo = itemRecibo;
	}

	public void addItemRecibo(ItemRecibo item) {
		this.itemRecibo.add(item);
	}

	public Double calcularTotal() {

		Double total = 0.0;

		int size = itemRecibo.size();
		for (int i = 0; i < size; i++) {
			total += itemRecibo.get(i).calcularImporte();
		}

		return total;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
