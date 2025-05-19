package Zoo;

/**
 * Zoo Class - Manages a collection of animals
 * 
 * @author ashongtical
 */
public class Zoo {
    private String name;
    private Animal[] animals;
    private int counter;

    // Constructor
    public Zoo(String name) {
        this.name = name;
        this.animals = new Animal[10];
        this.counter = 0;
    }

    public int addAnimal(Animal animal) {
        if (counter < animals.length) {
            animals[counter++] = animal;
            System.out.println("Successful add animal " + animal.getName() + " to the " + this.name + " zoo.");
            return counter;
        } else {
            System.out.println(this.name + " is full, can not add more animals.");
            return -1;
        }
    }

    public String getName() {
        return name;
    }

    public void displayAnimals() {
        System.out.println("---- Animals in " + this.name + " Zoo (" + this.counter + "/10)----");
        for (int i = 0; i < counter; i++) {
            animals[i].display();
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    public int findAnimal(String name) {
        for (int i = 0; i < counter; i++) {
            if (animals[i].getName().equals(name)) {
                return i;
            }
        }
        System.out.println("Animal " + name + " not found in the " + this.name + " zoo.");
        return -1;
    }

    public void deleteAnimal(String animal_name) {
        // Find index of Animal to del
        for (int i = 0; i < counter; i++) {
            if (animals[i].getName().equals(animal_name)) {
                // Shift animals to the left
                for (int j = i; j < counter - 1; j++) {
                    animals[j] = animals[j + 1];
                }
                animals[--counter] = null; // Clear the last animal
                System.out.println("Successful delete animal " + animal_name + " from the " + this.name + " zoo.");
                return;
            }
        }
        System.out.println("Animal " + animal_name + " not found in the " + this.name + " zoo.");
    }

    public void moveAnimal(String animal_name, Zoo destZoo, Logistics logistics) {
        int mov_animal_idx = findAnimal(animal_name);

        if (mov_animal_idx == -1) {
            // return if animal not found
            System.out.println("Animal " + animal_name + " not found in the " + this.name + " zoo.");
            return;
        }

        System.out.println("*************************** ANIMAL TRANSFER INVOICE ***************************");
        System.out.println("From: " + this.name);
        System.out.println("To: " + destZoo.getName());
        System.out.print("Animal Details: ");
        animals[mov_animal_idx].display();
        System.out.println();
        System.out.println("-------------------------- Logistics Details --------------------------");
        logistics.printDetails();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("*******************************************************************************");
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("Moving animal " + animal_name + " from " + this.name + " to " + destZoo.getName() + "...");

        // add animal to destination zoo
        if (destZoo.addAnimal(animals[mov_animal_idx]) == -1) {
            System.out.println(
                    "DESTINATION ZOO ERROR: Animal " + animal_name + " not moved to " + destZoo.getName() + " zoo.");
            return;
        }
        // Remove animal from current zoo
        this.deleteAnimal(animal_name);
        System.out.println("Animal " + animal_name + " move to " + destZoo.getName() + " zoo.");
        System.out.println("Successful.");
        System.out.println();

    }

    public int getCounter() {
        return counter;
    }

    public Animal getAnimal(int index) {
        if (index >= 0 && index < counter) {
            return animals[index];
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

}
