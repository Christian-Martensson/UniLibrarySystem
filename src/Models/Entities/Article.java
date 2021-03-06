package Models.Entities;

public abstract class Article {
    protected String articleType;
    protected String title;
    protected String publicationYear;
    protected String genre;
    protected String creator;

    public abstract boolean checkAvailabilityInDb();

    public abstract void insertIntoDb();

    public abstract void removeFromDb();

    public abstract void createLoan(int barcodeId, int userId);

    public abstract void addBarcodesInDb(int number, String title);

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



