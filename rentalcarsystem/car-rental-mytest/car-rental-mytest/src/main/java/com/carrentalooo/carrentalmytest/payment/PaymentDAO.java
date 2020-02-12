package com.carrentalooo.carrentalmytest.payment;

import com.carrentalooo.carrentalmytest.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PaymentDAO extends JpaRepository<Payment, Long> {
	List<Payment> findByPaymentId(int paymentId);
	Page<Payment> findAll(Pageable pageable);
	
	
}
