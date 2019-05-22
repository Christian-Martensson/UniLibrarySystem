package Models.Entities;

public class BookModel extends Article {
    private String isbn;
    private String articleType;
    private String publisher;



    public BookModel(String isbn, String articleType, String title, String publisher, String publicationYear, String genre, String author, int available) {
        this.isbn = isbn;
        this.articleType = articleType;
        super.title = title;
        this.publisher = publisher;
        super.publicationYear = publicationYear;
        super.genre = genre;
        super.creator = author;
        super.setAvailable(available);
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

    public boolean isAvailable() {
        return available;
    }

}
