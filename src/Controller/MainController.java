package Controller;

import Controller.FormControllers.FormBookController;
import Controller.FormControllers.FormMagazineController;
import Controller.FormControllers.FormMovieController;
import Controller.FormControllers.FormUserController;
import Models.Entities.*;
import Models.SearchModel;
import UI.UI_Components.ScrollPanel;
import UI.Views.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainController {
    private static MainView view;
    private SearchModel model;

    public static boolean loggedIn = false;
    public static String searchAlternativeWhenPressed;
    public static String addItemAlternative;
    public static UserModel loggedInUser;

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
        this.view.getBottomToolBar().addAddButtonListener(new AddButtonListener());
    }

    class SearchButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String searchWord = view.getToolbar().getTextField().getText();
            searchAlternativeWhenPressed = view.getToolbar().getSearchAlternativesDropdown().getSelectedItem().toString();

            switch (searchAlternativeWhenPressed) {
                case "Book": {
                    model.searchBook(searchWord);

                    JTable table = model.convertListOfBooksToJTable();
                    view.getScrollPanel().appendSearchResult(table);
                    break;
                }
                case "Movie": {
                    model.searchMovie(searchWord);

                    JTable table = model.convertListOfMoviesToTable();
                    view.getScrollPanel().appendSearchResult(table);
                    break;
                }
                case "Magazine": {
                    model.searchMagazine(searchWord);

                    JTable table = model.convertListOfMagazinesToTable();
                    view.getScrollPanel().appendSearchResult(table);
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

                switch (searchAlternativeWhenPressed) {
                    case "Book": {
                        String valueIsbn = ScrollPanel.getTable().getValueAt(row, column).toString();
                        BookModel book = model.getBookWith(valueIsbn);

                        LoanConfirmationView loanConfirmationView = new LoanConfirmationView(book);
                        LoanConfirmationController loanConfirmationController = new LoanConfirmationController(loanConfirmationView, book);
                        break;
                    }
                    case "Movie": {
                        int value = Integer.parseInt(ScrollPanel.getTable().getValueAt(row, column).toString());
                        MovieModel movie = model.getMovieWith(value);
                        LoanConfirmationView loanConfirmationView = new LoanConfirmationView(movie);
                        LoanConfirmationController loanConfirmationController = new LoanConfirmationController(loanConfirmationView, movie);

                        break;
                    }
                    case "Magazine": {
                        ErrorMessageView error = new ErrorMessageView("You can not loan a magazine.");
                        break;
                    }
                    case "User": {
                        ErrorMessageView error = new ErrorMessageView("You can not loan a user.");
                        break;
                    }
                }




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
            if(ScrollPanel.getTable().getSelectionModel().isSelectionEmpty()) {
                ErrorMessageView error = new ErrorMessageView("You must select an item to loan!");
            }
            else {
                int row = ScrollPanel.getTable().getSelectedRow();
                int column = 0;

                switch (searchAlternativeWhenPressed) {
                    case "Book": {
                        String valueIsbn = ScrollPanel.getTable().getValueAt(row, column).toString();
                        BookModel book = model.getBookWith(valueIsbn);

                        LoanConfirmationView loanConfirmationView = new LoanConfirmationView(book);
                        LoanConfirmationController loanConfirmationController = new LoanConfirmationController(loanConfirmationView, book);
                        break;
                    }
                    case "Movie": {
                        int value = Integer.parseInt(ScrollPanel.getTable().getValueAt(row, column).toString());
                        MovieModel movie = model.getMovieWith(value);
                        LoanConfirmationView loanConfirmationView = new LoanConfirmationView(movie);
                        LoanConfirmationController loanConfirmationController = new LoanConfirmationController(loanConfirmationView, movie);

                        break;
                    }
                    case "Magazine": {
                        ErrorMessageView error = new ErrorMessageView("You can not loan a magazine.");
                        break;
                    }
                    case "User": {
                        ErrorMessageView error = new ErrorMessageView("You can not loan a user.");
                        break;
                    }
                }




            }

        }
    }

    class OverdueItemsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<LoanModel> listOfLoans = LoanModel.fetchOverdueLoansFromDBfor();
            JTable table = SearchModel.converListOfLoansToTable(listOfLoans);
            OverdueItemsView view = new OverdueItemsView(table);

        }
    }

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            addItemAlternative = view.getBottomToolBar().getAddAlternativesDropdown().getSelectedItem().toString();

            switch (addItemAlternative) {
                case "Book": {
                    FormView formV = FormView.generateFormBook();
                    FormBookController formC = new FormBookController(formV);
                    break;
                }
                case "Movie": {
                    FormView formV = FormView.generateFormMovie();
                    FormMovieController formC = new FormMovieController(formV);
                    break;
                }
                case "Magazine": {
                    FormView formV = FormView.generateFormMagazine();
                    FormMagazineController formC = new FormMagazineController(formV);
                    break;
                }
                case "User": {
                    FormView formV = FormView.generateFormUser();
                    FormUserController formC = new FormUserController(formV);
                    break;
                }
            }

        }
    }


    public static void giveLibrarianAccess() {
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

