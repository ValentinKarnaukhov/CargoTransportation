package com.javaschool.logistic.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="USER")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_ID", nullable = false)
	private Integer user_id;


	@Column(name = "USERNAME", nullable = false)
	private String username;


	@Column(name="EMAIL", nullable=false)
	private String email;

	@Column(name="PASSWORD", nullable=false)
	private String password;

	@Column(name="ROLE",nullable = false)
	private String role;

	@Column(name="ENABLED",nullable = false)
	private boolean enabled;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
