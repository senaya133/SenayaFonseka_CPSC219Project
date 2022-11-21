package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BankingApplicationController {
	
	Stage applicationStage;
	
	@FXML
    private TextField enterNameTextfield;
	
	@FXML
	private Label enterNameErrorLabel;

    @FXML
    void showAccountsSummary(ActionEvent event) {
    	Scene introScene = applicationStage.getScene();
    	VBox allRows = new VBox();
    	Scene accountsSummaryScene = new Scene(allRows,600,600);
        String nameEntered = enterNameTextfield.getText();
        if (nameEntered != "") {
        	applicationStage.setScene(accountsSummaryScene);
        }
        else {
        	enterNameErrorLabel.setText("Error: You need not enter a name. Please enter your full name");
        }
    }

}



