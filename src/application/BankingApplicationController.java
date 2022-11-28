package application;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BankingApplicationController {
	
	Stage applicationStage;
	
	private Scene myScene;
	
	private BankingApplicationAccountDetailsController accountDetailsController;
	
	@FXML
    private TextField enterNameTextfield;
	
	@FXML
	private Label enterNameErrorLabel;

	private Stage primaryStage;
	
	/* the code from this method mainly came from the Nov25_Using Multiple FXML files presentation which is under content in 
	 * the CPSC 219 D2L shell */
	public void takeFocus() {
		applicationStage.setScene(myScene);
	}
	
	public ArrayList<BankAccount> createDefaultBankAccounts() {
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		double twoDigitNum1 = 10 +(Math.random()*99);
		double fiveDigitNum1 = 10000 + (Math.random()*99999);
		double twoDigitNum2 = 10 +(Math.random()*99);
		double fiveDigitNum2 = 10000 + (Math.random()*99999);
		String chequingAccountNumber = "12345-" + (int)twoDigitNum1 + "-" + (int)fiveDigitNum1;
        String savingsAccountNumber = "12345-" + (int)twoDigitNum2 + "-" + (int)fiveDigitNum2;
		ChequingAccount primaryChequingAccount = new ChequingAccount("Chequing Account",chequingAccountNumber);
    	SavingsAccount primarySavingsAccount = new SavingsAccount("Savings Account",savingsAccountNumber);
    	bankAccounts.add(primaryChequingAccount);
    	bankAccounts.add(primarySavingsAccount);
		return bankAccounts;
	}
	
	public double calculateTotalAmountFromAllBankAccounts(ArrayList<BankAccount> bankAccounts) {
		double totalAmount = 0.00;
		int index = 0;
		while (index < bankAccounts.size()) {
			totalAmount += ((BankAccount)bankAccounts.get(index)).getBalance();
			index++;
		}
		return totalAmount;
	}

    @FXML
    void showAccountsSummaryScene(ActionEvent event) {
    	VBox allRows = new VBox();
    	Scene accountsSummaryScene = new Scene(allRows,700,600);
        String nameEntered = enterNameTextfield.getText();
        if (nameEntered != "") {
        	Label welcomeLabel = new Label("Welcome " + nameEntered + "!");
        	VBox.setMargin(welcomeLabel, new Insets(10,5,10,5));
        	Label accountsSummaryTitle = new Label("Accounts Summary");
        	VBox.setMargin(accountsSummaryTitle, new Insets(10,5,10,5));
        	HBox bankAccountsHeader = new HBox();
        	Label bankAccountsTitle = new Label("Bank Accounts");
        	HBox.setMargin(bankAccountsTitle, new Insets(0,5,10,5));
        	ArrayList<BankAccount> bankAccounts = createDefaultBankAccounts();
        	double totalAmountFromAllBankAccounts = calculateTotalAmountFromAllBankAccounts(bankAccounts);
        	Label totalAmountLabel = new Label(String.format("Total:  $%.2f CAD", totalAmountFromAllBankAccounts));
        	HBox.setMargin(totalAmountLabel, new Insets(0,5,10,200));
        	bankAccountsHeader.getChildren().addAll(bankAccountsTitle,totalAmountLabel);
        	allRows.getChildren().addAll(welcomeLabel,accountsSummaryTitle,bankAccountsHeader);
            for (int index = 0;index < bankAccounts.size();index++) {
            	HBox bankAccountRow = new HBox();
            	Label bankAccountName = new Label((bankAccounts.get(index)).accountLabel());
            	HBox.setMargin(bankAccountName, new Insets(0,5,10,10));
            	// Label amountLabel = new Label(String.format("$%.2f CAD", (bankAccounts.get(index)).getBalance()));
            	Button viewAccountButton = new Button("View Account Details");
            	HBox.setMargin(viewAccountButton, new Insets (0,5,10,25));
            	BankAccount bankAccount = bankAccounts.get(index);
            	viewAccountButton.setOnAction(doneEvent -> getAccountDetailsScene(doneEvent,bankAccount));
            	bankAccountRow.getChildren().addAll(bankAccountName,viewAccountButton);
            	// HBox.setMargin(amountLabel, new Insets(0,5,10,210));
            	allRows.getChildren().add(bankAccountRow);
            }
        	
        	applicationStage.setScene(accountsSummaryScene);
        }
        else {
        	enterNameErrorLabel.setText("Error: You need not enter a name. Please enter your full name");
        }
    }
    
    /* in general, most of the code for this method was used from the presentation Nov25_Using Multiple FXML files under content 
     * in the CPSC 219 D2L shell, with certain variables being changed for this project */
    void getAccountDetailsScene(ActionEvent event,BankAccount bankAccount) {
    	if (accountDetailsController == null) {
    		try {
    			FXMLLoader loader = new FXMLLoader();
    			VBox root = loader.load(new FileInputStream("src/application/BankingApplicationAccountDetailsView.fxml"));
    			accountDetailsController = (BankingApplicationAccountDetailsController)loader.getController();
    			accountDetailsController.setApplicationStage(applicationStage);
    			accountDetailsController.setScene(new Scene(root,900,300));
    			accountDetailsController.setNextController(this);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	accountDetailsController.takeFocus();
    	
    }

}



