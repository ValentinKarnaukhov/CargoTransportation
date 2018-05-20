package com.javaschool.logistic.model;





import java.io.Serializable;


import javax.persistence.*;



@Entity
@Table(name="USER")
public class User implements Serializable{



	public enum Role{
		ADMIN,
		MANAGER,
		DRIVER
	}

	@Id
	@GeneratedValue
	@Column(name = "user_id", nullable = false)
	private int user_id;


	@Column(name = "username", nullable = false)
	private String username;


	@Column(name="email", nullable=false, unique = true)
	private String email;

	@Column(name="password", nullable=false)
	private String password;


	@Enumerated(EnumType.STRING)
	@Column(name="role",nullable = false)
	private Role role = Role.MANAGER;

	@Column(name="enabled",nullable = false)
	private boolean enabled = true;

	@OneToOne(mappedBy="user")
	private Driver driver;



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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}


}
