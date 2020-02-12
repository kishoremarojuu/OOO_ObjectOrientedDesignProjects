package com.carrentalooo.carrentalmytest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;

@Entity
public class Person {
	@Id
	@GeneratedValue
	private Integer PersonId;
	 @Size(min=6, max=25)
	@NotEmpty(message="Please enter Your name")
	private String name;
	 @Column(unique=true)
	 @Size(min=5, max=8)
	@NotEmpty(message="Please enter Your identificationNumber")
	private String identificationNumber;
	@Size(min=10, max=15)
	@NotEmpty(message="Please enter Your phoneNumber")
	private String phoneNumber;
	 //@Size(min=2, max=15)
	@NotEmpty(message="Please enter Your phoneNumber")
	@Email(message="Ivvalid email address")
	private String email;
	@Valid
	@Embedded
	private Address address;
	@Valid
	@OneToOne
	//@JoinColumn(name = "account_person_id", referencedColumnName = "accountId")
	@JoinColumn(name = "account_person_id")
	@JsonIgnore
	private Account account;
	
	@Override
	public String toString() {
		return "Person [PersonId=" + PersonId + ", name=" + name + ", identificationNumber=" + identificationNumber
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + ", address=" + address + ", account=" + account
				+ "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}  
	public Integer getPersonId() {
		return PersonId;
	}

	public void setPersonId(Integer personId) {
		PersonId = personId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Boolean isAdmin(){
		if(this.account.getAccountType().equals(AccountType.ADMIN)){
			return true;
		}
		else{
			return false;
		}
	}
	
}
