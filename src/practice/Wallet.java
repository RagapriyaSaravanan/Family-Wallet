package practice;

// Class for Wallet and functionalities

public class Wallet {
	
	public double balance = 00.00;
		
	public double getBalance() {
		return balance;
	}
	
	public void deposit (double amount) {
		balance = balance + amount;
	}
	
	public void withdraw (double amount) {
		balance = balance - amount;
	}
}
