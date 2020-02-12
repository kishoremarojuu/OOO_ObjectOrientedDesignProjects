package com.carrentalooo.carrentalmytest.payment;

import com.carrentalooo.carrentalmytest.domain.Payment;
import com.carrentalooo.carrentalmytest.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {
	@Autowired
	PaymentDAO paymentDao;

	@Override
	public void addPayment(Payment payment, Reservation obj) {
		payment.setPaymentDateTime(new Date());
		payment.setReservation(obj);
		payment.setIsConfirm("PAID");
		paymentDao.save(payment);
	}

	@Override
	public List<Payment> findPaymentByID(String paymentId) {
		List<Payment> paymentList = paymentDao.findByPaymentId(Integer.parseInt(paymentId));
		return paymentList;
	}

	@Override
	public List<Payment> findAllPayment() {
		List<Payment> paymentList = paymentDao.findAll();
		return paymentList;
	}

	@Override
	public void cancelPayment(String paymentId) {
		List<Payment> paymentList = paymentDao.findByPaymentId(Integer.parseInt(paymentId));
		for (Payment p : paymentList) {
			if (paymentId.equals(p.getPaymentId() + "")) {
				paymentDao.delete(p);
			}
		}
	}

	@Override
	public double findTotalAmount(List<Payment> list) {
		double amount = 0.0;
		for (Payment p : list)
			amount += p.getAmount();
		return amount;
	}

	@Override
	public Payment getPaymentObject(String paymentId) {
		List<Payment> paymentList = paymentDao.findByPaymentId(Integer.parseInt(paymentId));
		for (Payment p : paymentList) {
			if (paymentId.equals(p.getPaymentId() + "")) {
				return p;
			}
		}
		return null;
		
	}

	@Override
	public void paymentUpdated(Payment payment, double amount) {
		if (payment != null) {
			payment.setAmount(amount);
			paymentDao.save(payment);
		}

	}

	@Override
	public List<Payment> searchPaymentByCustomerName(String customerName) {
		List<Payment> paymentList = new ArrayList<>();
		for (Payment p : paymentDao.findAll()) {
			if (p.getReservation().getPerson().getName().toLowerCase().startsWith(customerName.toLowerCase()))
				paymentList.add(p);
		}
		return paymentList;
	}

}
