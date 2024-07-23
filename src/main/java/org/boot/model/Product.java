package org.boot.model;

import lombok.Data;

@Data
public class Product {
    private Integer productId;
    private String title;
    private String description;
    private double price;
    private double discount;
}
