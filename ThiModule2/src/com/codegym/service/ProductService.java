package com.codegym.service;

import com.codegym.motel.Product;
import com.codegym.untils.CSVUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductService implements IProductService{
    List<Product> productList = new ArrayList();
    public String path = "data/products.csv";

    public ProductService() {
        this.getItem();
    }

    public List<Product> getItem() {
        List<Product> productList = new ArrayList();
        List<String> records = CSVUtils.read(this.path);
       for (String record : records){
           productList.add(new Product(record));
        }

        return productList;
    }

    public void add(Product product) {
        this.productList.add(product);
        CSVUtils.write(this.path, this.productList);
    }

    public void update() {
        CSVUtils.write(this.path, this.productList);
    }

    public void remove(ProductService productService) {
        this.productList.remove(productService);
        this.update();
    }

    public void search() {
    }

    public boolean isFullNameHaveInList(String name) {
    for (Product product: productList){
        if(product.getName().equals(name))
            return true;
    }

        return false;
    }

    @Override
    public boolean getProductByName(String name) {
       for(Product product: productList){
           if(product.getName().contains(name)){
               return true;
           }
       }
        return false;
    }
    public boolean existById(int id) {
        for (Product product : productList){
            if(product.getId() == id)
                return true;
        }
        return false;
    }

}
