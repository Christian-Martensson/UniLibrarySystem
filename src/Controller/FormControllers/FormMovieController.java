package Controller.FormControllers;


import Models.Entities.MovieModel;
import UI.Views.FormView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMovieController {
    FormView view;
    MovieModel movieModel;
    final int ADDING = 1;
    final int EDITING = 2;
    int option;


    public FormMovieController(FormView formView, int option) {
        this.option = option;
        this.view = formView;

        this.view.addSubmitButtonListener(new SubmitButtonListener());
    }


    class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (option == ADDING) {
                String title = view.getText(0);
                String publicationYear = view.getText(1);
                String producer = view.getText(2);
                String genre = view.getText(3);
                movieModel = new MovieModel(0, 18, title, publicationYear, genre, producer);
                movieModel.insertIntoDb();
                movieModel.addBarcodesInDb(1, title);
            }
            if (option == EDITING) {
                int movieId = Integer.parseInt(view.getText(0));
                String title = view.getText(1);
                String publicationYear = view.getText(2);
                String producer = view.getText(3);
                String genre = view.getText(4);
                movieModel = new MovieModel(movieId, 18, title, publicationYear, genre, producer);
                movieModel.updateInDb();
            }
        }
    }
}
