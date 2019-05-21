package Controller;

import Model.LoginModel;
import UI.Views.LoginView;
import Controller.MainController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView view;
    private LoginModel model;
    private boolean loginSuccess;


    //Controlls login - checks if right credentials for authentication of user or not.
    public LoginController(LoginView view){
            this.view = view;
            model = new LoginModel();

            this.view.addLoginButtonListener(new LoginButtonListener());
    }

    //This method first ask for the data of user to the Model class.
    public boolean checkCredentials(String username, String password){
        boolean loginSuccess = false;

        model.setUsername(username);
        model.getCredentials();
        if(password.equals(model.getPassword())){
            view.setErrorMessage("Login Success!");
            loginSuccess = true;
        }
        else{
            view.setErrorMessage("Login Failed!");
        }
        // If the username and password makes appropriate match the "Login Success!"
        // message is passed to View other wise "Login Failed!" message is passed.
        return loginSuccess;
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginSuccess = checkCredentials(view.getTxtUsername().getText(), view.getTxtPassword().getText());
            if (loginSuccess) {
                MainController.giveLibrarianAccess();
            }
        }
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }
}

