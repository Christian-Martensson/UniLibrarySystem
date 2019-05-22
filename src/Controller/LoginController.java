package Controller;

import Models.Entities.UserModel;
import UI.Views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView view;
    private UserModel userModel;

    //Controls login - checks if right credentials for authentication of user or not.
    public LoginController(LoginView view){
            this.view = view;
            userModel = new UserModel();

            this.view.addLoginButtonListener(new LoginButtonListener());
    }

    public void getUserFromDB(String username){
        userModel.setUsername(username);
        userModel.populateuser();
    }


    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String usernameFieldValue = view.getTxtUsername().getText();
            String passwordFieldValue = view.getTxtPassword().getText();

            getUserFromDB(usernameFieldValue);

            if (passwordFieldValue.equals(userModel.getPassword())) {
                view.setErrorMessage("Login Success!");
                MainController.setLogoutButton();
                MainController.loggedInUser = userModel;

                if (userModel.getUserType().equals("librarian")) {
                    MainController.giveLibrarianAccess();
                }
                else {
                    MainController.givePatronViewAccess();
                }
            }
            else {
                view.setErrorMessage("Login Failed!");
            }
        }
    }
}

