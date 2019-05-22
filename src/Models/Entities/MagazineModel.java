package Models.Entities;

import java.util.Date;

public class MagazineModel extends Article {
    private int magazineId;
    private int magazineNr;
    private String publisher;
    private Date publicationDate;

    public MagazineModel(int magazineId, int magazineNr, String publisher, Date publicationDate, String title, String genre) {
        this.magazineId = magazineId;
        this.magazineNr = magazineNr;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        super.title = title;
        super.genre = genre;
    }

    public int getMagazineId() {
        return magazineId;
    }

    public int getMagazineNr() {
        return magazineNr;
    }

    public String getPublisher() {
        return publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }
}
