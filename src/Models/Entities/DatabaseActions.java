package Models.Entities;

public interface DatabaseActions {

    public void createLoan(int barcodeId, int userId);

    public boolean checkAvailabilityInDb();

    public void loadToDb();

    public int getAvailableBarcode();

}
