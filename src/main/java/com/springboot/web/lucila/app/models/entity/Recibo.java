package com.springboot.web.lucila.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "recibos")
public class Recibo implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String descripcion;

	private String observaciones;
	
	@JsonIgnoreProperties({"recibos", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "recibo_id")
	@Column(name = "items_recibo")
	private List<Carrito> productosParaVender;
	
	@OneToMany(mappedBy = "recibo", cascade = CascadeType.ALL)
	private List<ProductoVendido> productosVendidos;

	public Recibo() {
		this.productosParaVender = new ArrayList<Carrito>();
		this.productosVendidos = new ArrayList<ProductoVendido>();
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

	public List<Carrito> getProductosParaVender() {
		return productosParaVender;
	}

	public void setProductosParaVender(List<Carrito> productosParaVender) {
		this.productosParaVender = productosParaVender;
	}

	public List<ProductoVendido> getProductosVendidos() {
		return productosVendidos;
	}

	public void setProductosVendidos(List<ProductoVendido> productosVendidos) {
		this.productosVendidos = productosVendidos;
	}



	public void addItemRecibo(Carrito item) {
		this.productosParaVender.add(item);
	}

	public Double calcularTotal() {

		Double total = 0.0;

		int size = productosParaVender.size();
		for (int i = 0; i < size; i++) {
			total += productosParaVender.get(i).calcularImporte();
		}

		return total;

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
