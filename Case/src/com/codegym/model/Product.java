package com.codegym.model;

public class Product {
    private Long id;
    private String title;
    private String color;
    private Double price;
    private Integer quantity;


    public Product() {
    }

    public Product(Long id, String title, String color,Integer quantity,Double price) {
        this.id = id;
        this.title = title;
        this.color =color;
        this.quantity = quantity;
        this.price = price;

    }
    public static Product parse(String record){
        String [] fields = record.split(",");
        long id = Long.parseLong(fields[0]);
        String title = fields[1];
        String color = fields[2];
        Integer quantity = Integer.parseInt(fields[3]);
        double price = Double.parseDouble(fields[4]);
        return new Product(id,title,color,quantity,price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,",
                id,
                title,
                color,
                quantity,
                price);
    }
}
