package ebay.results;

public class SearchResult {
    private final String title;
    private final String price;

    public SearchResult(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String toString() {
        return this.getTitle() + " - " + this.getPrice();
    }
}
