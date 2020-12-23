package com.bhaskar.singh.domain.security;

import java.io.Serializable;

import javax.persistence.*;

import com.bhaskar.singh.entity.User;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 12312122L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_role_id_seq")
	@SequenceGenerator(name="user_role_id_seq", sequenceName="user_role_id_seq", allocationSize=1)
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	public UserRole() {}
	
	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

}
