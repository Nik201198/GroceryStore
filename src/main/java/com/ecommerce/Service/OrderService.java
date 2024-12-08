package com.ecommerce.Service;

import com.ecommerce.entity.GroceryItem;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.User;
import com.ecommerce.repository.GroceryItemRepository;
import com.ecommerce.repository.OrderRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private GroceryItemRepository groceryItemRepository;

  public Order placeOrder(Long userId, List<OrderItem> orderItems){
    Double totalPrice = 0.0;
    for(OrderItem item : orderItems){
      GroceryItem groceryItem = groceryItemRepository.findById(item.getGroceryItem().getId())
          .orElseThrow(() -> new RuntimeException("Item not found"));
      if(groceryItem.getQuantity() < item.getQuantity()){
        throw new RuntimeException("Insufficient stock");
      }
      groceryItem.setQuantity(groceryItem.getQuantity() - item.getQuantity());
      groceryItemRepository.save(groceryItem);
      totalPrice += item.getPrice() * item.getQuantity();
    }

    Order order = new Order();
    order.setUser(new User(userId));
    order.setOrderDate(LocalDate.now());
    order.setTotalPrice(totalPrice);
    order.setOrderItems(orderItems);

    return orderRepository.save(order);
  }

}
