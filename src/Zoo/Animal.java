/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Zoo;

/**
 * Animal Class - Represents animals in the zoo
 * @author ashongtical
 */
public class Animal {
    private String name;
    private String species;
    private int age;

    // Constructor
    public Animal(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }

    // Accessor
    public String getName() {
        return name;
    }
    public String getSpecies() {
        return species;
    }
    public int getAge() {
        return age;
    }

    // Mutator
    public void setName(String name) {
        this.name = name;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public void setAge(int age) {
        this.age = age;
    }


    public void display() {
        System.out.print("Animal Name: " + name);
        System.out.print("\t,Species: " + species);
        System.out.print("\t,Age: " + age);
    }

}
