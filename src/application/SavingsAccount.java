package application;

public class SavingsAccount extends BankAccount {
	SavingsAccount(String nameOfAccount,String accountNumber) {
		super(nameOfAccount,accountNumber);
	}
	
	protected boolean sufficientFunds(double withdrawAmount) {
		if (withdrawAmount > 0 && (balance - withdrawAmount) > 0) {
			return true;
		}
		else return false;
	}
	
	
}
