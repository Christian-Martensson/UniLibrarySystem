package Controller.FormControllers;

import Models.Entities.MagazineModel;
import UI.Views.FormView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FormMagazineController {
    FormView view;
    MagazineModel magazineModel;
    final int ADDING = 1;
    final int EDITING = 2;
    int option;


    public FormMagazineController(FormView formView, int option) {
        this.option = option;
        this.view = formView;

        this.view.addSubmitButtonListener(new SubmitButtonListener());
    }


    class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(option == ADDING) {
                int magazineNr = Integer.parseInt(view.getText(0));
                String title = view.getText(1);
                String publisher = view.getText(2);
                String publicationDate = view.getText(3);
                String genre = view.getText(4);
                magazineModel = new MagazineModel(0, magazineNr, publisher, publicationDate, title, genre);
                magazineModel.insertIntoDb();
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

        }
    }
}
