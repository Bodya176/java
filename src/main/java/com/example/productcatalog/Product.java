package com.example.productcatalog;

import java.time.LocalDate;

public class Product {
    private long id;
    private String name;
    private double price;
    private LocalDate creationDate = LocalDate.now(); // дата создания

    // Конструкторы
    public Product() {}
    public Product(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.creationDate = LocalDate.now();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    // Новый метод: дата через 5 дней
    public LocalDate getDeliveryDate() {
        return creationDate.plusDays(5);
    }
}
