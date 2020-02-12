package com.carrentalooo.carrentalmytest.payment;

import com.carrentalooo.carrentalmytest.domain.Payment;
import com.carrentalooo.carrentalmytest.domain.Person;
import com.carrentalooo.carrentalmytest.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	PaymentService paymentService;

	@RequestMapping(value = "/add-payment", method = RequestMethod.GET)
	public String addPayment(Payment payment, HttpSession sessionAdd, Model model) {
		System.out.println("shova"+sessionAdd.getAttribute("totalPriceSession"));
		sessionAdd.setAttribute("amountValue", sessionAdd.getAttribute("totalPriceSession"));
		
		return "payment/add-payment";
	}

	@RequestMapping(value = "/pay-bill", method = RequestMethod.POST)
	public String payBill(@Valid Payment payment, BindingResult bindingResult, HttpSession sessionReservation) {
		if (bindingResult.hasErrors())
			return "payment/add-payment";
		Reservation reservationObject = (Reservation) sessionReservation.getAttribute("reservationObject");
		paymentService.addPayment(payment, reservationObject);
		return "redirect:view-all-payment";
	}

	@RequestMapping(value = "/view-payment/{paymentid}", method = RequestMethod.GET)
	public String viewPayment(@PathVariable("paymentid") String paymentid, Model model, HttpSession mySession) {
		Person p=(Person) mySession.getAttribute("person");
		
		if (mySession.getAttribute("person") != null) {
			model.addAttribute("isAdmin", ((Person) mySession.getAttribute("person")).isAdmin());
		} else {
			model.addAttribute("isAdmin", false);
		}
		
		List<Payment> paymentList = paymentService.findPaymentByID(paymentid);
		model.addAttribute("paymentList", paymentList);
		return "payment/view-payment";
	}

	@RequestMapping(value = "/view-all-payment", method = RequestMethod.GET)
	public String viewAllPayment(Model model, HttpSession viewSession) {
		List<Payment> paymentLst = paymentService.findAllPayment();
		Person p=(Person) viewSession.getAttribute("person");	
		System.out.println("kishoremarojuu"+ p.getName());
		System.out.println("kishoremarojuu"+ p.getAccount().getAccountType());
		if (viewSession.getAttribute("person") != null) {
			model.addAttribute("isAdmin", ((Person) viewSession.getAttribute("person")).isAdmin());
		} else {
			model.addAttribute("isAdmin", false);
		}
		System.out.println("kishoremarojuu "+ p);
		model.addAttribute("paymentList", paymentLst);
		model.addAttribute("totalAmount", paymentService.findTotalAmount(paymentLst));
		return "payment/view-all-payments";
	}

	@RequestMapping(value = "/cancel-payment/{paymentid}", method = RequestMethod.GET)
	public String cancelPayment(@PathVariable("paymentid") String paymentid) {
		paymentService.cancelPayment(paymentid);
		return "redirect:/payment/view-all-payment";

	}

	@RequestMapping(value = "/update-payment/{paymentid}", method = RequestMethod.GET)
	public String updatePayment(@PathVariable("paymentid") String paymentid, Model model, HttpSession sessionObj) {
		sessionObj.setAttribute("paymentObject", paymentService.getPaymentObject(paymentid));
		model.addAttribute("paymentObject", paymentService.getPaymentObject(paymentid));
		return "payment/update-payment-form";

	}

	@RequestMapping(value = "/payment-updated", method = RequestMethod.POST)
	public String paymentUpdated(@Valid @ModelAttribute("payment") Payment payment, BindingResult bindingResult,
			@Param("amount") double amount, Model model, HttpSession sessionObj) {
		Payment paymentObject = (Payment) sessionObj.getAttribute("paymentObject");
		paymentService.paymentUpdated(paymentObject, amount);
		return "redirect:/payment/view-all-payment";

	}

	// search list of payments based on customer name
	@RequestMapping(value = "/search-payment", method = RequestMethod.POST)
	public String paymentSearched(@Param("customerName") String customerName, Model model) {
		List<Payment> paymentList = paymentService.searchPaymentByCustomerName(customerName);
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("totalAmount", paymentService.findTotalAmount(paymentList));
		return "payment/view-all-payments";

	}
}
