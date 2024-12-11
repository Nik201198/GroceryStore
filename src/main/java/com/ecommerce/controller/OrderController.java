package com.ecommerce.controller;

import com.ecommerce.Service.OrderService;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  @Autowired
  private OrderService orderService;

  @PostMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public Order placeOrder(@PathVariable Long id, @RequestBody List<OrderItem> orderItems) {
    System.out.println("Received Order: " + orderItems);
    return orderService.placeOrder(id, orderItems);
  }
}

