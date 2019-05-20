package Controller;

import Model.SearchModel;
import UI.Views.DefaultView;
import UI.Views.LoginView;

import javax.swing.*;
import java.awt.event.*;

public class MainController {
    private DefaultView defaultView;
    private SearchModel searchModel;

    private boolean loggedIn = false;


    public MainController(DefaultView mainView, SearchModel searchModel) {
        this.defaultView = mainView;
        this.searchModel = searchModel;

        this.defaultView.getToolbar().addLoginButtonListener(new LoginButtonListener());
        this.defaultView.getToolbar().addSearchButtonListener(new SearchButtonListener());
    }

    class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchWord = defaultView.getToolbar().getTextField().getText();
            searchModel.searchInDatabase(searchWord);

            JTable table = searchModel.displayBooks();
            defaultView.getScrollPanel().appendSearchResult(table);

        }
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!loggedIn) {
                // Opens a new window with login dialogue
                LoginView lv = new LoginView();
            }

        }
    }

}

