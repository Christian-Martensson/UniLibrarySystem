package Models;

public class BookModel {
    private String isbn;
    private String articleType;
    private String title;
    private String publisher;
    private String publicationYear;
    private String genre;
    private String author;
    private boolean available;
   /* Add eventually, needs work on database side
    private int numberOfBooksInStock;
    */


    public BookModel(String isbn, String articleType, String title, String publisher, String publicationYear, String genre, String author, int available) {
        this.isbn = isbn;
        this.articleType = articleType;
        this.title = title;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.author = author;
        setAvailable(available);
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

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(int value) {
        if (value == 1) {
            this.available = true;
        }
        else {
            this.available = false;
        }
    }
}
