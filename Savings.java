/*
 *  Author : Sithum Egodawatte
 *  Date :June 08, 2020
*   Description : Savings class
*   
*   
*     Method List:
*   	withdraw - Method used to withdraw an amount (double) of money. Method works if users balance is greater than withdrawal request (boolean returns true). Otherwise
*   			   return false.
*     
*   
*   Constructor(s):
*   	Savings() - Default constructor with default values for minimum balance and fee
*   
*   	Savings(Customer customer) - Overloaded constructor with customer object
*   
 */
public class Savings extends Account {
	private double fee;
	private double minBalance;

	// getter method for fee
	public double getFee() {
		return fee;
	}

	// setter method for fee
	public void setFee(double fee) {
		this.fee = fee;
	}

	// getter method for minBal
	public double getMinBalance() {
		return minBalance;
	}

	// setter method for minBal
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	// withdraw method
	public boolean withdraw(double amount) {
		boolean retVal = false;
		double accBal = 0.00;
		// checking if account balance is greater than withdrawal amount
		if (getAccountBalance() > amount) {
			retVal = true;
			// new balance
			accBal = getAccountBalance() - amount;
			// if new balance is less than minimum balance (4000.00)
			if (accBal < getMinBalance()) {
				// charge fee and set new balance
				accBal = accBal - getFee();
			}
			// setting new final balance
			setAccountBalance(accBal);
		}
		return retVal;
	}

	/*
	 * default constructor
	 */
	public Savings() {
		super();
		// setting values for fee and minBal
		this.fee = 4.25;
		this.minBalance = 4000.00;
	}

	/*
	 * overloaded constructor
	 */
	public Savings(Customer customer) {
		super(customer);
		this.fee = 4.25;
		this.minBalance = 4000.00;
	}

	// self-testing main method
	public static void main(String[] args) {
		// testing savings object
		Savings sav = new Savings();
		// generating new account number
		System.out.println("New Account Number : " + sav.generateAccountNumber());
		// testing setters for min bal and fee
		sav.setMinBalance(3500.00);
		sav.setFee(4.75);
		System.out.println(sav.toString());
		Customer cust = new Customer("David", "Best", "23 Apple Road", "416-235-5746");
		Savings sav2 = new Savings(cust);
		// printing customer details through savings object
		System.out.println("Customer First Name : " + sav2.getCustomer().getFirstName());
		System.out.println("Customer Last Name : " + sav2.getCustomer().getLastName());
		System.out.println("Customer Address : " + sav2.getCustomer().getAddress());
		System.out.println("Customer Phone # : " + sav2.getCustomer().getPhone());
		// testing deposit method and accountBalance method
		sav2.deposit(5000.00);
		sav2.withdraw(1000.00);
		System.out.println("New Balance : " + sav2.getAccountBalance());
		sav2.withdraw(1000.00);
		System.out.println("New Balance : " + sav2.getAccountBalance());
	}

	@Override
	public String toString() {
		// default toString method for testing
		return "Savings [fee=" + fee + ", minBalance=" + minBalance + "]";
	}

}
