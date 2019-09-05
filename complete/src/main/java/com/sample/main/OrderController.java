package com.sample.main;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.main.model.Order;
import com.sample.main.model.OrderDetail;
import com.sample.main.repository.OrderRepository;

@RestController
@RequestMapping("/api/auth/order")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@PostMapping("/process")
	ResponseEntity<String> placeOrder(@Valid @RequestBody Order order)
	{
		OrderDetail ordr = new OrderDetail();
		ordr.setBasePrice(order.getBasePrice());
		ordr.setBorrowerName(order.getBorrowerName());
		ordr.setInterest(order.getInterest());
		ordr.setTotalAmt(order.getTotalAmt());
		orderRepository.save(ordr);
		
		return ResponseEntity.ok().body(ordr.getBorrowerName());
	}
	
	@PutMapping("/fetch/{orderId}")
	@ResponseBody
	OrderDetail fetchDetail(@PathVariable("orderId") Long orderId)
	{
		OrderDetail order = orderRepository.findByOrderId(orderId);
		return order;
	}
	

}
