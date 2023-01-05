package com.employee.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;
	public Role(ERole name) {
		this(0,name);
	}
	public Role() {

	}
	public Role(Integer id, ERole name) {
		this.id = id;
		this.name = name;
	}
}