package application;

public class SavingsAccount extends BankAccount {
	SavingsAccount(String nameOfAccount,String accountNumber) {
		super(nameOfAccount,accountNumber);
	}
	
	protected boolean sufficientFunds(double withdrawalAmount) {
		if (withdrawalAmount > 0 && (balance - withdrawalAmount) > 0) {
			return true;
		}
		else return false;
	}
	
	
}
