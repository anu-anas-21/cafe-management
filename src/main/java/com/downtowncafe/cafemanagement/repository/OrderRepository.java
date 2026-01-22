package com.downtowncafe.cafemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.downtowncafe.cafemanagement.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
