package com.ecommerce.Service;

import com.ecommerce.entity.GroceryItem;
import com.ecommerce.repository.GroceryItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroceryItemService {

  @Autowired
  private GroceryItemRepository groceryItemRepository;

  public List<GroceryItem> getAllItems() {
    return groceryItemRepository.findAll();
  }

  public GroceryItem addItem(GroceryItem item) {
    return groceryItemRepository.save(item);
  }

  public void deleteItem(Long id) {
    groceryItemRepository.deleteById(id);
  }

  public GroceryItem updateItem(Long id, GroceryItem groceryItem) {
    GroceryItem item = groceryItemRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Item not found"));
    item.setName(groceryItem.getName());
    item.setPrice(groceryItem.getPrice());
    item.setQuantity(groceryItem.getQuantity());
    return groceryItemRepository.save(item);
  }

}
