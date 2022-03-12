package com.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orders.common.Payment;
import com.orders.common.TransactionRequest;
import com.orders.common.TransactionResponse;
import com.orders.entity.Order;
import com.orders.repository.OrderRepository;
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private RestTemplate template;

	public TransactionResponse saveOrder(TransactionRequest request)
	{
		String response="";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		
		
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		orderRepo.save(order);
		
		//rest call
		Payment paymentResponse = template.postForObject("http://localhost:1234/payment/doPayment", payment, Payment.class);
		
		
		
		response = paymentResponse.getPaymentStatus().equals("success")?"payment processing successfull and order placed":"there is a failure in payment api, order added to cart";
		
		return new TransactionResponse(order, paymentResponse.getTransactionId(), paymentResponse.getAmount(), response);
		
		
	}

}
