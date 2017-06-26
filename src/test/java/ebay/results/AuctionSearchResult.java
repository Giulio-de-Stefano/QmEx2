package ebay.results;

public class AuctionSearchResult extends SearchResult {
    private final String bids;

    public AuctionSearchResult(String title, String price, String bids) {
        super(title, price);
        this.bids = bids.substring(0, bids.indexOf(" "));
    }

    public String getBids() {
        return bids;
    }

    public String toString() {
        return super.toString() + " - " + this.getBids();
    }
}
