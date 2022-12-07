package application;

public class ChequingAccount extends BankAccount {
	
	private double minimumBalance;
	
	ChequingAccount(String nameOfAccount,String accountNumber) {
		super(nameOfAccount,accountNumber);
		minimumBalance = 0;
	}
	
	public double getBalance() {
		BankAccount copyOfAccount = new BankAccount(this);
		return copyOfAccount.balance;
	}
	
	protected boolean sufficientFunds(double withdrawalAmount) {
		if (withdrawalAmount > 0 && (this.balance - withdrawalAmount) >= minimumBalance) {
			return true;
		}
		else return false;
	}
	
	public boolean deposit(double depositAmount) {
		if (depositAmount > 0) {
			this.balance += depositAmount;
			return true;
		}
		else return false;
	}
	
	public boolean withdraw(double withdrawalAmount) {
		if (sufficientFunds(withdrawalAmount)) {
			this.balance -= withdrawalAmount;
			return true;
		}
		else return false;
	}
		
	public boolean transfer(double transferAmount, BankAccount accountToTransferInto) {
		if (sufficientFunds(transferAmount)) {
			withdraw(transferAmount);
			accountToTransferInto.deposit(transferAmount);
			return true;
		}
		else return false;
	}
	
	public String accountLabel() {
		String balanceAsString = String.format("$%.2f CAD", this.balance);
		return (this.getAccountName() + " (" + this.getAccountNumber() + "): " + balanceAsString);

	}
}
