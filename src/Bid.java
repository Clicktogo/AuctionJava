// Filip Garcia

public class Bid implements Comparable<Bid> {

    private final Owner biddingOwner;
    private double bidAmount;

    public Bid(Owner biddingOwner, double bidAmount) {
        this.biddingOwner = biddingOwner;
        this.bidAmount = bidAmount;
    }

    public Owner getBiddingOwner() {
        return biddingOwner;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    @Override
    public int compareTo(Bid bid) {
        return Double.compare(bidAmount, bid.getBidAmount());
    }

    @Override
    public String toString() {
        return biddingOwner.getName() + " " + bidAmount + " kr";
    }
}

