package com.carrentalooo.carrentalmytest.payment;


import com.carrentalooo.carrentalmytest.domain.Payment;
import com.carrentalooo.carrentalmytest.domain.Reservation;

import java.util.List;

public interface PaymentService {
	public void addPayment(Payment payment, Reservation obj);

	public List<Payment> findPaymentByID(String paymentId);

	public List<Payment> findAllPayment();

	public void cancelPayment(String paymentId);

	public Payment getPaymentObject(String paymentId);

	public void paymentUpdated(Payment payment, double amount);

	public double findTotalAmount(List<Payment> list);

	public List<Payment> searchPaymentByCustomerName(String customerName);
}
