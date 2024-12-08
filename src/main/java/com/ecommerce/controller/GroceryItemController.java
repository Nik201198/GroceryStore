package com.ecommerce.controller;

import com.ecommerce.Service.GroceryItemService;
import com.ecommerce.entity.GroceryItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grocery")
public class GroceryItemController {
  @Autowired
  private GroceryItemService groceryItemService;

  @GetMapping
  public List<GroceryItem> getItems() {
    return groceryItemService.getAllItems();
  }

  @PostMapping

  public GroceryItem addItem(@RequestBody GroceryItem item) {
    return groceryItemService.addItem(item);
  }

  @PutMapping("/{id}")

  public GroceryItem updateItem(@PathVariable Long id, @RequestBody GroceryItem item) {
    return groceryItemService.updateItem(id, item);
  }

  @DeleteMapping("/{id}")

  public void deleteItem(@PathVariable Long id) {
    groceryItemService.deleteItem(id);
  }
}
