package Controller.FormControllers;

import Models.Entities.MagazineModel;
import UI.Views.FormView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class FormMagazineController {
    FormView view;
    MagazineModel magazineModel;


    public FormMagazineController(FormView formView) {
        this.view = formView;

        this.view.addSubmitButtonListener(new SubmitButtonListener());
    }


    class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int magazineNr = Integer.parseInt(view.getText(0));
            String title = view.getText(1);
            String publisher = view.getText(2);
            Date publicationDate = MyConversionHelper.fromStringToDate(view.getText(3));
            String genre = view.getText(4);
            magazineModel = new MagazineModel(0, magazineNr, publisher, publicationDate, title, genre);
            magazineModel.insertIntoDb();

        }
    }
}
