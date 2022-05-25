package com.codegym.motel;

public class Product {
    private int id;
    private String name;
    private int price;
    private int quantity;
    private String description;

    public Product() {
    }

    public Product(int id, String name, int price, int quantity, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public Product (String record) {
        String[] fields = record.split(",");
        id = Integer.parseInt(fields[0]);
        name = fields[1];
        price = Integer.parseInt(fields[2]);
        quantity = Integer.parseInt(fields[3]);
        description = fields[4];

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,",id + "," + name+ "," + price + "," + quantity + "," + description);
    }
}
