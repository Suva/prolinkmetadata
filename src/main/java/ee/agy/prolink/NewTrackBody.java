package ee.agy.prolink;

public class NewTrackBody {
    private int deck;
    private String artist;
    private String title;

    public NewTrackBody(int deck, String artist, String title) {
        this.deck = deck;
        this.artist = artist;
        this.title = title;
    }

    public int getDeck() {
        return deck;
    }

    public void setDeck(int deck) {
        this.deck = deck;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
