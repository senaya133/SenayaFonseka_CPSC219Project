package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
    void showAccountsSummaryScene(ActionEvent event) {
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
        	HBox bankAccountsHeader = new HBox();
        	Label bankAccountsTitle = new Label();
        	bankAccountsTitle.setText("Bank Accounts");
        	HBox.setMargin(bankAccountsTitle, new Insets(0,5,10,5));
        	Label totalAmountLabel = new Label();
        	totalAmountLabel.setText("Total: ");
        	HBox.setMargin(totalAmountLabel, new Insets(0,5,10,200));
        	ChequingAccount primaryChequingAccount = new ChequingAccount("Chequing Account");
        	bankAccountsHeader.getChildren().addAll(bankAccountsTitle,totalAmountLabel);
        	allRows.getChildren().addAll(welcomeLabel,accountsSummaryTitle,bankAccountsHeader);
        	
        	applicationStage.setScene(accountsSummaryScene);
        }
        else {
        	enterNameErrorLabel.setText("Error: You need not enter a name. Please enter your full name");
        }
    }

}



