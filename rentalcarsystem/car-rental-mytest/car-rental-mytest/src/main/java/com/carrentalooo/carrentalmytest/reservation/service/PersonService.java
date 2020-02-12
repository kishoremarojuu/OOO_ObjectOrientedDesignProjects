package com.carrentalooo.carrentalmytest.reservation.service;


import com.carrentalooo.carrentalmytest.domain.Person;

import java.util.List;

public interface PersonService {
	void save(Person person);
	List<Person> getAll();
	void delete(Person person);
	void delete(int id);
	Person findById(int id);
	
}
