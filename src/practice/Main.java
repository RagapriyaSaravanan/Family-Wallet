package practice;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Objects and variables declaration
		
		Wallet wallet = new Wallet();
		Father dad = new Father();
		Mother mom = new Mother();
		
		Children[] child = new Children[10];
		for (int i=0; i < 10; i++) {
			child[i] = new Children();
		}
		
		ArrayList<String> notifications = new ArrayList<String>();
		ArrayList<Transactions> transactions =  new ArrayList<Transactions>();
		int menu;
		double amount;
		
		//Login
		
		try (Scanner input = new Scanner(System.in)) {
			
						
			String username, password;
			
			while(true) {			
				System.out.println("Please login");
			
				System.out.println("username:");
				username = input.nextLine();
				
				if(!username.toLowerCase().contains("child")) {
								
					if(username.equals("Dad")) {
						
						System.out.println("password:");
						password = input.nextLine();
						
							if(password.equals(dad.getPassword())) {
							
								System.out.println("You are successfully logged in!!!\n \n");
								
								// MENU for Actor - Dad
							
								do {
								
									System.out.println("\t MENU:");
									System.out.println("\t 1. Check Balance");
									System.out.println("\t 2. Deposit money");
									System.out.println("\t 3. Withdraw money");
									System.out.println("\t 4. Pay");
									System.out.println("\t 5. View Transaction history");
									System.out.println("\t 6. View Requests");
									System.out.println("\t 7. View Notifications");
									System.out.println("\t 8. Block family member");
									System.out.println("\t 9. Exit");
									System.out.println("\t Please select an option");
								
									menu = Integer.parseInt(input.nextLine());
									
									if(menu == 1) {
										
										// Check Balance - Dad
										
										System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
									
									} else if(menu == 2) {
										
										// Deposit functionality - Dad
										
										System.out.println("Please enter amount to deposit:");
										amount = Double.parseDouble(input.nextLine());
										wallet.deposit(amount);
										System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
										
										// Store in Transactions
										
										Transactions trans = new Transactions();
										trans.purchasedBy = "Dad";
										trans.purchaseDesc = "has deposited";
										trans.purchaseAmount = amount;
										trans.balance = wallet.getBalance();
										LocalDateTime localDate = LocalDateTime.now();
										trans.purchaseTime = String.valueOf(localDate);
										transactions.add(trans);
										
										// Notifications
																				
										if (wallet.getBalance() < 100.0) {
											
											notifications.add("Balance is less than $100.00");
											System.out.println("Warning! Balance is less than $100.00");
										}
									
									} else if(menu == 3) {
										
										// Withdraw functionality - Dad
										
										System.out.println("Please enter amount to withdraw:");
										amount = Double.parseDouble(input.nextLine());
													
										if (wallet.getBalance() - amount >= 0) { // Check to see if balance is sufficient
											
											// Store in Transactions
											
											Transactions trans = new Transactions();
											trans.purchasedBy = "Dad";
											trans.purchaseDesc = "has withdrawed";
											trans.balance = wallet.getBalance();
											LocalDateTime localDate = LocalDateTime.now();
											trans.purchaseTime = String.valueOf(localDate);
											
											wallet.withdraw(amount);
											
											// Notifications
											
											if (wallet.getBalance() < 100.0) {
												
												notifications.add("Balance is less than $100.00");
												System.out.println("Warning! Balance is less than $100.00");
											}
											
											System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
										
										} else System.out.println("Withdraw unsuccessful!! You entered an amount greater than your balance");
																			
									} else if(menu == 4) {
										
										// Payment functionality - Dad
										
										System.out.println("To Pay, enter details of transaction: ");
										Transactions trans = new Transactions();
										
										System.out.println("Enter purchased shop name: \n");
										trans.purchaseDesc = "has purchased at " + input.nextLine();
										System.out.println("Enter purchased amount: \n");
										trans.purchaseAmount = Double.parseDouble(input.nextLine());
										
										LocalDateTime localDate = LocalDateTime.now();
										trans.purchaseTime = String.valueOf(localDate);
										
										if((wallet.getBalance() - trans.purchaseAmount) >= 0) { // Check to see if balance is sufficient
											
											// Store in Transactions
											
											trans.purchasedBy = "Dad";
											wallet.withdraw(trans.purchaseAmount);
											trans.balance = wallet.getBalance();
											transactions.add(trans);
											
											// Notifications
											
											if (wallet.getBalance() < 100.0) {
												
												notifications.add("Balance is less than $100.00");
												System.out.println("Warning! Balance is less than $100.00");
											}
											
											System.out.println("Payment successful!! The wallet balance is: $" + wallet.getBalance());
																						
										} else System.out.println("\nPayment unsuccessful!! Insufficient funds\n \n");
										
									} else if (menu == 5) {
										
										// Printing Transaction Records - Dad
										
										if (transactions.size() == 0) {
											
											System.out.println("\n No transaction records found \n");
										
										} else {
											
																						
											System.out.println("\n Transaction Records: \n ");
											System.out.println("--------------------------------------------------------------------------------------------");
																						
											for (int i = transactions.size()-1; i >= 0; i-- ) {
												
												Transactions trans = transactions.get(i);
												trans.viewDetails(trans);												
											}
											System.out.println("\n\n");
										}
										
									} else if (menu == 6) {
										
										// Requests functionality - Dad
										
										if(dad.requests.size() == 0) {
											
											System.out.println("\n No pending requests \n");
											
										} else {
											
																						
											System.out.println("Your requests are : \n \n");											
											
											
											for ( int i = 0; i < dad.requests.size(); i++ ) {
												
												int key = (int) dad.requests.keySet().toArray()[i];
																								
												System.out.println("\t Child " + key + " has requested " + dad.requests.get(key));
												
												System.out.println("\t Do you accept or decline the request (Y/N) :"); // Request decision
												String decision = input.nextLine();
																						
												if (decision.equals("Y")) {
																			
													if(dad.requests.get(key).toLowerCase().contains("overpay")) { // Over pay Requests
														
														System.out.println("\n You have accepted the overpay request");													
														System.out.println("Enter amount for overpay request:");
														double amountOverpay = Double.parseDouble(input.nextLine());
														child[key-1].setLimitPerDay(amountOverpay);
														child[key-1].setFlagRequest(false);
																											
													} else if (dad.requests.get(key).toLowerCase().contains("usage")) { // Extra usage Requests
														
														System.out.println("You have accepted the extra usage request");
														child[key-1].setNumberOfUses(1);
														child[key-1].setFlagRequest(false);
													 
													} else if (dad.requests.get(key).toLowerCase().contains("deposit")) { // Deposit Requests
														
														System.out.println("You have accepted the deposit request");
														System.out.println("Please enter amount to deposit:");
														amount = Double.parseDouble(input.nextLine());
														wallet.deposit(amount);
														System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
														child[key-1].setFlagRequest(false);
														
													} 
																								
												} else continue;
																						
											}	
											
											System.out.println("\nYou have read all your requests. \n \n");
										
										 }
										
										dad.requests.clear();
										
									} else if (menu == 7) {
										
										// View Notifications - Dad
										
										if ( notifications.size() == 0) {
											
											System.out.println("\n No notifications \n");
											
										} else {
											
											System.out.println("Your notifications are : \n \n");
											
											for ( int i = 0; i < notifications.size(); i++) {
												System.out.println( (i+1) + ". " + notifications.get(i));
											}	
											System.out.println("\n\n");
											notifications.clear();
										}
																			
									} else if (menu == 8) {
										
										// Block user functionality - Dad
										
										System.out.println("Please select who you want to block");
										System.out.println(" 1. Mom \n 2. Child 1 \n 3. Child 2 \n 4. Child 3 \n 5. Child 4 \n 6. Child 5 "
												+ "\n 7. Child 6 \n 8. Child 7 \n 9. Child 8 \n 10. Child 9 \n 11. Child 10 \n");
										
										int menuBlock = Integer.parseInt(input.nextLine());
										
										if (menuBlock == 1) mom.setBlockStatus(true);
										else child[menuBlock-2].setBlockStatus(true);
										
										System.out.println("Block successful!! \n \n");
										
									}
								} while (menu < 9);
							
							} else {
								System.out.println("\nWrong password\n");
							}
							
					} else if (username.equals("Mom")) {
						
						System.out.println("password:");
						password = input.nextLine();
							
						if(password.equals(mom.getPassword()) && !mom.isBlockStatus()) {
							
							System.out.println("\nYou are successfully logged in!!!\n \n");
							
							// MENU for Actor - Mom
						
							do {
							
								System.out.println("\t MENU:");
								System.out.println("\t 1. Check Balance");
								System.out.println("\t 2. Deposit money");
								System.out.println("\t 3. Withdraw money");
								System.out.println("\t 4. Pay");
								System.out.println("\t 5. View Transaction history");
								System.out.println("\t 6. View Requests");
								System.out.println("\t 7. View Notifications");
								System.out.println("\t 8. Exit");
								System.out.println("\t Please select an option");
							
								menu = Integer.parseInt(input.nextLine());
								
								if(menu == 1) {
									
									// Check Balance - Mom
									
									System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
								
								} else if(menu == 2) {
									
									// Deposit functionality - Mom
									
									System.out.println("\n Please enter amount to deposit:");
									amount = Double.parseDouble(input.nextLine());
									wallet.deposit(amount);
									System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
									
									// Store in Transactions
									
									Transactions trans = new Transactions();
									trans.purchasedBy = "Mom";
									trans.purchaseDesc = "has deposited";
									trans.purchaseAmount = amount;
									trans.balance = wallet.getBalance();
									LocalDateTime localDate = LocalDateTime.now();
									trans.purchaseTime = String.valueOf(localDate);
									transactions.add(trans);
									
									// Notifications
									
									if (wallet.getBalance() < 100.0) {
										
										notifications.add("Balance is less than $100.00");
										System.out.println("Warning! Balance is less than $100.00");
									}
								
								} else if(menu == 3) {
									
									// Withdraw functionality - Mom
									
									System.out.println("\n Please enter amount to withdraw:");
									amount = Double.parseDouble(input.nextLine());
									
									if (amount <= wallet.getBalance()) { // Check to see if balance is sufficient
										
										wallet.withdraw(amount);
										
										// Add to Transactions
										
										Transactions trans = new Transactions();
										trans.purchasedBy = "Mom";
										trans.purchaseDesc = "has withdrawed";
										trans.purchaseAmount = amount;
										trans.balance = wallet.getBalance();
										LocalDateTime localDate = LocalDateTime.now();
										trans.purchaseTime = String.valueOf(localDate);
										transactions.add(trans);
										
										// Notifications
										
										if (wallet.getBalance() < 100.0) {
											
											notifications.add("Balance is less than $100.00");
											System.out.println("Warning! Balance is less than $100.00");
										}
										
										System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
										
									} else System.out.println("Withdraw unsuccessful!! You entered an amount greater than your balance");
								
								} else if(menu == 4) {
									
									// Payment functionality - Mom
									
									System.out.println("To Pay, enter details of transaction:");
									
									Transactions trans = new Transactions();
									System.out.println("Enter purchased shop name: \n");
									trans.purchaseDesc = "has purchased at " + input.nextLine();
									System.out.println("Enter purchased amount: \n");
									trans.purchaseAmount = Double.parseDouble(input.nextLine());
									LocalDateTime localDate = LocalDateTime.now();
									trans.purchaseTime = String.valueOf(localDate);
									
									if(wallet.getBalance() - trans.purchaseAmount > 0) { // Check to see if balance is sufficient

										// Add to Transactions
										
										trans.purchasedBy = "Mom";
										wallet.withdraw(trans.purchaseAmount);
										trans.balance = wallet.getBalance();
										transactions.add(trans);
										
										// Notifications
										
										if (wallet.getBalance() < 100.0) {
											
											notifications.add("Balance is less than $100.00");
											System.out.println("Warning! Balance is less than $100.00");
										}
										
										System.out.println("\nPayment successful!! The wallet balance is: $" + wallet.getBalance() + "\n \n");
										
									} else System.out.println("\nPayment unsuccessful!! Insufficient funds \n \n");
									
								} else if (menu == 5) {
									
									// View Transactions - Mom
									
									if (transactions.size() == 0) {
										
										System.out.println("\n No transaction records found \n");
									
									} else {
										
										System.out.println("\n Transaction Records: \n ");
										System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
										
										for (int i = transactions.size()-1; i >= 0; i-- ) {
											
											Transactions trans = transactions.get(i);
											trans.viewDetails(trans);												
										}
										System.out.println("\n \n");
									}
								} else if (menu == 6) {
									
									// View Requests - Mom
									
									if (mom.requests.size() == 0) {
										
										System.out.println("No pending requests");
										
									} else {
										
										System.out.println("Your requests are : \n \n");
																													
										for ( int i = 0; i < mom.requests.size(); i++ ) {
											int key = (int) mom.requests.keySet().toArray()[i];
																			
											System.out.println("\t Child " + key + " has requested " + mom.requests.get(key));
											
											System.out.println("\t Do you want to:");
											System.out.println("\t \t 1. Decide on the request");
											System.out.println("\t \t 2. Transfer the request to Dad");
											
											int menuRequest = Integer.parseInt(input.nextLine());
											
											if(menuRequest == 1) {
											
												System.out.println("Do you accept or decline the request (Y/N) :");
												String decision = input.nextLine();
												
												if (decision.equals("Y")) {
													
													if(mom.requests.get(key).toLowerCase().contains("overpay")) { // Over pay request
														
														System.out.println("You have accepted the overpay request");													
														System.out.println("Enter amount for overpay request:");
														amount = Double.parseDouble(input.nextLine());
														child[key-1].setLimitPerDay(amount);
														child[key-1].setFlagRequest(false);
																											
													} else if (mom.requests.get(key).toLowerCase().contains("usage")) { // extra usage request
														
														System.out.println("You have accepted the extra usage request");
														child[key-1].setNumberOfUses(1);
														child[key-1].setFlagRequest(false);
													
													} else if (mom.requests.get(key).toLowerCase().contains("deposit")) { // deposit request
														
														System.out.println("You have accepted the deposit request");
														System.out.println("Please enter amount to deposit:");
														amount = Double.parseDouble(input.nextLine());
														wallet.deposit(amount);
														System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
														child[key-1].setFlagRequest(false);
														
													} 
											
												} else continue;
												
											} else {
												
												// Transfer request to Dad
												
												dad.addRequest(key, mom.requests.get(key));
												System.out.println("\nRequest transfered successfully!!!\n");
											}
											
											System.out.println("\n");										
										}	
									    
										
										System.out.println("\nYou have read all your requests. \n");
																												
									}
									
									mom.requests.clear();
									
								} else if (menu == 7) {
									
									// Notifications - Mom
									
									if(notifications.size() == 0) {
										
										System.out.println("\n No notifications \n");
									}
									
									else {
										
										System.out.println("Your notifications are : \n \n");
										
										for ( int i = 0; i < notifications.size(); i++) {
											System.out.println( (i+1) + ". " + notifications.get(i));
										}	
										notifications.clear();
										
									}
								
								}
								
							} while (menu < 8);
						
						} else {
							
							if(mom.isBlockStatus())	System.out.println("\n You have been blocked!!\n");
							else System.out.println("\n Wrong password \n");
						}
												
					}
				
				} else if(username.toLowerCase().contains("child")){
					
					System.out.println("Enter your ID:");
					int ID = Integer.parseInt(input.nextLine());
					
					if (!(child[ID-1].isBlockStatus()) && (ID <= 10 && ID >= 1)) { // Check for block and only 10 children are allowed
						
						System.out.println("\nYou are successfully logged in!!!\n \n");
						
						// Only one request can be entered at a time. The child has to wait until Mom / Dad has 
						//decided on their request to place another request since hashmap is used.
						
						// MENU for Actor - Child
						
						do {
						
							System.out.println("\t MENU:");
							System.out.println("\t 1. Check Balance");
							System.out.println("\t 2. Check Limits");
							System.out.println("\t 3. Pay");
							System.out.println("\t 4. Deposit Request");
							System.out.println("\t 5. Extra usage Request");
							System.out.println("\t 6. Overpay Request");
							System.out.println("\t 7. Exit");
							System.out.println("\t Please select an option");
													
							menu = Integer.parseInt(input.nextLine());
							
														
							if(menu == 1) {
								
								// Check balance - child
								
								System.out.println("The wallet balance is: $" + wallet.getBalance() + "\n \n");
							
							} else if (menu == 2) {
								
								// Check limits - child
								// To view their remaining limits or to see the decision of their requests
								
								System.out.println("Your limits are : ");
								System.out.println(" Usage Amount Limit - $ " + child[ID-1].getLimitPerDay());
								System.out.println(" Number of uses permitted -  " + child[ID-1].getNumberOfUses());
								System.out.println("\n");
								
							} else if (menu == 3) {
								
								// Payment functionality - child
								// Check for limits
								
								if (child[ID-1].getLimitPerDay() > 0) {
									
									if (child[ID-1].getNumberOfUses() == 1) {
																												
												System.out.println("To Pay, enter details of transaction:");
												Transactions trans = new Transactions();
												
												// Details of transactions entered
												
												System.out.println("Enter purchased shop name: \n");
												trans.purchaseDesc = "has purchased at " + input.nextLine();
												System.out.println("Enter purchased amount: \n");
												trans.purchaseAmount = Double.parseDouble(input.nextLine());
												LocalDateTime localDate = LocalDateTime.now();
												trans.purchaseTime = String.valueOf(localDate);
																																				
												if ((child[ID-1].getLimitPerDay() - trans.purchaseAmount) > 0 && (wallet.getBalance() - trans.purchaseAmount > 0)) { // Check for limit and if balance is sufficient
													
													// Add to transactions
													
													trans.purchasedBy = "Child ";
													trans.purchasedBy  += String.valueOf(ID);
													wallet.withdraw(trans.purchaseAmount);
													trans.balance = wallet.getBalance();
													transactions.add(trans);
													
													// Notifications
													
													if (wallet.getBalance() < 100.0) {
														
														notifications.add("Balance is less than $100.00");
														System.out.println("Warning! Balance is less than $100.00");
													}
													
													// Modify limits
													
													child[ID-1].limitPerDay -= trans.purchaseAmount;
													child[ID-1].numberOfUses--;
													
													System.out.println("Payment successful!! The wallet balance is: $" + wallet.getBalance() + "\n \n");
																																											
												} else System.out.println("\nPayment unsuccessful!!Insufficient funds \n \n");
																
									} else System.out.println("You have used the wallet once already today. \n To use it again, please request extra usage from Mom \n \n");
																	
								} else System.out.println("You have used up your daily limit. \n To use the wallet, please request overpay from Mom \n \n");
																		
							 } else if (menu == 4) {
								 
								 // Deposit request
								 
								 if(!child[ID-1].isFlagRequest()) { // Only one request at a time check
								 
									 System.out.println("Enter amount to request for deposit");
									 amount = Double.parseDouble(input.nextLine());
									 								 								 
									 String request = "deposit of amount $";
									 request += String.valueOf(amount);
									 mom.addRequest(ID, request);
									 System.out.println("Your request has been submitted!! \n \n");
									
									 child[ID-1].setFlagRequest(true);
								 
								 } else System.out.println("You can place only one request at a time");
								 
							 } else if (menu == 5) {
								 
								 // Extra usage request
								 
								 if(!child[ID-1].isFlagRequest()) {
									 
									 String request = "extra usage";
									 mom.addRequest(ID, request);
									 System.out.println("Your request has been submitted!! \n \n");
									 
									 child[ID-1].setFlagRequest(true);
									 
								 } else System.out.println("You can place only one request at a time");								 
								 
							 } else if (menu == 6) {
								 
								 // Over pay request
								 
								 if(!child[ID-1].isFlagRequest()) {
									
									 System.out.println("Enter amount to request for overpay");
									 amount = Double.parseDouble(input.nextLine());							 
									 String request = "overpay of amount $";
									 request += String.valueOf(amount);
									 mom.addRequest(ID, request);	
									 System.out.println("Your request has been submitted!! \n \n");
									 child[ID-1].setFlagRequest(true);									 
								 
								 } else System.out.println("You can place only one request at a time");	
								 								 
							 }
						
									
						} while(menu < 7);
						
					} else System.out.println("\nYou have been blocked!!\n");   
					
				} 
			}
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	
	     
	}

}
