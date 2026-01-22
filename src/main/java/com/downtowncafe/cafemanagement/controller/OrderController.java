package com.downtowncafe.cafemanagement.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downtowncafe.cafemanagement.model.OrderEntity;
import com.downtowncafe.cafemanagement.repository.OrderRepository;

@RestController
@RequestMapping("api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    @PostMapping 
    public OrderEntity placeOrder(@RequestBody OrderEntity order) {
        order.setStatus("PENDING");
        order.setOrderTime(LocalDateTime.now());
        return repo.save(order);
    }

    @GetMapping
    public List<OrderEntity> getOrders() {
        return repo.findAll();
    }
}
