package com.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

//import com.orders.common.Payment;
import com.orders.common.TransactionRequest;
import com.orders.common.TransactionResponse;
//import com.orders.entity.Order;
import com.orders.service.OrderService;

@RestController @RequestMapping("/order")
public class OrderController {
	
	@Autowired
	public OrderService service;
	
	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request)
	{
	
		return service.saveOrder(request);
	}
	}
