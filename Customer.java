/*
 *  Author : Sithum Egodawatte
 *  Date :June 08, 2020
*   Description : Customer class to store customer attributes
*   
*   
*   Method List:
*   
*   		getCustomer - Method used to retrieve customer record from a text file. Passes a string for the file name, and a string for the saerch key which in this
*   					  case is the login ID.
*   
*   Constructor(s):
*   		Customer() - Defualt constructor.
*   
*   		Customer(String firstName, String lastName, String address, String phone) - Overloaded constructor which has all the attributes for the customer
*   
 */

public class Customer {

	private String firstName;
	private String lastName;
	private String address;
	private String phone;

	// getter method for first name
	public String getFirstName() {
		return firstName;
	}

	// setter method for first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// getter method for last name
	public String getLastName() {
		return lastName;
	}

	// setter method for last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// getter method for address
	public String getAddress() {
		return address;
	}

	// setter method for address
	public void setAddress(String address) {
		this.address = address;
	}

	// getter method for phone number
	public String getPhone() {
		return phone;
	}

	// setter method for phone number
	public void setPhone(String phone) {
		this.phone = phone;
	}

	// retrieve customer information from text file
	public String getCustomer(String fileName, String searchKey) {
		String retVal; // variable for record
		// search for customer record in customer.txt using loginID as search key
		String custRec = Utility.getRecord("Customer.txt", 1, searchKey);
		// if record is not found
		if (custRec == null || custRec.equals("")) {
			retVal = "";
		}
		// return record otherwise
		else {
			retVal = custRec;
		}
		return retVal;
	}

	/*
	 * default constructor
	 */
	public Customer() {
		super();
	}

	/*
	 * overloaded constructor
	 */

	public Customer(String firstName, String lastName, String address, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

	// self-testiing main method
	public static void main(String[] args) {
		// testing customer class
		// creating customer object and printing values
		Customer cust = new Customer("David", "Best", "23 Apple Road", "416-235-5746");
		System.out.println(cust.getFirstName());
		System.out.println(cust.getLastName());
		System.out.println(cust.getAddress());
		System.out.println(cust.getPhone());
		System.out.println(cust.toString());

		// testing getCustomer method
		BankGUI.userID = "123";
		String custRec = cust.getCustomer("Customer.txt", BankGUI.userID);
		System.out.println(custRec.toString());
	}

	@Override
	public String toString() {
		// auto generated toString method for testing
		return "Customer [first Name = " + firstName + ", last Name = " + lastName + ", address =" + address
				+ ", phone =" + phone + "]";
	}

}
