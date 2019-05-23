package Models.Entities;

public abstract class Article {
    String articleType;
    String title;
    String publicationYear;
    String genre;
    String creator;
    int numberInStock;

    public abstract boolean checkAvailabilityInDb();

    public abstract void loadToDb();

    public abstract void removeFromDb();

    public abstract void createLoan(int barcodeId, int userId);

    public abstract int getAvailableBarcode();

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



