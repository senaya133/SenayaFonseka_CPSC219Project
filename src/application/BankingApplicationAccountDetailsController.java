package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class BankingApplicationAccountDetailsController {

	public Stage applicationStage;	
	private Scene myScene;
	private BankingApplicationController nextController;
	
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
