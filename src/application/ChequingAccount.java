package application;

public class ChequingAccount extends BankAccount {
	ChequingAccount(String nameOfAccount,String accountNumber) {
		super(nameOfAccount,accountNumber);
	}
	
	protected boolean sufficientFunds(double withdrawalAmount) {
		if (withdrawalAmount > 0 && (balance - withdrawalAmount) > 0) {
			return true;
		}
		else return false;
	}
}
