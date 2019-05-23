package Models.Entities;

public interface DatabaseActions {

    void createLoan(int barcodeId, int userId);

    boolean checkAvailabilityInDb();

    void loadToDb();

    int getAvailableBarcode();

    void removeFromDb();

}
