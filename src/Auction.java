// Filip Garcia

import java.util.*;

public class Auction {

    private static int numberCount;
    private final int auctionNumber;
    private final Dog dogToAuction;

    public Auction(Dog dog) {
        numberCount++;
        this.auctionNumber = numberCount;
        this.dogToAuction = dog;
    }

    public int getAuctionNumber() {
        return auctionNumber;
    }

    public Dog getDogToAuction() {
        return dogToAuction;
    }

    @Override
    public String toString() {
        return "Auction #" + auctionNumber + ". Dog: " + dogToAuction.getName() + ". Top three bids: ";
    }
}
