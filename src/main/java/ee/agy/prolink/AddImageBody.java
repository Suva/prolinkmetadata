package ee.agy.prolink;

public class AddImageBody {
    private int deck;
    private String data;

    public AddImageBody(int deck, String data) {
        this.deck = deck;
        this.data = data;
    }

    public int getDeck() {
        return deck;
    }

    public void setDeck(int deck) {
        this.deck = deck;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
