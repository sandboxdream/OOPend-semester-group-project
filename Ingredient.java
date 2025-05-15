/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantmanagementsystem1;

/**
 *
 * @author y'y'f
 */
public class Ingredient implements Priceable {
    private final String name;
    private final double price;

    public Ingredient(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s (￥%.2f)", name, price);
    }
}
