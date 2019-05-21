package Models;

public class BookModel {
    private String isbn;
    private String articleType;
    private String title;
    private String publisher;
    private String publicationYear;
    private String genre;

   /* Add eventually, needs work on database side
    private String author;
    private int numberOfBooksInStock;
    */


    public BookModel(String isbn, String articleType, String title, String publisher, String publicationYear, String genre) {
        this.isbn = isbn;
        this.articleType = articleType;
        this.title = title;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getArticleType() {
        return articleType;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }
}
