/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zoo;

/**
 * Item Class - Represents objects used in logistics operations
 * @author ashongtical
 */
public class Item {
    private String name;
    private double price;
    private String code;

    public Item(String name,  String code) {
        this.name = name;
        this.code = code;
    }

    // Accessor
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getCode() {
        return code;
    }

    // Mutator
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public void printDetails() {
        System.out.print("Name: " + name);
        System.out.print("\tPrice:  " + price);
        System.out.println("\tCode: " + code);
    }
}
