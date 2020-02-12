package com.carrentalooo.carrentalmytest.dao;

import com.carrentalooo.carrentalmytest.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ReservationDAO extends JpaRepository<Reservation, Integer> {

	Reservation findByReservationId(int id);
	@Transactional
	@Modifying
	@Query("Update Reservation Set reservationDateTime = :resDT, pickUpDateTime = :pUDT, returnDateTime = :rDT Where reservationId = :id")
	void update(@Param("resDT") Date resDateTime, @Param("pUDT") Date date, @Param("rDT") Date date2, @Param("id") int reservationId);
	
	
	@Query("select r from Reservation r order by reservation_id Desc ")
	List<Reservation> findAllOrderbyreservationIdDesc();
}
