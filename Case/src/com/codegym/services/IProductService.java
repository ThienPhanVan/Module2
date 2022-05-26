package com.codegym.services;

import com.codegym.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    void add(Product newProduct);

    void update();

    //id,name,color,quantity,price
    void update(Product newProduct);

    Product getProductById(int id);

    boolean exist(int id);

    boolean existById(int id);

    void updateQuantity(int id, int quantity);

    boolean checkDuplicateName(String name);

    boolean checkDuplicateId(int id);

    void removeById(int id);

    List<Product> findAllOrderByPriceASC();

    List<Product> findAllOrderByPriceDESC();
}
