package com.carrentalooo.carrentalmytest.reservation.service;

import com.carrentalooo.carrentalmytest.domain.Person;
import com.carrentalooo.carrentalmytest.user.DAO.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	PersonDAO personDAO;
	@Override
	public void save(Person person) {
		// TODO Auto-generated method stub
		personDAO.save(person);
	}

	@Override
	public List<Person> getAll() {
		// TODO Auto-generated method stub
		return personDAO.findAll();
	}

	@Override
	public void delete(Person person) {
		// TODO Auto-generated method stub
		personDAO.delete(person);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		personDAO.delete(id);
	}

	@Override
	public Person findById(int id) {
		// TODO Auto-generated method stub
		return personDAO.findOne(id);
	}

}
