package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
    	VBox allRows = new VBox();
    	Scene accountsSummaryScene = new Scene(allRows,600,600);
        String nameEntered = enterNameTextfield.getText();
        if (nameEntered != "") {
        	Label welcomeLabel = new Label();
        	welcomeLabel.setText("Welcome " + nameEntered + "!");
        	VBox.setMargin(welcomeLabel, new Insets(10,5,10,5));
        	Label accountsSummaryTitle = new Label();
        	accountsSummaryTitle.setText("Accounts Summary");
        	VBox.setMargin(accountsSummaryTitle, new Insets(10,5,10,5));
        	allRows.getChildren().addAll(welcomeLabel,accountsSummaryTitle);
        	
        	applicationStage.setScene(accountsSummaryScene);
        }
        else {
        	enterNameErrorLabel.setText("Error: You need not enter a name. Please enter your full name");
        }
    }

}



