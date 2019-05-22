package Models.Entities;

class Article {
    String articleType;
    String title;
    String publicationYear;
    String genre;
    String creator;
    boolean available;
    int numberInStock;

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

    public boolean isAvailable() {
        return available;
    }

    void setAvailable(int value) {
        if (value == 1) {
            this.available = true;
        }
        else {
            this.available = false;
        }
    }
}



