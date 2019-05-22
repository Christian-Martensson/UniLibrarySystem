package Controller.FormControllers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyConversionHelper {

    public static Date fromStringToDate(String text) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(text);
        }
        catch (Exception e) {
            System.out.println("Parsing error");
        }

        return date;
    }
}
