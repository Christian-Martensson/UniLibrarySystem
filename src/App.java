import Controller.MainController;
import Model.SearchModel;
import UI.Views.DefaultView;
import UI.Views.LibrarianView;

public class App {

	public static void main(String[] args) {
		DefaultView defaultView = new DefaultView();
		SearchModel searchEvent = new SearchModel();
		MainController mainController = new MainController(defaultView, searchEvent);
		defaultView.setVisible(true);
	}

}
