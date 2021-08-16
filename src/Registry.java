// Filip Garcia

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Registry {

    private final List<Auction> auctionList = new ArrayList<>();
    private final List<Dog> dogsList = new ArrayList<>();
    private final List<Owner> ownersList = new ArrayList<>();
    private final List<Bid> bidsList = new ArrayList<>();
    private final List<String> commandList = List.of("register new dog", "list dogs",
            "increase age", "remove dog", "register new owner", "assign dog to owner",
            "list owners", "remove owner", "start new auction", "make bid", "list bids",
            "list auctions", "close auction", "list commands", "exit");


    //DOG methods
    public void addDog(Dog dog) {
        dogsList.add(dog);
    }

    public void addOwner(Owner owner) {
        ownersList.add(owner);
    }

    public Dog findDogByName(String dogName) {
        Dog result = null;
        for (Dog dog : dogsList) {
            if (dog.getName().equals(dogName)) {
                result = dog;
            }
        }
        return result;
    }

    public void removeDog(String dogName) {
        var dogToRemove = findDogByName(dogName);

        if (dogToRemove != null) {
            dogsList.remove(dogToRemove);
            Owner owner = dogToRemove.getOwner();
            if (owner != null) {
                owner.removeDogFromOwner(dogToRemove);
            }
        } else {
            System.out.println("Error: no such dog");
        }
    }

    private List<Dog> findDogsByTailLength(double minTailLength) {
        var dogs = new ArrayList<Dog>();
        for (Dog dog : dogsList) {
            if (dog.getTailLength() >= minTailLength) {
                dogs.add(dog);
            }
        }
        return dogs;
    }

    public void listDogs(double tailLength) {
        var dogsByTailLength = findDogsByTailLength(tailLength);
        if (dogsByTailLength.isEmpty()) {
            System.out.println("No dog matches requirements");
        } else {
            sortDogsList(dogsByTailLength);
            System.out.println("Here is the list of dogs: ");
            for (Dog dog : dogsByTailLength) {
                System.out.println(dog);
            }
        }
    }

    public void sortDogsList(List<Dog> arrayList) {
        Collections.sort(arrayList);
    }

    public boolean dogListEmpty() {
        return dogsList.isEmpty();
    }

    public boolean checkIfDogHasOwner(Dog dog) {
        if (dog == null) {
            System.out.println("Error: no dog with that name");
            return true;
        }

        if (dog.getOwner() != null) {
            System.out.println("Error: " + dog.getName() + " already has an owner");
            return true;
        }
        return false;
    }

    public void increaseDogAge(String nameDog) {
        var dog = findDogByName(nameDog); // Dog dog = register.findDogsByName(name);
        if (dog != null) {
            dog.addAge();
            System.out.println(nameDog + " is now one year older");
        } else {
            System.out.println("Error: no such dog");
        }
    }

    public void removeDogs(String dogName) {
        if (findDogByName(dogName) != null) {
            Auction auction = findAuctionByDog(findDogByName(dogName));
            removeAuction(auction);
        }
        removeDog(dogName);
        System.out.println(dogName + " has been removed");
    }

    public void registerDog(String name, Dog dog) {
        addDog(dog);

        System.out.println(name + " has been added to the register");
    }


    // OWNER methods
    public Owner findOwnerByName(String ownerName) {
        for (Owner owner : ownersList) {
            if (owner.getName().equals(ownerName)) {
                return owner;
            }
        }
        return null;
    }

    public void removeOwner(String nameOwner) {
        Owner ownerToRemove = findOwnerByName(nameOwner);
        if (ownerToRemove == null) {
            System.out.println("Error: no owner in register");
        } else {
            List<Dog> dogsToRemove = ownerToRemove.getDogs();
            for (Dog doggo : dogsToRemove) {
                dogsList.removeIf(dog -> dog.equals(doggo));
            }
            ownerToRemove.removeAllDogsFromOwner();
            ownersList.remove(ownerToRemove);
            System.out.println("Owner removed from list");
        }
    }

    public void listOwners() {
        if (ownersList.isEmpty()) {
            System.out.println("Error, no owners in list");
        } else {
            ownersList.stream().sorted().forEach(System.out::println);
        }
    }

    public void setOwner(Dog dog, Owner owner) {
        if (owner == null) {
            System.out.println("Error: no such owner");
            return;
        }

        dog.setOwner(owner);
        System.out.println(owner.getName() + " now owns " + dog.getName());
        removeAuction(findAuctionByDog(dog));
    }

    public void registerOwner(String name) {
        Owner owner = new Owner(name);
        addOwner(owner);
        System.out.println(owner.getName() + " is now added to the register");
    }

    public void removeOwners(String nameOwner) {
        Owner owner = findOwnerByName(nameOwner);
        if (owner != null) {
            removeBid(owner);
        }
        removeOwner(nameOwner);
        System.out.println("Owner removed");
    }


    // AUCTION methods
    public void listAuctions() {
        if (auctionList.isEmpty()) {
            System.out.println("Error: no ongoing auctions");
        } else {
            auctionList.stream().forEach(System.out::println);
        }
    }

    public void startAuction(String nameDogToAuction) {
        Dog dogToAuction = findDogByName(nameDogToAuction);
        if (dogToAuction == null) {
            System.out.println("Error: no such dog");
        } else {
            if (dogToAuction.getOwner() != null) {
                System.out.println("Error: this dog already has an owner");
            } else {
                if (findAuctionByDog(dogToAuction) == null) {
                    Auction auction = new Auction(dogToAuction);
                    auctionList.add(auction);
                    System.out.println(auction.getDogToAuction() + " has been put up for auction in auction nr " + auction.getAuctionNumber());
                } else {
                    System.out.println("Error: this dog is already up for auction");
                }
            }
        }
    }

    public Auction findAuctionByDog(Dog dogInAuction) {
        for (Auction auction : auctionList) {
            if (auction.getDogToAuction().equals(dogInAuction)) {
                return auction;
            }
        }
        return null;
    }

    public void removeBid(Owner owner) {
        removeBid(auctionList, owner);
    }

    public void removeAuction(Auction auction) {
        auctionList.remove(auction);
    }

    public void bidList(String dogName) {
        Dog dog = findDogByName(dogName);
        if (dog == null) {
            System.out.println("Error: no such dog");
        } else {
            Auction auction = findAuctionByDog(dog);
            if (auction == null) {
                System.out.println("Error: " + dog.getName() + " is not up for auction");
            } else {
                listBids(dog);
            }
        }
    }

    public void closeAuctions(String nameDog) {
        Dog dog = findDogByName(nameDog);
        if (dog == null) {
            System.out.println("Error: " + nameDog + " is not up for auction");
        } else {
            Auction auction = findAuctionByDog(dog);
            if (auction == null) {
                System.out.println("Error: " + dog.getName() + " is not up for auction");
            } else if (!containBids()) {
                removeAuction(auction);
                System.out.println("The auction has been closed. No bids were made for: " + dog.getName());
            } else {
                Bid winningBid = getTopBid();
                Owner winner = winningBid.getBiddingOwner();
                double amount = winningBid.getBidAmount();
                dog.setOwner(findOwnerByName(winner.getName()));
                System.out.println("The winning bid was " + amount + "kr and was made by " + winner);
                removeAuction(auction);
            }
        }
    }

    //BID methods
    public void addBidToList(Owner owner, double bidAmount) {
        if (bidAmount > getTopBidAmount()) {
            bidsList.removeIf(bid -> bid.getBiddingOwner().equals(owner));
        }
        bidsList.add(new Bid(owner, bidAmount));
    }

    public void listBids(Dog dog) {
        if (bidsList.isEmpty()) {
            System.out.println("No bids registered yet for: " + dog.getName());
        } else {
            displayTopThreeBids();
        }
    }

    // TODO: 2021-08-15 Fix parameters
    public void removeBid(List<Auction> auctionList, Owner owner) {
        bidsList.removeIf(bid -> bid.getBiddingOwner().equals(owner));
    }

    public double getTopBidAmount() {
        Collections.sort(bidsList);
        int lastIndex = bidsList.size() - 1;
        if (bidsList.isEmpty()) {
            return 0;
        }
        return bidsList.get(lastIndex).getBidAmount();
    }

    public void displayTopThreeBids() {
        Collections.sort(bidsList);
        Collections.reverse(bidsList);
        if (bidsList.size() <= 2) {
            System.out.println(bidsList.toString());
        } else {
            System.out.println("Here are the top three bids:");
            List<Bid> topThreeBids = bidsList.stream().limit(3).collect(Collectors.toList());
            topThreeBids.stream().forEach(System.out::println);
        }
    }

    public boolean containBids() {
        if (bidsList.size() > 0) {
            return true;
        }
        return false;
    }

    public Bid getTopBid() {
        if (bidsList.size() > 0) {
            Collections.sort(bidsList);
            Collections.reverse(bidsList);
            return bidsList.get(0);
        } else {
            return null;
        }
    }

    // PRINT Commands
    public void printCommands() {
        System.out.println("Here are your available commands: ");
        for (String cmd : commandList) {
            System.out.println("- " + cmd);
        }
    }
}


//MANUAL array sorting

       /* for (int i = 1; i < arrayList.size(); i++) {

            Dog tempVar = arrayList.get(i);  // get since arrayList not array - sid 460 D Liang
            int j;
            //Move list[i−1] to list[i] if list[i−1] > tempVar, move list[i−2] to list[i−1] if list[i−2] > tempVar.
            for (j = i - 1; j >= 0 && arrayList.get(j).getTailLength() >= tempVar.getTailLength(); j--) {
            //compare tail length through Dog - getTilLength().
                if (arrayList.get(j).getTailLength() == tempVar.getTailLength()) {
                    // Now we need to compare names.
                    if (arrayList.get(j).getName().compareTo(tempVar.getName()) >= 0) {
                        // When names are not in alphabetic order switch place.
                        arrayList.set(j + 1, arrayList.get(j));
                    } else {
                        // Names are in alphabetic order - do nothing.
                        break;
                    }
                } else {
                    arrayList.set(j + 1, arrayList.get(j));  // When j-taillength is bigger then tempVar. (int index, Dog Element).
                    //set and get since arrayList not array - sid 460 D Liang
                }
            }
            arrayList.set(j + 1, tempVar); // When j-taillength is smaller then tempVar-taillength.
        }
    }*/




