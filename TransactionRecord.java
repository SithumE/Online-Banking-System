/*
 *  Author : Sithum Egodawatte
 *  Date :June 16, 2020
*   Description : Transaction record class
*   
*   
*   Method List:
*   	depositAcc - Method used to change values when depositing an amount. Takes in an amount (double) for depositing.
*   
*   	withdrawAcc - Method used to change values when withdrawing an amount. Takes in an amount (double) for withdrawing.
*   
*   	stringToObj - Method used to convert string into object so it can be used in file and inside method, creates default TransactionRecord object.
*   
*   
*   Constructor(s):
*   	TransactionRecord() - Default constructor
*   
*   	TransactionRecord(char accountType, String transactionType, double transactionAmt, double startBal, double endBal) - Overloaded constructor
*   							used to set values.
*   
 */
public class TransactionRecord {
	// private instance variables for class
	private char accountType;
	private String transactionType;
	private double transactionAmt;
	private double startBal;
	private double endBal;

	// getter for account type
	public char getAccountType() {
		return accountType;
	}

	// setter for account type
	public void setAccountType(char accountType) {
		this.accountType = accountType;
	}

	// getter for transaction type
	public String getTransactionType() {
		return transactionType;
	}

	// setter for transaction type
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	// getter for transaction amount
	public double getTransactionAmt() {
		return transactionAmt;
	}

	// setter for transaction amount
	public void setTransactionAmt(double transactionAmt) {
		this.transactionAmt = transactionAmt;
	}

	// getter for start balance
	public double getStartBal() {
		return startBal;
	}

	// setter for start balance
	public void setStartBal(double startBal) {
		this.startBal = startBal;
	}

	// getter for end balance
	public double getEndBal() {
		return endBal;
	}

	// setter for end balance
	public void setEndBal(double endBal) {
		this.endBal = endBal;
	}

	// method to deposit
	public void depositAcc(double amount) {
		// changing values when depositing amount
		setTransactionType("Deposit");
		setTransactionAmt(amount);
		setStartBal(this.endBal);
		setEndBal(this.endBal + amount);
	}

	// method to withdraw
	public boolean withdrawAcc(double amount) {
		boolean retVal = false;
		setTransactionType("Withdraw");

		// if withdraw amount is valid
		if (amount <= getEndBal()) {
			// change values when withdrawing
			setTransactionAmt(amount);
			setStartBal(this.endBal);
			setEndBal(this.endBal - amount);
			retVal = true;
		} else {
			System.out.println("Insufficient funds");
		}
		return retVal;
	}
	
	// convert String to TransactionRecord object
	public TransactionRecord stringToObj(String strLine) {

		// Create a TransactionRecord object using a String
		// Format : accType|tranType|tranAmt|startBal|endBal
		String strArr[] = strLine.split("\\|");
		char accountType = strArr[0].charAt(0);
		String tranType = strArr[1];
		double tranAmt = Double.parseDouble(strArr[2]);
		double startBal = Double.parseDouble(strArr[3]);
		double endBal = Double.parseDouble(strArr[4]);

		// set transaction record object and return it
		TransactionRecord tr = new TransactionRecord(accountType, tranType, tranAmt, startBal, endBal);
		return tr;

	}

	/*
	 * default constructor
	 */
	public TransactionRecord() {
		super();
	}


	/*
	 * overloaded constructor
	 */
	public TransactionRecord(char accountType, String transactionType, double transactionAmt, double startBal,
			double endBal) {
		super();
		this.accountType = accountType;
		this.transactionType = transactionType;
		this.transactionAmt = transactionAmt;
		this.startBal = startBal;
		this.endBal = endBal;
	}

	public static void main(String[] args) {
		// testing transaction record class
		TransactionRecord tr = new TransactionRecord(); // class object
		// setting transaction fields
		tr.setAccountType('s');
		tr.setTransactionType("deposit");
		tr.setTransactionAmt(1000.00);
		tr.setStartBal(0);
		tr.setEndBal(1000);

		System.out.println("Account Type : " + tr.getAccountType());
		System.out.println("Transaction Type : " + tr.getTransactionType());
		System.out.println("Transaction Amount : " + tr.getTransactionAmt());
		System.out.println("Start Balance : " + tr.getStartBal());
		System.out.println("End Balance : " + tr.getEndBal());

		// testing overloaded constructor & toString method
		TransactionRecord tr2 = new TransactionRecord('g', "withdraw", 1000.00, 4000.00, 3000.00);
		System.out.println(tr2.toString());
		
		// testing depositAcc method
		tr.depositAcc(1000.0);
		System.out.println("End Balance After Deposit : " + tr.getEndBal());
		
		// testing withdrawAcc method
		tr.withdrawAcc(1000.0);
		System.out.println("End Balance After Withdraw : " + tr.getEndBal());
		
		// testing stringToObj method
		// accType|tranType|tranAmt|startBal|endBal
		String strLine = "g|deposit|1000|0|1000";
		TransactionRecord tr3 = new TransactionRecord();
		TransactionRecord tr4 = tr3.stringToObj(strLine);
		System.out.println(tr4.toString());
		
	}

	@Override
	public String toString() {
		return accountType + "|" + transactionType + "|" + transactionAmt + "|" + startBal + "|" + endBal;
	}

}
