package com.cash.online.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.validation.constraints.Email;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6586683090248000207L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Size(max = 65)
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(max = 65)
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Email
	@Size(max = 100)
	@Column(unique = true)
	private String email;

    @OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
    private Set<Loan> loands;

    
	public User() {
	}

	
	public User(@NotNull @Size(max = 65) String firstName, @NotNull @Size(max = 65) String lastName,
			@NotNull @Email @Size(max = 100) String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Loan> getLoands() {
		return loands;
	}

	public void setLoands(Set<Loan> loands) {
		this.loands = loands;
	}
    
    

}
