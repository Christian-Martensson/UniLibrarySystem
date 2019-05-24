import Controller.MainController;
import Models.SearchModel;
import UI.Views.MainView;
import UI.Views.MessageView;

public class App {

	public static void main(String[] args) {
		MainView mainView = new MainView();
		SearchModel searchEvent = new SearchModel();
		MainController mainController = new MainController(mainView, searchEvent);
		mainView.setVisible(true);
		MainController.giveDefaultViewAccess();

		String text = "" +
				"\nWelcome to UniLibrarySystem!" +
				"\n\nTo log in with admin access, please use the following login credentials:" +
				"\nUsername: 'admin' " +
				"\nPassword: 'admin'" +
				"\n\nTo search for all items in each respective category at once, please press the search button " +
				"without entering anything into the search field.";
		MessageView message = new MessageView(text);
	}
}
