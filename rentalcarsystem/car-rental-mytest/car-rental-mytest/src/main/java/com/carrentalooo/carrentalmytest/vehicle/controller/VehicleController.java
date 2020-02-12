package com.carrentalooo.carrentalmytest.vehicle.controller;

import com.carrentalooo.carrentalmytest.domain.Person;
import com.carrentalooo.carrentalmytest.domain.Vehicle;
import com.carrentalooo.carrentalmytest.vehicle.domain.VehicleSpec;
import com.carrentalooo.carrentalmytest.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/vehicle/")
@Controller
public class VehicleController {

	final private String URL = "/vehicle/";
	// private boolean testing = true;

	@RequestMapping(value = "vehicles", method = { RequestMethod.GET, RequestMethod.POST })
	public String vehicles(@ModelAttribute("vs") VehicleSpec vs, BindingResult result, HttpSession session,
						   Model model) {
		setRole(session, model);
		List<Vehicle> found = this.vehicleService.search(vs.getMinSeats(), vs.getMinPrice(), vs.getMaxPrice(), null);
		model.addAttribute("vehicles", found);
		return URL + "vehicles";
	}

	@GetMapping("detail/{vehicleId}")
	public String vehicleDetail(@PathVariable Integer vehicleId, Model model) {
		Vehicle vehicle = vehicleService.vehicleByVehicleId(vehicleId);
		model.addAttribute("vehicle", vehicle);
		return URL + "detail";
	}

	// @Secured("ROLE_ADMIN")
	@PostMapping("delete")
	public @ResponseBody String delete(int vehicleId, HttpSession session) {
		//authenticate(session);
		vehicleService.deleteVehicle(vehicleId);
		return "redirect:" + URL + "vehicles";
	}

	// @Secured("ROLE_ADMIN")
	@GetMapping("update/{vehicleId}")
	public String update(@PathVariable int vehicleId, HttpSession session, Model model) {
		// authenticate(session);
		Vehicle vehicle = this.vehicleService.find(vehicleId);
		model.addAttribute("updated", false);
		model.addAttribute("vehicle", vehicle);
		return URL + "update";
	}

	// @Secured("ROLE_ADMIN")
	@PostMapping("update")
	public String update(@Valid Vehicle vehicle, BindingResult result, HttpSession session, Model model) {

	//	authenticate(session);

		if (!result.hasErrors()) {
			vehicleService.update(vehicle);
			model.addAttribute("updated", true);
			model.addAttribute("vehicle", vehicle);
			model.addAttribute("available", vehicle.getIsAvailable() ? "YES" : "NO");
			return "redirect:" + URL + "vehicles";
		}
		model.addAttribute("updated", false);
		model.addAttribute("vehicle", vehicle);
		return URL + "update";
	}

	// @Secured("ROLE_ADMIN")
	@GetMapping("add")
	public String add(HttpSession session, Model model) {
		//authenticate(session);

		Vehicle vehicle = new Vehicle();
		vehicle.setIsAvailable(false);
		model.addAttribute("added", false);
		model.addAttribute("vehicle", vehicle);

		return URL + "add";
	}

	// @Secured("ROLE_ADMIN")
	@PostMapping("add")
	public String add(@Valid Vehicle vehicle, BindingResult result, HttpSession session, Model model) {

		//authenticate(session);

		if (!result.hasErrors()) {
			model.addAttribute("added", true);
			vehicleService.addVehicle(vehicle);
		} else {
			model.addAttribute("added", false);
			model.addAttribute("vehicle", vehicle);
		}
		return URL + "add";
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	private void setRole(HttpSession session, Model model) {
		/*
		 * if (testing) { model.addAttribute("isAdmin", true); }
		 */ if (session.getAttribute("person") != null) {
			model.addAttribute("isAdmin", ((Person) session.getAttribute("person")).isAdmin());
		} else {
			model.addAttribute("isAdmin", false);
		}
	}


	private void authenticate(HttpSession mySession) {
		if (mySession.getAttribute("person") == null || !((Person) mySession.getAttribute("person")).isAdmin()) {
			throw new RuntimeException("Not authenticated to do the operation.");
		}
	}

	@RequestMapping(value = "search", method = { RequestMethod.POST, RequestMethod.GET })
	public String searchVehicles(@ModelAttribute("vs") VehicleSpec vs, BindingResult result, HttpSession session,
			Model model) {
		setRole(session, model);

		List<Vehicle> found = this.vehicleService.search(vs.getMinSeats(), vs.getMinPrice(), vs.getMaxPrice(), null);
		model.addAttribute("vehicles", found);

		return URL + "search";
	}

	@Autowired
	private VehicleService vehicleService;

}
