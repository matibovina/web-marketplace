package com.springboot.web.lucila.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;import org.hibernate.boot.model.naming.ImplicitAnyDiscriminatorColumnNameSource;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email(message = "Formato de Email invalido, Ej: \"email@email.com\"")
	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false, unique = true)
	private String email;

	@NotEmpty(message = "No puede estar vacio")
	@Column(nullable = false, length = 20, unique = true)
	private String username;

	@Column(length = 60)
	@NotEmpty
	private String password;

	@NotNull
	private Boolean enabled;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Cliente cliente;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_authorities", joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})}) 
	private List<Authority> roles;

	public Usuario(Long id,
			@Email(message = "Formato de Email invalido, Ej: \"email@email.com\"") @NotEmpty(message = "No puede estar vacio") String email,
			@NotEmpty(message = "No puede estar vacio") String username, @NotEmpty String password,
			@NotNull Boolean enabled, Cliente cliente) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.cliente = cliente;
	}

	public Usuario() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Authority> getRoles() {
		return roles;
	}

	public void setRoles(List<Authority> roles) {
		this.roles = roles;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
