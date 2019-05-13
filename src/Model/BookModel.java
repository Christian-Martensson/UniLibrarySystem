package Model;

public class BookModel {
    private String title;
    private String publicationYear;

    public BookModel(String title, String publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public String getPublicationYear() {
        return publicationYear;
    }
}
