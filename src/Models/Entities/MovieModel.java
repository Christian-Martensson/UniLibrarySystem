package Models.Entities;

public class MovieModel extends Article {

    private int movieId;
    private int minimumAge;


    public MovieModel(int movieId, int minimumAge, String articleType, String title, String publicationYear, String genre, String producer, int available) {
        this.movieId = movieId;
        this.minimumAge = minimumAge;
        super.articleType = articleType;
        super.title = title;
        super.publicationYear = publicationYear;
        super.genre = genre;
        super.creator = producer;
        super.setAvailable(available);
    }


    public int getMovieId() {
        return movieId;
    }

    public int getMinimumAge() {
        return minimumAge;
    }
}
