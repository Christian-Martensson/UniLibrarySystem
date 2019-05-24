package Controller.FormControllers;

import Models.Entities.MagazineModel;
import UI.Views.FormView;
import UI.Views.MessageView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FormMagazineController {
    private FormView view;
    private MagazineModel magazineModel;
    private final int ADDING = 1;
    private final int EDITING = 2;
    private int option;


    public FormMagazineController(FormView formView, int option) {
        this.option = option;
        this.view = formView;

        this.view.addSubmitButtonListener(new SubmitButtonListener());
    }


    class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        try {
            if(option == ADDING) {
                if (view.getText(0).equals("")) {
                    String text = "Error! \n\n" +
                            "You must enter a value in the first row.";
                    MessageView error = new MessageView(text);
                }
                else {
                    int magazineNr = Integer.parseInt(view.getText(0));
                    String title = view.getText(1);
                    String publisher = view.getText(2);
                    String publicationDate = view.getText(3);
                    String genre = view.getText(4);
                    magazineModel = new MagazineModel(0, magazineNr, publisher, publicationDate, title, genre);
                    magazineModel.insertIntoDb();
                    magazineModel.addBarcodesInDb(1, title);
                }
            }


            else if (option == EDITING) {
                int magazineId = Integer.parseInt(view.getText(0));
                int magazineNr = Integer.parseInt(view.getText(1));
                String title = view.getText(2);
                String publisher = view.getText(3);
                String publicationDate = view.getText(4);
                String genre = view.getText(5);
                magazineModel = new MagazineModel(magazineId, magazineNr, publisher, publicationDate, title, genre);
                magazineModel.updateInDb();
            }
        } catch (Exception ex) {
            String text = "Error! \n\n" +
                    "You must enter a value for Magazine Nr.";
            MessageView error = new MessageView(text);
        }


        }
    }
}
