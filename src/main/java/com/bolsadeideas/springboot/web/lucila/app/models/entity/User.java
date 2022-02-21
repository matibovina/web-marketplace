package com.bolsadeideas.springboot.web.lucila.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User  implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@Email(message="Formato de Email invalido, Ej: \"email@email.com\"")
	//@NotEmpty(message= "No puede estar vacio")
	//@Column(nullable = false, unique=true)
	private String email;
	
	//@NotEmpty(message = "No puede estar vacio")
	//@Column(nullable = false, unique = true)
	private String username;
	
	@Column
	//@NotEmpty
	private String password;
	
	private Boolean enabled;
	
	/*@OneToOne
	private List<Cliente> cliente;*/
	


	public Long getId() {
		return idUser;
	}

	public void setId(Long idUser) {
		this.idUser = idUser;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	/*public List<Cliente> getCliente() {
		return cliente;
	}


	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}
	*/
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
