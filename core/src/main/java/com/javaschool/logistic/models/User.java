package com.javaschool.logistic.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Getter
@Setter
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

	@Override
	public String toString() {
		return "User{" +
				"user_id=" + user_id +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", role=" + role +
				'}';
	}
}
