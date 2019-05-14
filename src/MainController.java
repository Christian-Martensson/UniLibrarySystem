import Model.SearchEvent;
import UI.MainView;

import javax.swing.*;
import java.awt.event.*;

public class MainController {
    private MainView mainView;
    private SearchEvent searchEvent;

    private boolean loggedIn;


    public MainController(MainView mainView, SearchEvent searchModel) {
        this.mainView = mainView;
        this.searchEvent = searchModel;

        this.mainView.getToolbar().addLoginButtonListener(new LoginButtonListener());
        this.mainView.getToolbar().addSearchButtonListener(new SearchButtonListener());
    }

    class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchWord = mainView.getToolbar().getTextField().getText();
            searchEvent.searchInDatabase(searchWord);

            JTable table = searchEvent.displayBooks();
            mainView.getScrollPanel().appendSearchResult(table);

        }
    }

    class LoginButtonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (loggedIn) {
                loggedIn = false;
                mainView.getToolbar().getLoginButton().setText("Log in");
            }
            else {
                loggedIn = true;
                mainView.getToolbar().getLoginButton().setText("Log out");
            }
        }
    }

}
