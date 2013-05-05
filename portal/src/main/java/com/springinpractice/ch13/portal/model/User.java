/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Entity
@Table(name = "user")
@SuppressWarnings("serial")
public class User implements UserDetails {
	private static final GrantedAuthority USER_AUTH = new SimpleGrantedAuthority("ROLE_USER");
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Transient
	private List<GrantedAuthority> authorities;
	
	public User() {
		this.authorities = new ArrayList<GrantedAuthority>();
		authorities.add(USER_AUTH);
	}
	
	public Long getId() { return id; }
	
	@Override
	public String getUsername() { return username; }

	@Override
	public String getPassword() { return password; }
	
	public String getFirstName() { return firstName; }
	
	public String getLastName() { return lastName; }
	
	public String getFirstNameLastName() { return firstName + " " + lastName; }
	
	public String getEmail() { return email; }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
	
	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return true; }

	@Override
	public int hashCode() {
		final int prime = 17;
		int result = 1;
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { return true; }
		if (obj == null) { return false; }
		if (getClass() != obj.getClass()) { return false; }
		
		User other = (User) obj;
		
		if (authorities == null) {
			if (other.authorities != null) { return false; }
		} else if (!authorities.equals(other.authorities)) {
			return false;
		}
		
		if (email == null) {
			if (other.email != null) { return false; }
		} else if (!email.equals(other.email)) {
			return false;
		}
		
		if (firstName == null) {
			if (other.firstName != null) { return false; }
		} else if (!firstName.equals(other.firstName)) {
			return false;
		}
		
		if (id == null) {
			if (other.id != null) { return false; }
		} else if (!id.equals(other.id)) {
			return false;
		}
		
		if (lastName == null) {
			if (other.lastName != null) { return false; }
		} else if (!lastName.equals(other.lastName)) {
			return false;
		}
		
		if (password == null) {
			if (other.password != null) { return false; }
		} else if (!password.equals(other.password)) {
			return false;
		}
		
		if (username == null) {
			if (other.username != null) { return false; }
		} else if (!username.equals(other.username)) {
			return false;
		}
		
		return true;
	}
}
