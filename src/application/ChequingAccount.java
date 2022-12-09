package application;

public class ChequingAccount extends BankAccount {
	
	private double minimumBalance;
	
	ChequingAccount(String nameOfAccount,String accountNumber) {
		super(nameOfAccount,accountNumber);
		minimumBalance = 0; // chequing account can be withdrawed from as long as the balance is at least $0
	}
	
	/**
	 * Gets the current balance for the chequing account 
	 * @return double that represents the current balance
	 */
	public double getBalance() {
		BankAccount copyOfAccount = new BankAccount(this);
		return copyOfAccount.balance;
	}
	
	/**
	 * Checks if the chequing account has enough money for a withdrawal of a certain amount to happen
	 * @param withdrawalAmount
	 * @return boolean that tells whether the chequing account does have sufficient funds or not
	 */
	protected boolean sufficientFunds(double withdrawalAmount) {
		if (withdrawalAmount > 0 && (this.balance - withdrawalAmount) >= minimumBalance) {
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
	 * Checks if the chequing account has enough money for a withdrawal of a certain amount to happen in which
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
	 * Checks if the chequing account has enough money for a transfer of a certain amount to happen from that
	 * chequing account in which case the transfer of that money will occur from the chequing account
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
	 * Creates a single string that displays the chequing account information for a specific account 
	 * (ie. balance, account name, account number)
	 * @return the string containing the information about the chequing account
	 */
	public String accountLabel() {
		String balanceAsString = String.format("$%.2f CAD", this.balance);
		return (this.getAccountName() + " (" + this.getAccountNumber() + "): " + balanceAsString);

	}
}
