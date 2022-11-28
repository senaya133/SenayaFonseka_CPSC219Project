package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class BankingApplicationAccountDetailsController {

	public Stage applicationStage;	
	private Scene myScene;
	private BankingApplicationController nextController;
	
	/* the creation of the setApplicationStage(). setScene(),setNextController(), and takeFocus() methods were all used based
	 * on what was suggested in the Nov25_Using Multiple FXML files presentation which is under content in the CPSC 219 D2L shell
	 */
	
	public void setApplicationStage(Stage aStage) {
		applicationStage = aStage;
	}
	
	public void setScene(Scene accountDetailsScene) {
		myScene = accountDetailsScene;
		applicationStage.setTitle("Account Details");
	}
	
	public void setNextController(BankingApplicationController next) {
		nextController = next;
	}
	
	public void takeFocus() {
		applicationStage.setScene(myScene);
	}
}
