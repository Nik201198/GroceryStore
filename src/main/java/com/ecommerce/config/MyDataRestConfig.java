package com.ecommerce.config;

import com.ecommerce.entity.GroceryItem;
import com.ecommerce.entity.Order;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
      CorsRegistry cors) {
    HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE};

    //disable HTTP methods for Order; PUT,POST,DELETE
    config.getExposureConfiguration()
        .forDomainType(Order.class)
        .withItemExposure((metData, httpMethods) -> httpMethods.disable(theUnsupportedActions))
        .withCollectionExposure(
            (metData, httpMethods) -> httpMethods.disable(theUnsupportedActions));

    //disable HTTP methods for GroceryItem; PUT,POST,DELETE
    config.getExposureConfiguration()
        .forDomainType(GroceryItem.class)
        .withItemExposure((metData, httpMethods) -> httpMethods.disable(theUnsupportedActions))
        .withCollectionExposure(
            (metData, httpMethods) -> httpMethods.disable(theUnsupportedActions));
  }
}
