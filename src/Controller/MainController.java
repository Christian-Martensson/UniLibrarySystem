package Controller;

import Models.Entities.BookModel;
import Models.SearchModel;
import UI.UI_Components.ScrollPanel;
import UI.Views.*;

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
        this.view.getBottomToolBar().addLoanButtonListener(new LoanButtonListener());
        this.view.getBottomToolBar().addReserveButtonListener(new ReserveButtonListener());
        this.view.getBottomToolBar().addEditButtonListener(new EditButtonListener());
        this.view.getBottomToolBar().addRemoveButtonListener(new RemoveButtonListener());
        this.view.getBottomToolBar().addOverdueItemsButtonListener(new OverdueItemsButtonListener());
    }

    class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchWord = view.getToolbar().getTextField().getText();
            String searchAlternative = view.getToolbar().getSearchAlternativesDropdown().getSelectedItem().toString();

            switch (searchAlternative) {
                case "Book": {
                    model.searchBookFor(searchWord);

                    JTable table = model.convertListOfBooksToJTable();
                    view.getScrollPanel().appendSearchResult(table);
                    break;
                }
                case "Movie": {
                    System.out.println("Movie search not implemented");
                    model.searchMovie(searchWord);

                    JTable table = model.convertListOfMoviesToTable();
                    view.getScrollPanel().appendSearchResult(table);
                    break;
                }
                case "Magazine": {
                    System.out.println("Magazine search not implemented");
                    break;
                }
                case "User": {
                    model.searchUser(searchWord);

                    JTable table = model.convertListOfUsersToJTable();
                    view.getScrollPanel().appendSearchResult(table);
                    break;
                }
            }

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


    class LoanButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(ScrollPanel.getTable().getSelectionModel().isSelectionEmpty()) {
                ErrorMessageView error = new ErrorMessageView("You must select an item to loan!");
            }
            else {

                int row = ScrollPanel.getTable().getSelectedRow();
                int column = 0;
                String valueIsbn = ScrollPanel.getTable().getValueAt(row, column).toString();
                BookModel book = model.getBookWith(valueIsbn);

                LoanConfirmationView loanConfirmationView = new LoanConfirmationView(book);
                LoanConfirmationController loanConfirmationController = new LoanConfirmationController(loanConfirmationView, book);

            }
        }
    }

    class ReserveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(ScrollPanel.getTable().getSelectionModel().isSelectionEmpty()) {
                ErrorMessageView error = new ErrorMessageView("You must select an item to loan!");
            }
            else {

                int row = ScrollPanel.getTable().getSelectedRow();
                int column = 0;
                String valueIsbn = ScrollPanel.getTable().getValueAt(row, column).toString();
                BookModel book = model.getBookWith(valueIsbn);

                ReservationConfirmationView reservationConfirmationView = new ReservationConfirmationView(book);
                ReservationConfirmationController reservationConfirmationController = new ReservationConfirmationController(reservationConfirmationView, book);

            }

        }
    }

    class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class OverdueItemsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }



    public static void giveLibrarianAccess() {
        view.getFormPanel().setVisible(true);
        view.getToolbar().getSearchAlternativesDropdown().addItem("User");
        view.getBottomToolBar().setVisible(true);
        view.getBottomToolBar().getEditSelectedItemButton().setVisible(true);
        view.getBottomToolBar().getRemoveSelectedItemButton().setVisible(true);
        view.getBottomToolBar().getOverdueItemsButton().setVisible(true);

    }

    public static void givePatronViewAccess() {
        giveDefaultViewAccess();
        view.getBottomToolBar().setVisible(true);
    }

    public static void giveDefaultViewAccess() {
        view.getBottomToolBar().setVisible(false);
        view.getFormPanel().setVisible(false);
        view.getToolbar().getSearchAlternativesDropdown().removeItem("User");
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

