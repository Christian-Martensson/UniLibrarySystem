import Model.SearchModel;
import UI.MainView;

public class App {

	public static void main(String[] args) {
		MainView mainView = new MainView();
		SearchModel searchEvent = new SearchModel();

		MainController mainController = new MainController(mainView, searchEvent);

		mainView.setVisible(true);


		//Josefine Testar nu ny push
		System.out.println("Test");
	}

}
