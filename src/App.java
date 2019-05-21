import Controller.MainController;
import Models.SearchModel;
import UI.Views.MainView;

public class App {

	public static void main(String[] args) {
		MainView librarianView = new MainView();
		SearchModel searchEvent = new SearchModel();
		MainController mainController = new MainController(librarianView, searchEvent);
		librarianView.setVisible(true);
	}

}
