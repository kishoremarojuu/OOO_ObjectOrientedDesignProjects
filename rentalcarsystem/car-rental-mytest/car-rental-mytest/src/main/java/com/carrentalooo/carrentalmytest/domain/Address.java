package com.carrentalooo.carrentalmytest.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	 @Size(min=2, max=15)
	@NotEmpty(message="Please enter Your Country")
	private String country;
	 @Size(min=3, max=25)
	@NotEmpty(message="Please enter Your street")
	private String street;
	 @Size(min=4, max=15)
	@NotEmpty(message="Please enter Your city")
	private String city;
	 @Size(min=2, max=10)
	@NotEmpty(message="Please enter Your state")
	private String state;
	@Size(min=4, max=6)
	@NotEmpty(message="Please enter Your zip")
	private String zip;

	@Override
	public String toString() {
		/*return "Address [country=" + country + ", street=" + street + ", city=" + city + ", state=" + state + ", zip="
				+ zip + "]";*/
		return street+" "+city+" "+state+" "+zip+" "+country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
