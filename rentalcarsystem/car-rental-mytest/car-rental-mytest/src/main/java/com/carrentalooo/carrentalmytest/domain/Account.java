package com.carrentalooo.carrentalmytest.domain;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Account { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer accountId;
	@Column(unique=true)
	 @Size(min=4, max=15)
	@NotEmpty(message="Please enter Your username")
	private String username;
	//@Size(min=6, max=10)
	@NotEmpty(message="Please enter Your password")
	private String password;
	private Boolean active;
	 
	@Enumerated(EnumType.STRING)
	private AccountType accountType; 
	 
	@OneToOne(mappedBy = "account")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Person personOwner;
	public Integer getAccountId() {
		return accountId;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", username=" + username + ", password=" + password + ", active="
				+ active + ", accountType=" + accountType + "]";
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public Person getPersonOwner() {
		return personOwner;
	}
	public void setPersonOwner(Person personOwner) {
		this.personOwner = personOwner;
	}

	 

}
