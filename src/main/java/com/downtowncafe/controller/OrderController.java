package com.downtowncafe.controller;

import com.downtowncafe.dto.OrderDTO;
import com.downtowncafe.dto.MenuItemDTO;
import com.downtowncafe.entity.CustomerOrder;
import com.downtowncafe.entity.OrderItem;
import com.downtowncafe.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    public List<OrderDTO> getOrders() {
        return orderRepository.findAllByOrderByTimestampDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/orders")
    public OrderDTO placeOrder(@RequestBody OrderRequest request) {
        BigDecimal total = request.items().stream()
                .map(MenuItemDTO::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Debug logging (system out for simplicity in this context)
        System.out.println("Placing Order: Table " + request.tableNumber() + ", Items: " + request.items().size()
                + ", Total: " + total);

        CustomerOrder order = new CustomerOrder(
                UUID.randomUUID().toString(),
                request.tableNumber(),
                total,
                "PENDING",
                LocalDateTime.now());

        for (MenuItemDTO itemDTO : request.items()) {
            OrderItem orderItem = new OrderItem(itemDTO.id(), itemDTO.name(), itemDTO.price());
            order.addItem(orderItem);
        }

        CustomerOrder savedOrder = orderRepository.save(order);
        System.out.println("Saved Order ID: " + savedOrder.getId());

        return convertToDTO(savedOrder);
    }

    public record OrderRequest(String tableNumber, List<MenuItemDTO> items) {
    }

    private OrderDTO convertToDTO(CustomerOrder order) {
        List<MenuItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> new MenuItemDTO(item.getItemId(), item.getName(), "", item.getPrice(), ""))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId(),
                order.getTableNumber(),
                itemDTOs,
                order.getTotal(),
                order.getStatus(),
                order.getTimestamp());
    }
}
