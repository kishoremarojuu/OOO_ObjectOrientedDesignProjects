package com.carrentalooo.carrentalmytest.user.Service;

import com.carrentalooo.carrentalmytest.domain.Account;
import com.carrentalooo.carrentalmytest.domain.Person;
import com.carrentalooo.carrentalmytest.user.DAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {

	@Autowired
	private PersonDAO personDAO;

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	/*public Person personByPersonId(Integer PersonId) {
		return this.personDAO.findByPersonId(PersonId);
	}*/
	
	public void addPerson(Person person){
		personDAO.save(person);
	}
	
	public List<Person> getAll(){
		return personDAO.findAll();
	}
	public Person findByIdentificationNumber(String identificationNumber){
		return personDAO.findByIdentificationNumber(identificationNumber);
	}
	
	public Person find(Integer PersonId){
		return personDAO.findOne(PersonId);
	}
	
	public List<Person> find(String name){
		return personDAO.findByNameIgnoringCase(name);
	} 
	public Person findByName(String name){
		return personDAO.findByName(name);
	}
	public void deletePerson(Integer PersonId) {
		personDAO.delete(PersonId);
		
	}

	public void updatePerson(Person person, Integer accountId) {
		personDAO.setPersonByPersonId(person.getName(), person.getIdentificationNumber(), person.getPhoneNumber(), person.getEmail(), person.getAddress().getCountry(), person.getAddress().getStreet(), person.getAddress().getCity(),person.getAddress().getState(), person.getAddress().getZip(), accountId, person.getPersonId());
		
	}
	/*public void updatePerson(Person person) {
		personDAO.setPersonByPersonId(person.getName(), person.getIdentificationNumber(), person.getPhoneNumber(), person.getEmail(),person.getAddress(),  person.getPersonId());
		
	}
	*/
	 
	
public Person getLoggedInPersonByAccount(Account account) {
		
		
		Person person = personDAO.getLoggedInPersonByAccount(account);
		System.out.println(account);
		System.out.println("pppppppppppppp"+person);
		/*if(person==null){
			 Person user = personDAO.findByAccount(account); 
			 System.out.println("uuuuuuu"+user);
			return user;
		}*/
		return person;
	}
}
