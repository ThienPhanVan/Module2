package com.codegym.service;

import com.codegym.motel.Product;

import java.util.List;

public interface IProductService {
    List<Product>getItem();

    void add(Product product);
    void update();
    void remove(ProductService productService);
    void search();
    boolean isFullNameHaveInList(String name);
    boolean getProductByName(String name);
    boolean existById(int id);
}
