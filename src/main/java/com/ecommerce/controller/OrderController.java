package com.ecommerce.controller;

import com.ecommerce.Service.OrderService;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping
  public Order placeOrder(@RequestBody List<OrderItem> orderItems) {
    Long userId = 1L;  // Mocked for simplicity
    return orderService.placeOrder(userId, orderItems);
  }
}

