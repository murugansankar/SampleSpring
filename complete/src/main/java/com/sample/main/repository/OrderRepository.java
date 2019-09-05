package com.sample.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.main.model.OrderDetail;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetail, Long> {
	OrderDetail findByOrderId(Long orderId);
	Optional<OrderDetail> findByBorrowerName(String borrowerName);


}
