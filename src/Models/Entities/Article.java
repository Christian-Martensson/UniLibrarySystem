package Models.Entities;

public class Article {
    String articleType;
    String title;
    String publicationYear;
    String genre;
    String creator;
    int numberInStock;

    public boolean checkAvailabilityInDb() {
        return false;
    }

    public void loadToDb() {

    }

    public void removeFromDb() {

    }

    public void createLoan(int barcodeId, int userId) { }

    public int getAvailableBarcode() {
        return 0;
    }

    public String getArticleType() {
        return articleType;
    }

    public String getCreator() {
        return creator;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }
}



