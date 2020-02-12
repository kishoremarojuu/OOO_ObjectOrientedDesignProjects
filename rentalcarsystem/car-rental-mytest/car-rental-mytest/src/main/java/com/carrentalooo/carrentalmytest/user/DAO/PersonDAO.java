package com.carrentalooo.carrentalmytest.user.DAO;

import com.carrentalooo.carrentalmytest.domain.Account;
import com.carrentalooo.carrentalmytest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonDAO extends  JpaRepository<Person, Integer> {

	public abstract List<Person> findByNameIgnoringCase(String name);
 
	public abstract Person findByIdentificationNumber(String identificationNumber);
	public abstract Person findByName(String name);
	public abstract Person findByAccount(Account account);
	public abstract Person getLoggedInPersonByAccount(Account account);

	List<Person> findAll();
	
	@Modifying
	@Query("update Person p set p.name = ?1, p.identificationNumber = ?2, p.phoneNumber =?3, p.email = ?4, p.address.country = ?5, p.address.street = ?6, p.address.city = ?7, p.address.state = ?8, p.address.zip = ?9 ,p.account.accountId =?10  where PersonId =?11")
	public abstract void setPersonByPersonId(String name, String identificationNumber, String phoneNumber, String email, String country, String street, String city, String state, String zip, Integer accountId, Integer personId);

	/*@Modifying
	@Query("update Person p set p.name = ?1, p.identificationNumber = ?2, p.phoneNumber =?3, p.email = ?4, p.address = ?5   where PersonId =?6")
	public abstract void setPersonByPersonId(String name, String identificationNumber, String phoneNumber, String email, Address address,  Integer personId);

	*/
	
	Person getLoggedInPersonByName(String name);
}