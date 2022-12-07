package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BankingApplicationAccountDetailsController {

	public Stage applicationStage;	
	
	private BankAccount myBankAccount;
	
	private Scene myScene;
	
	private BankingApplicationController nextController;
	
	@FXML
    private Label accountNumberLabel;

    @FXML
    private Label accountCurrentBalanceLabel;

    @FXML
    private Label accountNameLabel;
    
    @FXML
    private Button depositButton;
    
    @FXML
    private Button withdrawButton;
    
    private ArrayList<BankAccount> bankAccounts;
	
	/* the creation of the setApplicationStage(). setScene(),setNextController(), and takeFocus() methods were all used based
	 * on what was suggested in the Nov25_Using Multiple FXML files presentation which is under content in the CPSC 219 D2L shell
	 */
	
	public void setApplicationStage(Stage aStage) {
		applicationStage = aStage;
	}
	
	public void setBankAccount(BankAccount bankAccount) {
		myBankAccount = bankAccount;
		
	}
	
	public void setListOfBankAccounts(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	
	public void setScene(Scene accountDetailsScene) {
		myScene = accountDetailsScene;
		applicationStage.setTitle("Account Details");
		accountNameLabel.setText("Account: " + myBankAccount.getAccountName());
	    accountNumberLabel.setText("Account Number: " + myBankAccount.getAccountNumber());
	    accountCurrentBalanceLabel.setText(String.format("Current Balance: $%.2f CAD", myBankAccount.getBalance()));
	}
	
	public void setNextController(BankingApplicationController next) {
		nextController = next;
	}
	
	public void takeFocus() {
		applicationStage.setScene(myScene);
	}
	
	public void returnToAccountDetailsScene() {
		applicationStage.setTitle("Account Details");
		accountNameLabel.setText("Account: " + myBankAccount.getAccountName());
	    accountNumberLabel.setText("Account Number: " + myBankAccount.getAccountNumber());
	    accountCurrentBalanceLabel.setText(String.format("Current Balance: $%.2f CAD", myBankAccount.getBalance()));
	    applicationStage.setScene(myScene);
	}
	
	public void getDepositInformation(ActionEvent event) {
		VBox allRows = new VBox();
		Scene depositInfoScene = new Scene(allRows,600,600);
		Label enterAmountPrompt = new Label("Enter the amount that you would like to deposit: ");
		VBox.setMargin(enterAmountPrompt, new Insets(10,10,10,10));
		TextField enterAmountTextfield = new TextField();
		Label currencyLabel = new Label("CAD");
		HBox enterAmountRow = new HBox();
		enterAmountRow.getChildren().addAll(enterAmountTextfield,currencyLabel);
		HBox.setMargin(enterAmountTextfield, new Insets(0,5,10,10));
		HBox.setMargin(currencyLabel, new Insets(0,10,10,5));
		Button depositMoneyButton = new Button("Deposit money");
		depositMoneyButton.setOnAction(doneEvent -> myBankAccount.deposit(Double.parseDouble(enterAmountTextfield.getText())));
		Button returnButton = new Button("Confirm");
		returnButton.setOnAction(doneEvent -> returnToAccountDetailsScene());
		HBox buttonRow = new HBox();
		buttonRow.getChildren().addAll(depositMoneyButton,returnButton);
		HBox.setMargin(depositMoneyButton, new Insets(10,10,10,10));
		HBox.setMargin(returnButton, new Insets(10,10,10,10));
		allRows.getChildren().addAll(enterAmountPrompt,enterAmountRow,buttonRow);
		applicationStage.setScene(depositInfoScene);
	}
	
	@FXML
	public void getAccountsSummaryScene(ActionEvent event) {
		if (nextController != null) {
			nextController.takeFocus();
    	}
	}

	public void getWithdrawalInformation(ActionEvent event) {
		VBox allRows = new VBox();
		Scene withdrawalInfoScene = new Scene(allRows,600,600);
		Label enterAmountPrompt = new Label("Enter the amount that you would like to withdraw: ");
		VBox.setMargin(enterAmountPrompt, new Insets(10,10,10,10));
		TextField enterAmountTextfield = new TextField();
		Label currencyLabel = new Label("CAD");
		HBox enterAmountRow = new HBox();
		enterAmountRow.getChildren().addAll(enterAmountTextfield,currencyLabel);
		HBox.setMargin(enterAmountTextfield, new Insets(0,5,10,10));
		HBox.setMargin(currencyLabel, new Insets(0,10,10,5));
		Button withdrawMoneyButton = new Button("Withdraw money");
		withdrawMoneyButton.setOnAction(doneEvent -> myBankAccount.withdraw(Double.parseDouble(enterAmountTextfield.getText())));
		Button returnButton = new Button("Return to Account Details");
		returnButton.setOnAction(doneEvent -> returnToAccountDetailsScene());
		HBox buttonRow = new HBox();
		buttonRow.getChildren().addAll(withdrawMoneyButton,returnButton);
		HBox.setMargin(withdrawMoneyButton, new Insets(10,10,10,10));
		HBox.setMargin(returnButton, new Insets(10,10,10,10));
		allRows.getChildren().addAll(enterAmountPrompt,enterAmountRow,buttonRow);
		applicationStage.setScene(withdrawalInfoScene);
	}
	
	public void getTransferInformation(ActionEvent event) {
		VBox allRows = new VBox();
		Scene transferInfoScene = new Scene(allRows,600,600);
		Label enterAmountPrompt = new Label("Enter the amount that you would like to transfer: ");
		VBox.setMargin(enterAmountPrompt, new Insets(10,10,10,10));
		TextField enterAmountTextfield = new TextField();
		Label currencyLabel = new Label("CAD");
		HBox enterAmountRow = new HBox();
		enterAmountRow.getChildren().addAll(enterAmountTextfield,currencyLabel);
		HBox.setMargin(enterAmountTextfield, new Insets(0,5,10,10));
		HBox.setMargin(currencyLabel, new Insets(0,10,10,5));
		Label accountToTransferIntoPrompt = new Label("Choose which account you would like to transfer "
				+ "money into:");
		ChoiceBox<BankAccount> transferIntoAccountChoiceBox = new ChoiceBox<BankAccount>();
		Button transferMoneyButton = new Button("Transfer money");
		transferMoneyButton.setOnAction(doneEvent -> myBankAccount.withdraw(Double.parseDouble(enterAmountTextfield.getText())));
		Button returnButton = new Button("Return to Account Details");
		returnButton.setOnAction(doneEvent -> returnToAccountDetailsScene());
		HBox buttonRow = new HBox();
		buttonRow.getChildren().addAll(transferMoneyButton,returnButton);
		HBox.setMargin(transferMoneyButton, new Insets(10,10,10,10));
		HBox.setMargin(returnButton, new Insets(10,10,10,10));
		allRows.getChildren().addAll(enterAmountPrompt,enterAmountRow,accountToTransferIntoPrompt,transferIntoAccountChoiceBox,
				buttonRow);
		VBox.setMargin(accountToTransferIntoPrompt, new Insets(10,10,10,10));
		VBox.setMargin(transferIntoAccountChoiceBox, new Insets(10,10,10,10));
		applicationStage.setScene(transferInfoScene);
	}

	
}

