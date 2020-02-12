package com.carrentalooo.carrentalmytest.reservation.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.carrentalooo.carrentalmytest.domain.Person;
import com.carrentalooo.carrentalmytest.domain.Reservation;
import com.carrentalooo.carrentalmytest.domain.Vehicle;
import com.carrentalooo.carrentalmytest.reservation.service.ReservationService;
import com.carrentalooo.carrentalmytest.user.Service.PersonService;
import com.carrentalooo.carrentalmytest.vehicle.dao.VehicleDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//vhicle controller
@RequestMapping("/reservation/")
@Controller
public class ReservationController {
	final private String URL = "/reservation/";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	private Logger logger = Logger.getLogger(ReservationController.class);
	@Autowired
	ReservationService reservationService;
	@Autowired
	VehicleDAO vehicleService;
	@Autowired
	PersonService personService;

	@GetMapping("add/{carid}")
	public String showForm(@PathVariable("carid") int carNumber, Reservation reservation, Model model) {
		model.addAttribute("carNumber", carNumber);
		return "addreservation";
	}

	@PostMapping("add/{carid}")
	public String add(@PathVariable("carid") int carNumber, @ModelAttribute Reservation reservation, Model model,
			BindingResult bindingResult, HttpSession sessionRev, @Param("addPayment") String addPayment) {
		// Person person = (Person) session.getAttribute("person");
		Vehicle vehicle = vehicleService.findByVehicleId(carNumber);
		//Person person = personService.findById(1);
		Person person = (Person) sessionRev.getAttribute("person");
		reservation.setPerson(person);
		reservation.setVehicle(vehicle);
		reservationService.save(reservation);
		vehicle.setIsAvailable(false);
		vehicleService.save(vehicle);
		sessionRev.setAttribute("reservationObject", reservation);
		double totalDay = reservation.getReturnDateTime().getDay() - reservation.getPickUpDateTime().getDay();
		double dayPrice = vehicle.getDailyPrice();
		double totalPrice = totalDay * dayPrice;
		System.out.println("mum"+totalPrice);
		sessionRev.setAttribute("totalPriceSession", totalPrice);
		if(addPayment.equals("Yes")){
			return "redirect:/payment/add-payment";
		}
		model.addAttribute("reservations", reservationService.getAll());
		return "redirect:/reservation/list";
	}

	@PostMapping("update/{carid}")
	public String update(@PathVariable("carid") int carNumber, @ModelAttribute Reservation reservation, Model model,
			BindingResult bindingResult, HttpSession session) {
		// Person person = (Person) session.getAttribute("person");
		Reservation res = reservationService.findById(reservation.getReservationId());
		res.setPickUpDateTime(reservation.getPickUpDateTime());
		res.setReservationDateTime(reservation.getReservationDateTime());
		res.setReturnDateTime(reservation.getReturnDateTime());
		reservationService.update(reservation);
		return "redirect:/reservation/list";
	}

	//Spring MVC
	/*@GetMapping("list")
	public String showList(Model model) {
		model.addAttribute("reservations", reservationService.getAll());
		return "reservationList";
	}*/


	@GetMapping("list")
	public ResponseEntity<?> listUsers() {
		return new ResponseEntity<>(reservationService.getAll(), HttpStatus.FOUND);
	}

 //springMVC
	@GetMapping("delete/{resid}")
	public String delete(@PathVariable("resid") int resId) {
		Reservation reservation = reservationService.findById(resId);
		Vehicle vehicle =  reservation.getVehicle();
		vehicle.setIsAvailable(true);
		vehicleService.save(vehicle);
		reservationService.delete(resId);

		return "redirect:/reservation/list";
	}
	/*//Springboot
	@DeleteMapping("delete/{reservationid}")
	public ResponseEntity<?> deleteReservation(@PathVariable("reservationid") int reservationid){
		try{
		Reservation reservation = reservationService.findById(reservationid);
		Vehicle vehicle = reservation.getVehicle();
		vehicle.setIsAvailable(true);
		vehicleService.save(vehicle);
		return new ResponseEntity<>(reservationService.delete(reservationid), HttpStatus.ACCEPTED);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>()
	}
*/
	@GetMapping("edit/{resid}")
	public String edit(@PathVariable("resid") int resId, Model model) {
		Reservation res = reservationService.findById(resId);
		model.addAttribute("carNumber", res.getVehicle().getVehicleId());
		model.addAttribute("reservation", res);
		return "editReservation";
	}


}
