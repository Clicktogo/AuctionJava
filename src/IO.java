// Filip Garcia
import java.util.Scanner;

public class IO {

    private final Register register = new Register();


    //SCANNER methods_________________________________________________________________

    private static final Scanner INPUT = new Scanner(System.in);

    public String userInputString(String askInput) {
        System.out.print(askInput);
        return INPUT.nextLine().toLowerCase().trim();
    }

    public int userInputInt(String askInput) {
        int userAnswer;
        System.out.print(askInput);

        while (!INPUT.hasNextInt()) {
            System.out.println("Error, please enter an integer");
            INPUT.next();
        }

        userAnswer = INPUT.nextInt();
        INPUT.nextLine();
        return userAnswer;
    }

    public double userInputDouble(String askInput) {
        double userAnswer;
        System.out.print(askInput);
        while (!INPUT.hasNextDouble()) {
            System.out.println("Error! Please enter a number and you can only use \",\" and not \".\"");
            System.out.println("Why you might ask? Because the system don't like it =)");
            INPUT.next();
        }
        userAnswer = INPUT.nextDouble();
        INPUT.nextLine();
        return userAnswer;
    }

    // Terminal Commands_________________________________________________________________
    public void command() {

        String cmd = "";

        while (cmd.isEmpty() || cmd != "exit") {
            cmd = userInputString("Command?> ");

            if (cmd.isEmpty()) {
                System.out.println("Error: No such command");
                continue;
            }

            switch (cmd) {
                case "register new dog":
                    registerNewDog();
                    break;
                case "list dogs":
                    listDogs();
                    break;
                case "increase age":
                    increaseAge();
                    break;
                case "register new owner":
                    registerNewOwner();
                    break;
                case "remove dog":
                    removeDog();
                    break;
                case "assign dog to owner":
                    giveDog();
                    break;
                case "list owners":
                    register.listOwners();
                    break;
                case "remove owner":
                    removeOwner();
                    break;
                case "start new auction":
                    startAuction();
                    break;
                case "make bid":
                    makeBid();
                    break;
                case "list bids":
                    listBids();
                    break;
                case "list auctions":
                    register.listAuctions();
                    break;
                case "close auction":
                    closeAuction();
                    break;
                case "list commands":
                    register.printCommands();
                    break;
                case "exit":
                    cmd = "exit";
                    break;
                default:
                    System.out.println("No such command exist");
                    System.out.println("the wrong command was: " + cmd);
            }
        }
    }

    //DOG IO_________________________________________________________________
    public void registerNewDog() {
        String name = userInputString("Name?> ");
        String breed = userInputString("Breed?> ");
        int age = userInputInt("Age?> ");
        int weight = userInputInt("Weight?> ");

        var dog = new Dog(name, breed, age, weight);
        register.registerDog(name, dog);
    }

    public void listDogs() {
        if (register.dogListEmpty())
            System.out.println("Error: no registered dogs");
        else {
            double tailLength = userInputDouble("Smallest tail length to display?> ");
            register.listDogs(tailLength);
        }
    }

    public void increaseAge() {
        String nameDog = userInputString("Enter the name of the dog?> ");
        register.increaseDogAge(nameDog);
    }

    public void removeDog() {
        String dogName = userInputString("Enter the name of the dog?>");
        register.removeDogs(dogName);
    }

    public void giveDog() {
        Dog dog = register.findDogByName(userInputString("Enter the name of the dog?>"));
        if(!register.checkIfDogHasOwner(dog)){
            setDogOwner(dog);
        }
    }

    private void setDogOwner(Dog dog) {
        Owner owner = register.findOwnerByName(userInputString("Enter the name of the new owner?>"));
        register.setOwner(dog, owner);
    }

    //IO Owner_________________________________________________________________

    public void registerNewOwner() {
        String name = userInputString("Name?>");
        register.registerOwner(name);
    }

    public void removeOwner() {
        String nameOwner = userInputString("Enter the name from the user?> ");
        register.removeOwners(nameOwner);
    }

    //IO Auction_________________________________________________________________

    public void startAuction() {
        String nameDogToAuction = userInputString("Enter the name of the dog?>");
        register.startAuction(nameDogToAuction);
    }

    public Auction findAuctionByDog(Dog dogInAuction) {
        return register.findAuctionByDog(dogInAuction);
    }

    private void handleAuction(String nameUser, Owner user, Dog dog, Auction auction) {
        double bidByUser = 0;
        double minBidAmount = register.getTopBidAmount() + 1;
        while (bidByUser < minBidAmount) {
            bidByUser = userInputDouble("Amount to bid (minimum bid. " + minBidAmount + ")?>");
            if (bidByUser >= minBidAmount) {
                register.addBidToList(user, bidByUser);
                System.out.println("Bidding owner " + nameUser + " has placed a bid of " + bidByUser + "kr on " + dog.getName() + " in auction nr " + auction.getAuctionNumber());
            } else {
                System.out.println("Error: too low bid! Minimum bid = " + minBidAmount);
            }
        }
    }

    public void closeAuction() {
        String nameDog = userInputString("Enter the name of the dog?>");
        register.closeAuctions(nameDog);
    }


    //IO Bid_________________________________________________________________
    public void makeBid() {
        String nameUser = userInputString("Enter the name of the bidding owner?>");
        Owner user = register.findOwnerByName(nameUser);
        if (user == null) {
            System.out.println("Error: The owner is not registered");
            return;
        }
        String nameDog = userInputString("Enter the name of the dog?>");
        Dog dog = register.findDogByName(nameDog);
        if (dog == null) {
            System.out.println("Error: no such dog in register");
            return;
        }
        if (dog.getOwner() != null) {
            System.out.println("Error: " + dog.getName() + " has an owner and is not up for auction");
            return;
        }
        Auction auction = findAuctionByDog(dog);
        if (auction == null) {
            System.out.println("Error: no such auction");
            return;
        }
        handleAuction(nameUser, user, dog, auction);
    }

    public void listBids() {
        String dogName = userInputString("Enter the name of the dog?>");
        register.bidList(dogName);
    }


    //DEMO setup_________________________________________________________________

    public void testingSetup() {
        Dog fido = new Dog("Fido", "Border Collie", 1, 18);
        Dog floyd = new Dog("Floyd", "Tax", 8, 9);
        Dog ellie = new Dog("Ellie", "Terrier", 4, 12);
        Dog dexter = new Dog("Dexter", "pitbull", 10, 30);
        Dog diego = new Dog("Diego", "Continental bulldog", 4, 6);
        Dog hera = new Dog("Hera", "Staffordshire Bull Terrier", 2, 14);
        Dog bosse = new Dog("Bosse", "Amstaff", 3, 7);
        Dog elli = new Dog("Ellie", "Siberian Husky", 6, 24);

        register.addDog(floyd);
        register.addDog(ellie);
        register.addDog(dexter);
        register.addDog(fido);
        register.addDog(diego);
        register.addDog(hera);
        register.addDog(bosse);
        register.addDog(elli);

        Owner kim = new Owner("Kim");
        Owner peter = new Owner("Peter");
        Owner katta = new Owner("Katta");
        Owner jamal = new Owner("Jamal");

        register.addOwner(kim);
        register.addOwner(peter);
        register.addOwner(katta);
        register.addOwner(jamal);

        System.out.println("Here are the pre-loaded auctions:");
        register.startAuction(ellie.getName());
        register.startAuction(fido.getName());
        System.out.println("------------------------------");
        System.out.println();
    }
}

