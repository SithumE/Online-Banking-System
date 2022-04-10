import java.text.SimpleDateFormat;
import java.util.Date;

/*
 *  Author : Sithum Egodawatte
 *  Date :June 09, 2020
*   Description : GIC class which inherits the Account class (a child of Account class).
*   
*   
*   Method List:
*   	withdraw - Method used to withdraw an amount (double) of money. If the withdraw date is before the maturity date, method adds a penalty fee. Method passes a boolean and
*   			   returns true if the penalty fee + amount is less than the customers account balance.
*   
*   
*   Constructor(s):
*   	GIC() - Default constructor
*   
*   	GIC(Customer customer) - Overloaded constructor with customer object
*   
 */
public class GIC extends Account {
	// private instance variables
	private Date investmentDate;
	private Date maturityDate;
	private double interestRate;
	private double penaltyRate;
	private double penaltyAmount;

	// getter for investment date
	public Date getInvestmentDate() {
		return investmentDate;
	}

	// setter for investment date
	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate;
	}

	// getter for maturity date
	public Date getMaturityDate() {
		return maturityDate;
	}

	// setter for maturity date
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

	// getter for interest rate
	public double getInterestRate() {
		return interestRate;
	}

	// setter for interest rate
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	// getter for penalty rate
	public double getPenaltyRate() {
		return penaltyRate;
	}

	// setter for penalty rate
	public void setPenaltyRate(double penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	// getter for penalty ammount
	public double getPenaltyAmount() {
		return penaltyAmount;
	}

	// setter for penalty amount
	public void setPenaltyAmount(double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	// withdraw method
	public boolean withdraw(double amount) {
		boolean retVal = false;
		Date date = new Date();
		double penalty = 0.00, accountBal = 0.00;
		setPenaltyAmount(penalty); // set default penalty

		// check if maturity date is beyond current date
		if (getMaturityDate().compareTo(date) > 0) {
			penalty = getAccountBalance() * getPenaltyRate() / 100;
			setPenaltyAmount(penalty); // new penalty amount
		}
		// if user takes out money before maturity date, check to see if they can also
		// pay the penalty fee
		if (penalty + amount < getAccountBalance()) {
			// setting new account balance by taking out amount that user wants to withdraw
			// and charging penalty
			accountBal = getAccountBalance() - (penalty + amount);
			setAccountBalance(accountBal);
			retVal = true;
		}
		return retVal;
	}

	/*
	 * default constructor
	 */
	public GIC() {
		super();
	}

	/*
	 * overloaded constructor for customer object
	 */
	public GIC(Customer customer) {
		super(customer);
	}

	public static void main(String[] args) {
		GIC gic = new GIC(); // gic object

		// testing account number method inherited
		System.out.println("Account Number : " + gic.generateAccountNumber());
		// testing deposit method
		gic.deposit(5000.00);
		System.out.println("Deposit : " + gic.getAccountBalance());
		// set investment date
		try {
			gic.setInvestmentDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-09"));
		} catch (Exception e) {

		}
		// set maturity date
		try {
			gic.setMaturityDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-09-09"));
		} catch (Exception e) {

		}

		// setting interest rate
		gic.setInterestRate(3.00);
		gic.setPenaltyRate(20.0);
		System.out.println(gic.toString());
		// withdraw 1000 before maturity
		gic.withdraw(1000);
		System.out.println(gic.toString() + "\nNew Account Balance : " + gic.getAccountBalance());
	}

	@Override
	public String toString() {
		return "GIC [investmentDate=" + investmentDate + ", maturityDate=" + maturityDate + ", interestRate="
				+ interestRate + ", penaltyRate=" + penaltyRate + ", penaltyAmount=" + penaltyAmount + "]";
	}

}
