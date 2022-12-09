package application;

public class BankAccount {
	// attributes/data
	protected double balance;
	private String accountName;
	private String accountNumber;
	
	//actions
	/**
	 * Constructor for bank account object that assigns an account name, an account number, and a balance of 
	 * $0 CAD
	 * @param nameOfAccount
	 * @param accountNumber
	 */
	BankAccount(String nameOfAccount,String accountNumber) {
		accountName = nameOfAccount;
		balance = 0.00;
		this.accountNumber = accountNumber;
	}
	
	/**
	 * Constructor that creates a copy of an existing bank account 
	 * @param accountToCopy the bank account that we want to make a copy of 
	 */
	public BankAccount(BankAccount accountToCopy) {
		this.balance = accountToCopy.balance;
		this.accountNumber = accountToCopy.accountNumber;
	}
	
	/**
	 * Sets the name of a bank account
	 * @param nameOfAccount the name of the account that we want to assign the bank account 
	 * @return the newly assigned name of the bank account
	 */
	public String setAccountName(String nameOfAccount) {
		accountName = nameOfAccount;
		return accountName;
	}
	
	/**
	 * Gets the name of the bank account
	 * @return the name of the bank account
	 */
	public String getAccountName() {
		return new String(accountName);
	}
	
	/**
	 * Gets the account number for the bank account
	 * @return the account number in the form of xxxx-yy-yyyyy
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	
	/**
	 * Gets the current balance for the bank account 
	 * @return double that represents the current balance
	 */
	public double getBalance() {
		BankAccount copyOfAccount = new BankAccount(this);
		return copyOfAccount.balance;
	}
	
	/**
	 * Checks if the bank account has enough money for a withdrawal of a certain amount to happen
	 * @param withdrawalAmount
	 * @return boolean that tells whether the bank account does have sufficient funds or not
	 */
	protected boolean sufficientFunds(double withdrawalAmount) {
		if (withdrawalAmount > 0 && (this.balance - withdrawalAmount) >= 0) {
			return true;
		}
		
		else return false;
	}
	
	/**
	 * Checks if the deposit amount entered by the user is a positive value in which case the deposit will
	 * occur
	 * @param depositAmount
	 * @return boolean that tells whether the deposit was successful
	 */
	public boolean deposit(double depositAmount) {
		if (depositAmount > 0) {
			this.balance += depositAmount;
			return true;
		}
		
		else return false;
	}
	
	/**
	 * Checks if the bank account has enough money for a withdrawal of a certain amount to happen in which
	 * case the withdrawal will occur
	 * @param withdrawalAmount
	 * @return boolean that tells whether the withdrawal was successful or not
	 */
	public boolean withdraw(double withdrawalAmount) {	
		if (sufficientFunds(withdrawalAmount)) {
			this.balance -= withdrawalAmount;
			return true;
		}
		
		else return false;
	}
	
	/**
	 * Checks if the bank account has enough money for a transfer of a certain amount to happen from that
	 * bank account in which case the transfer of that money will occur from the bank account
	 * @param transferAmount
	 * @return boolean that tells whether the transfer was successful or not
	 */
	public boolean transfer(double transferAmount, BankAccount accountToTransferInto) {
		if (sufficientFunds(transferAmount)) {
			withdraw(transferAmount);
			accountToTransferInto.deposit(transferAmount);
			return true;
		}
		
		else return false;
	}
	
	/**
	 * Creates a single string that displays the account information for a specific account (ie. balance, 
	 * account name, account number)
	 * @return the string containing the information about the bank account
	 */
	public String accountLabel() {
		String balanceAsString = String.format("$%.2f CAD", this.balance);
		return (accountName + " (" + accountNumber + "): " + balanceAsString);
	}

}
