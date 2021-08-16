// Filip Garcia

public class Dog implements Comparable<Dog> {

    private final int weight;
    private final String name;
    private final String breed;
    private Owner owner;
    private int age;

    public Dog(String name, String breed, int age, int weight) {
        if (age < 0) {
            throw new IllegalArgumentException("age to low");
        }
        this.name = name.toLowerCase();
        this.breed = breed.toLowerCase();
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public double getTailLength() {
        return (double) (age * 2 * weight) / 5;
    }

    public double getTailLength(int age, int weight) {
        return (double) (age * 2  * weight) / 5;
    }

    public void setOwner(Owner owner) {
        if (this.owner == null) {
            this.owner = owner;
            this.owner.addDogToOwner(this);
        } else {
            System.out.println("Error: Action can't be taken. " + name + "already has an owner");
        }
    }

    public Owner getOwner() {
        return owner;
    }

    public void addAge() { //todo incrementAge
        age++;
    }

    @Override
    public String toString() {
        String ownerName = "";
        if (this.owner != null) {
            ownerName = ", owned by " + owner.getName();
        }
        return "* " + name + " (" + breed + ", " + age + " years, " + weight + " kilo, " + getTailLength() + " cm tail" + ownerName + ")";
    }

    @Override
    public int compareTo(Dog o) {
        if (name.equalsIgnoreCase(o.name)) {
            if (getTailLength() > o.getTailLength(o.age, o.weight)) {
                return 1;
            } else if (getTailLength() < o.getTailLength(o.age, o.weight)) {
                return -1;
            } else {
                return 0;
            }
        }
        return name.compareTo(o.name);
    }
}


