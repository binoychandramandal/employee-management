package com.employee.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotBlank
	@Size(max = 30)
	private String username;

	@NotBlank
	@Size(max = 40)
	private String name;

	@NotBlank
	private char gender;

	@NotBlank
	private String dob;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 10)
	private String phone;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	private Timestamp createAt;

	@NotBlank
	private String createdBy;

	private String address;

	private byte deleted;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

//	@OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Customer> customers = new HashSet<>();

//	public void addCustomer(Customer customer) {
//		customers.add(customer);
//		customer.setUser(this);
//	}
//
//	public void removeCustomer(Customer customer) {
//		customers.remove(customer);
//		customer.setUser(null);
//	}

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(String username, String name, char gender, String email, String phone,
			String password, Timestamp createAt, String createdBy,String dob) {
		this.username = username;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.createAt = createAt;
		this.createdBy = createdBy;
		this.dob=dob;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	

}
