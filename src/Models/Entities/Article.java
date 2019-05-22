package Models.Entities;

class Article {
    String title;
    String publicationYear;
    String genre;
    String creator;
    boolean available;
    int numberInStock;


    public String getAuthor() {
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

    void setAvailable(int value) {
        if (value == 1) {
            this.available = true;
        }
        else {
            this.available = false;
        }
    }
}



