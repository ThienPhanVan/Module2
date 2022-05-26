package com.codegym.service;

import com.codegym.motel.Product;

import java.util.List;

public interface IProductService {
    List<Product>getItem();

    List<Product> findAll();

    void add(Product newProduct);

    List<Product> update(Product newProduct);

    Product findById(int id);

    boolean exist(int id);

    boolean existByName(String name);

    boolean existsById(int id);

    void deleteById(int id);

    List<Product> findAllOrderByPriceASC();

    List<Product> findAllOrderByPriceDESC();

    public Product findMaxPrice();
}
