package application;

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
	
	public double getBalance() {
		BankAccount copyOfAccount = new BankAccount(this);
		return copyOfAccount.balance;
	}

}
