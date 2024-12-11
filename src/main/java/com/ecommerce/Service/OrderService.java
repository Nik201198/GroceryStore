package com.ecommerce.Service;

import com.ecommerce.entity.GroceryItem;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.User;
import com.ecommerce.repository.GroceryItemRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private GroceryItemRepository groceryItemRepository;
  @Autowired
  private UserRepository userRepository;

  public Order placeOrder(Long userId, List<OrderItem> orderItems){
    Double totalPrice = 0.0;

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

    totalPrice = orderItems.stream()
        .mapToDouble(item -> item.getPrice() * item.getQuantity())
        .sum();

    Order order = new Order();
    order.setUser(new User(userId));
    order.setOrderDate(LocalDate.now());
    order.setTotalPrice(totalPrice);

    for(OrderItem item : orderItems){
      GroceryItem groceryItem = groceryItemRepository.findById(item.getGroceryItem().getId())
          .orElseThrow(() -> new RuntimeException("Item not found"));
      if(groceryItem.getQuantity() < item.getQuantity()){
        throw new RuntimeException("Insufficient stock");
      }
      groceryItem.setQuantity(groceryItem.getQuantity() - item.getQuantity());
      item.setOrder(order);
      item.setGroceryItem(groceryItem);
      groceryItemRepository.save(groceryItem);
    }

    order.setOrderItems(orderItems);
    System.out.println("Order: "+ order);
    Order saved = orderRepository.save(order);
    System.out.println("Saved Order " + saved );
    return orderRepository.save(order);
  }
}
