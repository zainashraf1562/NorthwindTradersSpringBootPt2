package com.pluralsight.NorthwindTradersSpringBootPt2.dao.interfaces;

import com.pluralsight.NorthwindTradersSpringBootPt2.model.Product;

import java.util.List;

public interface IProductDAO {


    Product add(Product product);


    List<Product> getAllProducts();


    Product getProductById(int productId);


    void update(int productId, Product product);


    void delete(int productId);

}
