package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
    
    @FXML 
    private Label additionalAccountInfoMessage;
    
    private ArrayList<BankAccount> bankAccounts;
	
	/* the creation of the setApplicationStage(). setScene(),setNextController(), and takeFocus() methods were all used based
	 * on what was suggested in the Nov25_Using Multiple FXML files presentation which is under content in the CPSC 219 D2L shell
	 */
	/**
	 * Sets the stage for the account details scene to be set on
	 * @param aStage 
	 */
	public void setApplicationStage(Stage aStage) {
		applicationStage = aStage;
	}
	
	/**
	 * Sets the bank account of interest for this controller when generating the account details scene
	 * so the information from the bank account (ie. account name, account number, balance, etc) is 
	 * accessible from this controller
	 * @param bankAccount the account that the account details scene will be generated for
	 */
	public void setBankAccount(BankAccount bankAccount) {
		myBankAccount = bankAccount;
		
	}
	
	/**
	 * Sets the list of bank accounts that the user has so that it is accessible from this controller
	 * @param bankAccounts the list containing the two bank accounts (the chequing account and savings account)
	 */
	public void setBankAccountsList(ArrayList<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	
	/**
	 * Creates and modifies the layout of the account details scene for a particular bank account which
	 * includes the account name, account number, and the current balance. If the account happens to be the 
	 * savings account then a message is displayed saying that the account must maintain $300 in order for
	 * any withdrawal from the account to occur
	 * @param accountDetailsScene the scene showing the account details for a particular bank account
	 */
	public void setScene(Scene accountDetailsScene) {
		myScene = accountDetailsScene;
		
		applicationStage.setTitle("Account Details");
		
		accountNameLabel.setText("Account: " + myBankAccount.getAccountName());
		
	    accountNumberLabel.setText("Account Number: " + myBankAccount.getAccountNumber());
	    
	    accountCurrentBalanceLabel.setText(String.format("Current Balance: $%.2f CAD", myBankAccount.getBalance()));
	   
	    if (myBankAccount instanceof SavingsAccount) {
	    	additionalAccountInfoMessage.setText("Note: " + myBankAccount.getAccountName() + " must maintain a minimum "
	    			+ "balance of $300 CAD in order for withdrawal from the account to occur");
	    }
	}
	
	/**
	 * Sets the BankingApplicationController as the next controller to switch to when the user decides to 
	 * return to the accounts summary
	 * @param next (the BankingApplicationController)
	 */
	public void setNextController(BankingApplicationController next) {
		nextController = next;
	}
	
	/**
	 * Takes the user to the account details scene from the accounts summary scene
	 */
	public void takeFocus() {
		applicationStage.setScene(myScene);
	}
	
	/**
	 * Updates and takes the user back to the accounts summary scene from any of the deposit, withdrawal,
	 * or transfer information scenes 
	 */
	private void returnToAccountDetailsScene() {
		applicationStage.setTitle("Account Details");
		
		accountNameLabel.setText("Account: " + myBankAccount.getAccountName());
		
	    accountNumberLabel.setText("Account Number: " + myBankAccount.getAccountNumber());
	    
	    accountCurrentBalanceLabel.setText(String.format("Current Balance: $%.2f CAD", myBankAccount.getBalance()));
	    
	    applicationStage.setScene(myScene);
	}
	
	/**
	 * Creates a new scene for the user to enter the amount of money they would like to deposit into their
	 * bank account of interest. 
	 * @param event when the user clicks on the "Deposit" button in the account details scene
	 */
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
		
		Label responseToUserInputMessage = new Label("");
		Button depositMoneyButton = new Button("Deposit money");
		depositMoneyButton.setOnAction(doneEvent -> depositMoneyButtonAction(enterAmountTextfield,
				responseToUserInputMessage));
		Button returnButton = new Button("Return to Account Details");
		returnButton.setOnAction(doneEvent -> returnToAccountDetailsScene());
		HBox buttonRow = new HBox();
		buttonRow.getChildren().addAll(depositMoneyButton,returnButton);
		HBox.setMargin(depositMoneyButton, new Insets(10,10,10,10));
		HBox.setMargin(returnButton, new Insets(10,10,10,10));
		
		allRows.getChildren().addAll(enterAmountPrompt,enterAmountRow,buttonRow,responseToUserInputMessage);
		VBox.setMargin(responseToUserInputMessage, new Insets(10,10,10,10));
		applicationStage.setScene(depositInfoScene);
	}
	
	/**
	 * Creates a new scene for the user to enter the amount of money they would like to withdraw from their
	 * bank account of interest
	 * @param event when the user clicks on the "Withdraw" button in the account details scene
	 */
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
		
		Label responseToUserInputMessage = new Label("");
		Button withdrawMoneyButton = new Button("Withdraw money");
		withdrawMoneyButton.setOnAction(doneEvent -> withdrawMoneyButtonAction(enterAmountTextfield,
				responseToUserInputMessage));
		Button returnButton = new Button("Return to Account Details");
		returnButton.setOnAction(doneEvent -> returnToAccountDetailsScene());
		HBox buttonRow = new HBox();
		buttonRow.getChildren().addAll(withdrawMoneyButton,returnButton);
		HBox.setMargin(withdrawMoneyButton, new Insets(10,10,10,10));
		HBox.setMargin(returnButton, new Insets(10,10,10,10));
		
		allRows.getChildren().addAll(enterAmountPrompt,enterAmountRow,buttonRow,responseToUserInputMessage);
		VBox.setMargin(responseToUserInputMessage, new Insets(10,10,10,10));
		applicationStage.setScene(withdrawalInfoScene);
	}
	
	/**
	 * Creates a new scene for the user to enter the amount of money they would like to transfer from their
	 * bank account of interest (the one that the button "Transfer" was clicked for in its account details
	 * scene) into the other bank account
	 * @param event when the user clicks on the "Transfer" button in the account details scene
	 */
	public void getTransferInformation(ActionEvent event) {
		BankAccount accountToTransferInto;
		VBox allRows = new VBox();
		Scene transferInfoScene = new Scene(allRows,600,600);
		if (myBankAccount instanceof ChequingAccount) accountToTransferInto = bankAccounts.get(1);
		else accountToTransferInto = bankAccounts.get(0);
		
		Label accountThatUserTransfersIntoLabel = new Label("Account that you are transferring into: "
				+ accountToTransferInto.getAccountName());
		Label enterAmountPrompt = new Label("Enter the amount that you would like to transfer from "
				+ myBankAccount.getAccountName());
		VBox.setMargin(enterAmountPrompt, new Insets(10,10,10,10));
		
		TextField enterAmountTextfield = new TextField();
		Label currencyLabel = new Label("CAD");
		HBox enterAmountRow = new HBox();
		enterAmountRow.getChildren().addAll(enterAmountTextfield,currencyLabel);
		HBox.setMargin(enterAmountTextfield, new Insets(0,5,10,10));
		HBox.setMargin(currencyLabel, new Insets(0,10,10,5));
		Label responseToUserInputMessage = new Label("");
		Button transferMoneyButton = new Button("Transfer money");
		transferMoneyButton.setOnAction(doneEvent -> transferMoneyButtonAction(accountToTransferInto,
				enterAmountTextfield,responseToUserInputMessage));
		Button returnButton = new Button("Return to Account Details");
		returnButton.setOnAction(doneEvent -> returnToAccountDetailsScene());
		
		HBox buttonRow = new HBox();
		buttonRow.getChildren().addAll(transferMoneyButton,returnButton);
		HBox.setMargin(transferMoneyButton, new Insets(10,10,10,10));
		HBox.setMargin(returnButton, new Insets(10,10,10,10));
		allRows.getChildren().addAll(accountThatUserTransfersIntoLabel,enterAmountPrompt,enterAmountRow,
				buttonRow,responseToUserInputMessage);
		VBox.setMargin(accountThatUserTransfersIntoLabel, new Insets(10,10,10,10));
		VBox.setMargin(responseToUserInputMessage, new Insets(10,10,10,10));
		applicationStage.setScene(transferInfoScene);
	}
	
	/**
	 * Checks to see if the user entered a reasonable deposit amount for their bank account. If the amount
	 * is considered valid, the deposit will occur and a confirmation message saying so will be displayed.
	 * If the amount entered by the user is not valid, the deposit will not have occurred and an error
	 * message will be displayed to the user
	 * @param enterAmountTextfield the text field that the user will have entered their deposit amount into
	 * @param responseToUserInputMessage the label that either displays a confirmation message for the 
	 * deposit transaction or an error message for invalid user input
	 */
	private void depositMoneyButtonAction(TextField enterAmountTextfield, Label responseToUserInputMessage) {
		String enteredAmount = enterAmountTextfield.getText();
		try {	
			boolean noErrors = myBankAccount.deposit(Double.parseDouble(enteredAmount));
			
			if (noErrors) {
				responseToUserInputMessage.setText(String.format("You have deposited $%.2f CAD into " + 
			myBankAccount.getAccountName(),Double.parseDouble(enteredAmount)));
			}
			
			else responseToUserInputMessage.setText("Error: $" + enteredAmount + " is not a valid amount.");
		}
		
		catch (NumberFormatException nfe) {
			responseToUserInputMessage.setText("Error: $" + enteredAmount + " is not a valid amount.");
		}
		
	}
	
	/**
	 * Checks to see if the user entered a reasonable withdrawal amount for their bank account. If the amount
	 * is considered valid, the withdrawal will occur and a confirmation message saying so will be displayed.
	 * If the amount entered by the user is not valid, the withdrawal will not have occurred and an error
	 * message will be displayed to the user
	 * @param enterAmountTextfield the text field that the user will have entered their withdrawal amount into
	 * @param responseToUserInputMessage the label that either displays a confirmation message for the 
	 * withdrawal transaction or an error message for invalid user input
	 */
	private void withdrawMoneyButtonAction(TextField enterAmountTextfield, Label responseToUserInputMessage) {
		String enteredAmount = enterAmountTextfield.getText();
		
		try {
			boolean noErrors = myBankAccount.withdraw(Double.parseDouble(enteredAmount));
			
			if (noErrors) {
				responseToUserInputMessage.setText(String.format("You have withdrawed $%.2f CAD from " + 
			myBankAccount.getAccountName(),Double.parseDouble(enteredAmount)));
			}
			
			else responseToUserInputMessage.setText("Error: $" + enteredAmount + " is not a valid amount.");
		}
		
		catch (NumberFormatException nfe) {
			responseToUserInputMessage.setText("Error: $" + enteredAmount + " is not a valid amount.");
		}
	}
	
	/**
	 * Checks to see if the user entered a reasonable transfer amount for their bank account. If the amount
	 * is considered valid, the transfer will occur from the bank account of interest to the other account
	 * and a confirmation message saying so will be displayed. If the amount entered by the user is not valid,
	 * the withdrawal will not have occurred and an error message will be displayed to the user
	 * @param accountToTransferInto the bank account that the user's entered amount will be transferred into
	 * @param enterAmountTextfield the text field that the user will have entered their transfer amount into
	 * @param responseToUserInputMessage the label that either displays a confirmation message for the 
	 * transfer transaction or an error message for invalid user input
	 */
	private void transferMoneyButtonAction(BankAccount accountToTransferInto, TextField enterAmountTextfield,
			Label responseToUserInputMessage) {
		String enteredAmount = enterAmountTextfield.getText();
		
		try {
			boolean noErrors = myBankAccount.transfer(Double.parseDouble(enteredAmount),accountToTransferInto);
			
			if (noErrors) {
				responseToUserInputMessage.setText(String.format("You have transfered $%.2f CAD from " + 
			myBankAccount.getAccountName() + " to " + accountToTransferInto.getAccountName(),
			Double.parseDouble(enteredAmount)));
			}
			
			else responseToUserInputMessage.setText("Error: $" + enteredAmount + " is not a valid amount.");
		}
		
		catch (NumberFormatException nfe) {
			responseToUserInputMessage.setText("Error: $" + enteredAmount + " is not a valid amount.");
		}
		
	}
	
	/**
	 * Takes the user back to the accounts summary scene that shows all the user's bank accounts
	 * @param event when the user clicks on the button "Return to Accounts Summary" in the account details
	 * scene
	 */
	@FXML
	private void getAccountsSummaryScene(ActionEvent event) {	
		if (nextController != null) {		
			nextController.takeFocus();	
    	}
	}

	
}

