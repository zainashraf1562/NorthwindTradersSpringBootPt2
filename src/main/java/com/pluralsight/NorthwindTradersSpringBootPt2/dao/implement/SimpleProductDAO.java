package com.pluralsight.NorthwindTradersSpringBootPt2.dao.implement;

import com.pluralsight.NorthwindTradersSpringBootPt2.dao.interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersSpringBootPt2.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductDAO implements IProductDAO {
    private List<Product> products;

    public SimpleProductDAO() {
        this.products = new ArrayList<>();

        products.add(new Product("Iphone",1,500));
        products.add(new Product("Gaming PC",2,1000));
        products.add(new Product("Headphones",3,50));
    }

    @Override
    public Product add(Product product) {
        int maxId = 0;
        for (Product t : products) {
            if (t.getProductId() > maxId) {
                maxId = t.getProductId();
            }
        }
        product.setProductId(maxId + 1);
        products.add(product);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void update(int productId, Product product) {
        int index = getProductIndex(productId);
        if (index != -1) {
            products.set(index, product);
        }

    }

    @Override
    public void delete(int productId) {
        int index = getProductIndex(productId);
        if (index != -1) {
            products.remove(index);
        }

    }

    private int getProductIndex(int productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == productId) {
                return i;
            }
        }
        return -1;
    }

}
