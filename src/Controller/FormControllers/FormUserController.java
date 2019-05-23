package Controller.FormControllers;


import Models.Entities.UserModel;
import UI.Views.FormView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUserController {
    FormView view;
    UserModel userModel;
    final int ADDING = 1;
    final int EDITING = 2;
    int option;


    public FormUserController(FormView formView, int option) {
        this.option = option;
        this.view = formView;

        this.view.addSubmitButtonListener(new SubmitButtonListener());
    }


    class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(option == ADDING) {
                String firstName = view.getText(0);
                String lastName = view.getText(1);
                String personalId = view.getText(2);
                String userType = view.getText(3);
                String Street = view.getText(4);
                String zipCode = view.getText(5);
                String city = view.getText(6);
                String country = view.getText(7);
                String telephone = view.getText(8);
                String mail = view.getText(9);
                String username = view.getText(10);
                String password = view.getText(11);
                UserModel user = new UserModel(userType, firstName, lastName, personalId, username, password);

                user.insertIntoDb();
            }

            else if (option == EDITING) {
                int movieId = Integer.parseInt(view.getText(0));
                String firstName = view.getText(1);
                String lastName = view.getText(2);
                String personalId = view.getText(3);
                String userType = view.getText(4);
                String Street = view.getText(5);
                String zipCode = view.getText(6);
                String city = view.getText(7);
                String country = view.getText(8);
                String telephone = view.getText(9);
                String mail = view.getText(10);
                String username = view.getText(11);
                String password = view.getText(12);
                UserModel user = new UserModel(userType, firstName, lastName, personalId, username, password);

                user.updateInDb();
            }


        }
    }
}
