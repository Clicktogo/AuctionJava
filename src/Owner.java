// Filip Garcia

import java.util.ArrayList;
import java.util.List;

public class Owner implements Comparable<Owner> {

    private final String name;
    private final List<Dog> dogsAssignedToOwner = new ArrayList<>();

    public Owner(String name) {
        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public List<Dog> getDogs() {
        return dogsAssignedToOwner;
    }

    public void removeDogFromOwner(Dog dog) {
        dogsAssignedToOwner.remove(dog);
    }

    public void removeAllDogsFromOwner() {
        if (dogsAssignedToOwner.isEmpty()) {
            System.out.println("Owner has no dogs");
        } else {
            dogsAssignedToOwner.clear();
        }
    }

    private Dog findOwnedDog(Dog doggo) {
        for (Dog dog : dogsAssignedToOwner) {
            if (dog.equals(doggo)) {
                return doggo;
            }
        }
        return null;
    }

    public void addDogToOwner(Dog newDog) {

        if (findOwnedDog(newDog) == null) {
            dogsAssignedToOwner.add(newDog);

            if (newDog.getOwner() == null) {
                newDog.setOwner(this);
            }
        }
    }

    @Override
    public String toString() {

        return "Owner" + " name: " + name + ". " + name + " own the following dogs: \n" + dogsAssignedToOwner.toString() + "\n";
    }

    @Override
    public int compareTo(Owner o) {
        return name.compareTo(o.name);
    }
}
