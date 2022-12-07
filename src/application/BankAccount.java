package application;

import javafx.scene.control.Label;

public class BankAccount {
	// attributes/data
	protected double balance;
	private String accountName;
	private String accountNumber;
	
	//actions
	BankAccount(String nameOfAccount,String accountNumber) {
		accountName = nameOfAccount;
		balance = 0.00;
		this.accountNumber = accountNumber;
	}
	
	public BankAccount(BankAccount accountToCopy) {
		this.balance = accountToCopy.balance;
		this.accountNumber = accountToCopy.accountNumber;
	}
	
	public String setAccountName(String nameOfAccount) {
		accountName = nameOfAccount;
		return accountName;
	}
	
	public String getAccountName() {
		return new String(accountName);
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public double getBalance() {
		BankAccount copyOfAccount = new BankAccount(this);
		return copyOfAccount.balance;
	}
	
	protected boolean sufficientFunds(double withdrawalAmount) {
		if (withdrawalAmount > 0 && (this.balance - withdrawalAmount) > 0) {
			return true;
		}
		else return false;
	}
	
	public void deposit(double depositAmount) {
		if (depositAmount > 0) {
			this.balance += depositAmount;
		}
		System.out.print(balance);
	}
	
	public Label withdraw(Label errorLabel, double withdrawalAmount) {
		if (withdrawalAmount > 0 && (this.balance - withdrawalAmount) >= 0.00) {
			this.balance -= withdrawalAmount;
			return errorLabel;
		}
		else {
			errorLabel.setText("Insufficent funds in account. Please enter a valid amount");
			return errorLabel;
		}
	}
	
	public void withdraw(double withdrawalAmount) {
		if (withdrawalAmount > 0 && (this.balance - withdrawalAmount) >= 0.00) {
			this.balance -= withdrawalAmount;
		}
	}
		
	public void transfer(double transferAmount, BankAccount accountToTransferMoneyInto) {
		if (transferAmount > 0 && this.balance - transferAmount >= 0.00) {
			withdraw(transferAmount);
			accountToTransferMoneyInto.deposit(transferAmount);
		}
	}
	
	public String accountLabel() {
		String balanceAsString = String.format("$%.2f CAD", this.balance);
		return (accountName + " (" + accountNumber + "): " + balanceAsString);

	}

}
