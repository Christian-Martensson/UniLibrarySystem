import Model.SearchEvent;
import UI.MainView;

public class App {

	public static void main(String[] args) {
		MainView mainView = new MainView();
		SearchEvent searchEvent = new SearchEvent();

		MainController mainController = new MainController(mainView, searchEvent);

		mainView.setVisible(true);


/*		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new UI.MainView();
			}
		});	*/
	}

}
