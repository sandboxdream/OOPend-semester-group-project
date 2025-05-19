/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zoo;

/**
 * Logistics Class - Manages transportation details for animal movements
 * 
 * @author ashongtical
 */
public class Logistics {
    private Item vehicle;
    private Item fuel;
    private String[] caretakers;

    public Logistics(Item vehicle, Item fuel, String[] caretakers) {
        this.vehicle = vehicle;
        this.fuel = fuel;
        this.caretakers = caretakers;
    }

    // Accessor
    public void setVehicle(Item vehicle) {
        this.vehicle = vehicle;
    }

    public void setFuel(Item fuel) {
        this.fuel = fuel;
    }

    public void setCaretakers(String[] caretakers) {
        this.caretakers = caretakers;
    }

    // Mutator
    public Item getVehicle() {
        return vehicle;
    }

    public Item getFuel() {
        return fuel;
    }

    public String[] getCaretakers() {
        return caretakers;
    }

    public double getTotalCost() {
        return vehicle.getPrice() + fuel.getPrice();
    }

    public void printDetails() {
        System.out.println("Vehicle Info:");
        this.vehicle.printDetails();
        System.out.println("Fuel Info:");
        this.fuel.printDetails();
        System.out.print("Caretakers: ");
        for (String caretaker : caretakers) {
            System.out.print(caretaker + " ");
        }
        double totalCost = vehicle.getPrice() + fuel.getPrice();
        System.out.println("\nTotal Cost: " + totalCost);
        System.out.println();
    }

}
