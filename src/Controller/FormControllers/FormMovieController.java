package Controller.FormControllers;


import Models.Entities.MovieModel;
import UI.Views.FormView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMovieController {
    FormView view;
    MovieModel movieModel;


    public FormMovieController(FormView formView) {
        this.view = formView;

        this.view.addSubmitButtonListener(new SubmitButtonListener());
    }


    class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = view.getText(0);
            String publicationYear = view.getText(1);
            String producer = view.getText(2);
            String genre = view.getText(3);
            String articleType = view.getText(4);

            movieModel = new MovieModel(0, 18, title, publicationYear, genre, producer);
            movieModel.insertIntoDb();

        }
    }
}
