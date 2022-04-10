/*
 *  Author : Sithum Egodawatte
 *  Date :June 08, 2020
*   Description : Account class to store customer info and account number and balance
*   
*   
*   Method List:
*   		generateAccountNumber - Method used to generate customer account number. Method returns a Long value (12 digits) randomized each time using Math.random().
*   
*   		deposit - Method used deposit an amount of money into account balance. Takes in an amount (double) and adds that amount current account balance.
*   
*   Constructor(s):
*   		Account() - Default constructor.
*   
*   		Account(Customer customer) - Overloaded constructor that passes Customer object 
*   
*   		Account(long accountNumber, double accountBalance) - Overloaded constructor with the account number and balance.
*   
*   		Account(Customer customer, long accountNumber, double accountBalance) - Overloaded constructor with customer object, account number and balance. 
*   
 */
public class Account {
	// private instance variables
	private long accountNumber;
	private double accountBalance;
	private Customer customer;

	// getter method for account balance
	public double getAccountBalance() {
		return accountBalance;
	}

	// setter method for account balance
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	// setter method for account number
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	// getter method for customer
	public Customer getCustomer() {
		return customer;
	}

	// setter method for customer
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	// generate account number method
	public long generateAccountNumber() {
		// initialize random function and define char array for 12 digits
		double accNum = Math.random();
		String strNum = String.valueOf(accNum);
		return Long.parseLong(strNum.substring(6));
	}

	// deposit method
	public void deposit(double amount) {
		if (amount > 0) {
			// adding deposited amount to current balance
			this.accountBalance = this.accountBalance + amount;
		}
	}

	/*
	 * default constructor
	 */
	public Account() {
		super();
	}

	/*
	 * overloaded constructor with customer
	 */
	public Account(Customer customer) {
		super();
		this.customer = customer;
	}

	/*
	 * overloaded constructor with account num and bal
	 */
	public Account(long accountNumber, double accountBalance) {
		super();
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}

	/*
	 * overloaded constructor with customer, account num and bal
	 */
	public Account(Customer customer, long accountNumber, double accountBalance) {
		super();
		this.customer = customer;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}
	
	// get the Account record by passing Customer object and account file
		public  String getAccRec(Customer cust , String fileName){
			String retVal="";
			// find customer record from Account Files
			String custName = cust.getFirstName()+" "+cust.getLastName();
			// get account record from filename 
			String accRec = Utility.getRecord(fileName, 2, custName); // params: fileName,FieldNumber,FieldValue

			if (accRec.equals("")){// No Entry Found
				retVal="";
			}else{ 
				// if file name is GIC return GIC record
				String[] rec = accRec.split("\\|");
				if (fileName.equals("GIC.txt")){
					
					// Format: loginID|firstName lastName|accNumber|accBalance|investDate|maturityDate|interestRate|PenaltyRate
					retVal = BankGUI.userID+"|"+custName+"|"+rec[2]+"|"+rec[3]+"|"+rec[4]+"|"+rec[5]+"|"+rec[6]+"|"+rec[7];
				
				// else return saving records
				}else if  (fileName.equals("SAVINGS.txt")){
					// Format: loginID|firstName lastName|accNumber|accBalance|minimalBal|tranFee
					retVal = BankGUI.userID+"|"+custName+"|"+rec[2]+"|"+rec[3]+"|"+rec[4]+"|"+rec[5];
				}else{
					retVal="";
				}

			}
			
			return retVal;
			
		}

	public static void main(String[] args) {
		// testing account number generator
		Account acc = new Account();
		System.out.println(acc.generateAccountNumber());
		// testing account with customer
		Customer cust = new Customer("David", "Best", "23 Apple Road", "416-235-5746");
		Account acc2 = new Account(cust, 123456789012l, 20.00);
		System.out.println(acc2.toString());
		// testing customer attributes through account object
		System.out.println("Customer First Name : " + acc2.getCustomer().getFirstName());
		// testing deposit method
		acc2.deposit(100.00);
		System.out.println(acc2.accountBalance);
		// testing account record method
		// 123|Sithum Egodawatte|6298377217236|123.0|2020-06-16|2021-06-16|3.0|20.0
		BankGUI.userID = "123";
		Customer cust2 = new Customer("Sithum", "Egodawatte", "23 Apple Road", "416-235-5746");
		Account acc3 = new Account();
		String accRec = acc3.getAccRec(cust2, "GIC.txt"); // testing with GIC file
		System.out.println(accRec.toString());
		String accRec2 = acc3.getAccRec(cust2, "SAVINGS.txt"); // testing with savings file
		System.out.println(accRec2.toString());
		// testing deposit method
		acc3.deposit(1000);
		System.out.println("Deposited Amount : " + acc3.getAccountBalance()); 

	}

	@Override
	public String toString() {
		// default toString method for testing
		return "Account [accountNumber=" + accountNumber + ", accountBalance=" + accountBalance + " " + customer + "]";
	}

}
