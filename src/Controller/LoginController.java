package Controller;

import Model.LoginModel;
import UI.Views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView view;
    private LoginModel model;

    //Controlls login - checks if right credentials for authentication of user or not.
    public LoginController(LoginView view){
            this.view = view;
            model = new LoginModel();

            this.view.addLoginButtonListener(new LoginButtonListener());
    }

    //This method first ask for the data of user to the Model class.
    public void checkCredentials(String username, String password){
        model.setUsername(username);
        model.getCredentials();
        if(password.equals(model.getPassword())){
            view.setErrorMessage("Login Success!");
        }
        else{
            view.setErrorMessage("Login Failed!");
        }
        // If the username and password makes appropriate match the "Login Success!"
        // message is passed to View other wise "Login Failed!" message is passed.
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkCredentials(view.getTxtUsername().getText(), view.getTxtPassword().getText());

        }
    }
}

