package application;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BankingApplicationController {
	
	Stage applicationStage;
	
	@FXML
    private TextField enterNameTextfield;
	
	@FXML
	private Label enterNameErrorLabel;

	private Scene accountsSummaryScene;
	
	private ArrayList<BankAccount> bankAccounts;

	private BankingApplicationAccountDetailsController accountDetailsController;
	
	private Label totalAmountLabel;
	
	private Label bankAccountName;
	
	private ArrayList<HBox> bankAccountRows = new ArrayList<HBox>();;
	
	public void setApplicationStage(Stage aStage) {
		applicationStage = aStage;
	}
	
	/* the code from this method mainly came from the Nov25_Using Multiple FXML files presentation which is under content in 
	 * the CPSC 219 D2L shell */
	public void takeFocus() {
		applicationStage.setScene(accountsSummaryScene);
		applicationStage.setTitle("Accounts Summary");
		double totalAmountFromAllBankAccounts = calculateTotalAmountFromAllBankAccounts(bankAccounts);
		totalAmountLabel.setText(String.format("Total:  $%.2f CAD", totalAmountFromAllBankAccounts));
		int index = 0;
		while (index < bankAccounts.size()) {
			ObservableList<Node> nodesOfBankAccountRow = bankAccountRows.get(index).getChildren();
			Label bankAccountName = (Label) nodesOfBankAccountRow.get(0);
			bankAccountName.setText((bankAccounts.get(index)).accountLabel());
			index++;
		}
	}
	
	/* credit goes to the source from this site (https://mkyong.com/java/java-generate-random-integers-in-a-range/) which 
	 * showed a method that allows for a random number to be generated within a certain range (the min and max values in the range
	 * are inclusive)
	 */
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public ArrayList<BankAccount> createDefaultBankAccounts() {
		ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		String chequingAccountNumber = "12345-" + getRandomNumberInRange(10,99) + "-" + getRandomNumberInRange(10000,99999);
        String savingsAccountNumber = "12345-" + getRandomNumberInRange(10,99) + "-" + getRandomNumberInRange(10000,99999);
		ChequingAccount primaryChequingAccount = new ChequingAccount("Chequing Account",chequingAccountNumber);
    	SavingsAccount primarySavingsAccount = new SavingsAccount("Savings Account",savingsAccountNumber);
    	bankAccounts.add(primaryChequingAccount);
    	bankAccounts.add(primarySavingsAccount);
    	this.bankAccounts = bankAccounts;
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
    	accountsSummaryScene = new Scene(allRows,700,600);
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
        	totalAmountLabel = new Label(String.format("Total:  $%.2f CAD", totalAmountFromAllBankAccounts));
        	HBox.setMargin(totalAmountLabel, new Insets(0,5,10,200));
        	bankAccountsHeader.getChildren().addAll(bankAccountsTitle,totalAmountLabel);
        	allRows.getChildren().addAll(welcomeLabel,accountsSummaryTitle,bankAccountsHeader);
            for (int index = 0;index < bankAccounts.size();index++) {
            	HBox bankAccountRow = new HBox();
            	bankAccountName = new Label((bankAccounts.get(index)).accountLabel());
            	HBox.setMargin(bankAccountName, new Insets(0,5,10,10));
            	Button viewAccountButton = new Button("View Account Details");
            	HBox.setMargin(viewAccountButton, new Insets (0,5,10,25));
            	BankAccount bankAccount = bankAccounts.get(index);
            	viewAccountButton.setOnAction(doneEvent -> getAccountDetailsScene(doneEvent,bankAccount));
            	bankAccountRow.getChildren().addAll(bankAccountName,viewAccountButton);
                bankAccountRows.add(bankAccountRow);
            	allRows.getChildren().add(bankAccountRow);
            }
        	
        	applicationStage.setScene(accountsSummaryScene);
        }
        else {
        	enterNameErrorLabel.setText("Error: You did not enter a name. Please enter your full name");
        }
    }
    
    /* in general, most of the code for this method was used from the presentation Nov25_Using Multiple FXML files under content 
     * in the CPSC 219 D2L shell, with certain variables being changed for this project */
    void getAccountDetailsScene(ActionEvent event,BankAccount bankAccount) {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		VBox root = loader.load(new FileInputStream("src/application/BankingApplicationAccountDetailsView.fxml"));
    		accountDetailsController = (BankingApplicationAccountDetailsController)loader.getController();
    		accountDetailsController.setApplicationStage(applicationStage);
    		accountDetailsController.setBankAccount(bankAccount);
    		accountDetailsController.setBankAccountsList(bankAccounts);
    		accountDetailsController.setScene(new Scene(root,900,300));
    		accountDetailsController.setNextController(this);
    	} 
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	accountDetailsController.takeFocus();
    }

}



