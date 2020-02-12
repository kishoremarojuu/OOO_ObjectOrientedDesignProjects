package com.carrentalooo.carrentalmytest.reservation.service;


import com.carrentalooo.carrentalmytest.domain.Reservation;

import java.util.List;

public interface ReservationService {
	void save(Reservation reservation);
	List<Reservation> getAll();
	void delete(Reservation reservation);
	void delete(int id);
	Reservation findById(int id);
	void update(Reservation res);
	
}
