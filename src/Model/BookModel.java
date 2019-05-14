package Model;

public class BookModel {
    private String title;
    private String publicationYear;
    private String ISBN;
    private String publisher;
    private String genre;
    private String author;
    private int numberOfBooksInStock;


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
