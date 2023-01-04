package practice;

// Class to store and display Transactions

public class Transactions{
	
	public String purchasedBy;
	public String purchaseDesc;
	public double purchaseAmount;
	public double balance;
	public String purchaseTime;
	
	void viewDetails(Transactions trans) {
		
			
			System.out.println("\t" + trans.purchasedBy +" " + trans.purchaseDesc + " $ " +
					trans.purchaseAmount + "  resulting in wallet balance of $ " +
					trans.balance + " at " +
					trans.purchaseTime);
			System.out.println("----------------------------------------------------------------------------------------");
			
					
	}
}
