package Controller;

import Models.Entities.UserModel;
import Models.SearchModel;
import UI.Views.MainView;
import UI.Views.LoginView;

import javax.swing.*;
import java.awt.event.*;

public class MainController {
    private static MainView view;
    private SearchModel model;

    public static boolean loggedIn = false;

    public MainController(MainView mainView, SearchModel searchModel) {
        this.view = mainView;
        this.model = searchModel;

        this.view.getToolbar().addLoginButtonListener(new LoginButtonListener());
        this.view.getToolbar().addSearchButtonListener(new SearchButtonListener());
    }

    class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchWord = view.getToolbar().getTextField().getText();
            model.searchBookFor(searchWord);

            JTable table = model.displayBooks();
            view.getScrollPanel().appendSearchResult(table);

        }
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!loggedIn) {
                // Opens a new window with login dialogue
                LoginView lv = new LoginView();
                LoginController lc = new LoginController(lv);

            }
            else {
                loggedIn = false;
                setLoginButton();
                giveDefaultViewAccess();
            }
        }
    }

    public static void giveLibrarianAccess() {
        view.getFormPanel().setVisible(true);
        view.getBottomToolBar().setVisible(true);
        view.getBottomToolBar().getEditSelectedItemButton().setVisible(true);
        view.getBottomToolBar().getRemoveSelectedItemButton().setVisible(true);
        view.getBottomToolBar().getOverdueItemsButton().setVisible(true);
        view.getToolbar().getCheckbox().setVisible(true);
        view.getToolbar().getCheckboxLabel().setVisible(true);
    }

    public static void givePatronViewAccess() {
        giveDefaultViewAccess();
        view.getBottomToolBar().setVisible(true);
    }

    public static void giveDefaultViewAccess() {
        view.getBottomToolBar().setVisible(false);
        view.getFormPanel().setVisible(false);
        view.getToolbar().getCheckbox().setVisible(false);
        view.getToolbar().getCheckboxLabel().setVisible(false);
        view.getBottomToolBar().getEditSelectedItemButton().setVisible(false);
        view.getBottomToolBar().getRemoveSelectedItemButton().setVisible(false);
        view.getBottomToolBar().getOverdueItemsButton().setVisible(false);
    }

    public static void setLogoutButton() {
        view.getToolbar().getLoginButton().setText("Log out");
    }
    public static void setLoginButton() {
        view.getToolbar().getLoginButton().setText("Log in");
    }

}

