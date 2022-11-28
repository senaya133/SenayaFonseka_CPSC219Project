package application;

public class BankAccount {
	// attributes/data
	private double amount;
	private String accountName;
	
	//actions
	BankAccount(String nameOfAccount) {
		accountName = nameOfAccount;
		amount = 0.00;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public double getAmount() {
		return amount;
	}

}
