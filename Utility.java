import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.swing.JOptionPane;

/*
 *  Author : Sithum Egodawatte
 *  Date :June 09, 2020
*   Description : Utility class used for many methods used throughout project. 
*   
*   
*   Method List:
*   	saveFile - Method used to save information to file. Takes in a string for fikleName, the content in the file, and true or false if the file should append or not.
*   			   Does not return any value
*   
*   	getRecord - Method used to search through files for a record. Takes in a string for fileName, int for the fieldnumber they are searching, and String for searchKey.
*   				Return true if the record is found.
*   
*   	dupRecord - Method used if there is a duplicate userID when creating an account. Takes in a string for fileName, int for the fieldnumber they are searching, and 
*   				String for searchKey. Returns true if duplicate is found
*   
*   	contentUpdate - Method used to update information in a file. Takes in a String for fileName, and a String for the updateLine. Does not return any value.
*   
*   	encode - Method to encode password. Takes in the password (String), encodes it and returns the encoded password.
*   
*   	decode - Method to decode pasword. Takes in the encoded password (String) and returns decoded password.
*   
*   	validAmount - Method to check if input amount for transaction is valid. Takes in a String for transactionAmount and  returns true if the amount is valid (above 0, no letters)
*   
*   	dateToString - Method used to convert date object to a string representation of the date object. Returns the converted value.
*   	
*   	stringToDate - Method used to convert String object to a date representation of the String object. Returns the converted value.
*   
*   	validCustomer - Method used to validate customer by checking userID and userType to see if they are already registered. Returns true if valid customer, false otherwise.
*   
*   	validEmployee - Method used to validate employee by checking userID and userType to see if they are already registered. Returns true if valid employee, false otherwise.
*   
*   	validAccount - Method used to see if user has any account entries (GIC or Savings). Returns true if they do, false otherwise.
*   
*   	validBalance - Method used to validate if balance is in numeric format and is greater than 0. Returns true if those conditions are met, false otherwise.
*   
*   Constructor(s):
*   	Utility() - Default constructor (unused).
 */
public class Utility {

	// save file method
	public static void saveFile(String fileName, String content, boolean appendMode) {
		try (FileWriter writer = new FileWriter(fileName, appendMode); // creating writer to write content to file
				BufferedWriter bw = new BufferedWriter(writer)) {
			// write content to buffered writer
			bw.write(content + "\n");
			bw.close(); // close buffered writer & file writer
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// search record method
	public static String getRecord(String fileName, int fieldNumber, String searchKey) {
		String retVal = "";
		boolean recFound = false;
		try (FileReader reader = new FileReader(fileName); // creating writer to write content to file
				BufferedReader br = new BufferedReader(reader)) {
			String line; // string to store every line

			// repeat until all lines are read
			while ((line = br.readLine()) != null) {
				// string array to split record into fields using seperator ( | )
				String[] rec = line.split("\\|");
				// search field has to always start with 1
				if (fieldNumber > 0) {
					// if search key is found in given field
					if (rec[fieldNumber - 1].equals(searchKey)) {
						// return record and break loop
						recFound = true;
						retVal = line;
						break;
					}
				}
			}
			// close readers
			br.close();
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return retVal;
	}

	// dupRecord method to check if a login is already made
	public static boolean dupRecord(String fileName, int fieldNumber, String searchKey) {
		int recCount = 0;
		boolean recDup = false;
		try (FileReader reader = new FileReader(fileName); // creating writer to write content to file
				BufferedReader br = new BufferedReader(reader)) {
			String line; // string to store every line

			// repeat until all lines are read
			while ((line = br.readLine()) != null) {
				// string array to split record into fields using seperator ( | )
				String[] rec = line.split("\\|");
				// search field has to always start with 1
				if (rec[0].equals(searchKey)) {
					// if search key is found in given field
					recCount = recCount + 1;
				}
			}
			// close readers
			br.close();
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if (recCount > 0) {
			recDup = true;
		}
		return recDup;
	}

	// method for content updates
	public static void contentUpdate(String fileName, String updateLine) {
		String content = "";
		boolean recFound = false;
		// creates a file if file does not exist
		// usually first time running program
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		try (FileReader reader = new FileReader(fileName); // creating writer to write content to file
				BufferedReader br = new BufferedReader(reader)) {
			String line; // string to store every line

			// repeat until all lines are read
			while ((line = br.readLine()) != null) {
				// string array to split record into fields using seperator ( | )
				String[] rec = line.split("\\|");
				// check if first field = login ID
				if (rec[0].equals(BankGUI.userID)) {
					// replace line with updated line
					content = content + updateLine + "\n";
					recFound = true;
				} else {
					// else keep the existing line
					content = content + line + "\n";
				}
			}
			// if record is not found
			if (!recFound) {
				// add new line for user
				content = content + updateLine + "\n";
			}
			// try block to update file by writing new content
			try (FileWriter writer = new FileWriter(fileName, false); BufferedWriter bw = new BufferedWriter(writer)) {
				// overwriting the file with new content
				bw.write(content);
				// close writers
				bw.close();
				writer.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			// closing readers
			br.close();
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	// encode method
	public static String encode(String str) {
		// encoding string using Base64 class
		String encodeStr = Base64.getEncoder().encodeToString(str.getBytes());
		return encodeStr;
	}

	// decode method
	public static String decode(String str) {
		// decode string using Base64 class
		byte[] bytes = Base64.getDecoder().decode(str);
		String decodeStr = new String(bytes);
		return decodeStr;
	}

	// validAmount method
	public static boolean validAmount(String transactionAmt) {
		boolean retVal = false;
		try {
			// parse string amount to double
			double amount;
			amount = Double.parseDouble(transactionAmt);
			// if amount is greater than 0 return true
			if (amount > 0) {
				retVal = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return retVal;
	}

	// dateToString method
	public static String dateToString(Date date) {
		// declaring string and format variables
		String strDate;
		Format formatter;
		// initialize format type to yyyy-MM-dd
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		// use format to format string
		strDate = formatter.format(date);
		return strDate;
	}

	// stringToDate method
	public static Date stringToDate(String date) {
		// declare date variable and set it to null
		Date retDate = null;
		try {
			// parse date to string using SimpleDateFormat
			retDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return retDate;
	}

	public static boolean validCustomer() {
		boolean retVal = false;
		// if user is not signed in
		if (BankGUI.userID == null || BankGUI.userID.equals("")) {
			JOptionPane.showMessageDialog(null, "Please Sign-in first ", "Customer Validation",
					JOptionPane.PLAIN_MESSAGE);
			// validate User Type
		} else if (BankGUI.userType != null && BankGUI.userType.substring(0, 4).equals("Cust")) {
			retVal = true;
		} else {
			JOptionPane.showMessageDialog(null, "Customer only feauture", "Customer Validation",
					JOptionPane.PLAIN_MESSAGE);
		}
		return retVal;
	}

	// Validate Bank Employee
	public static boolean validEmployee() {
		boolean retVal = false;
		// if user is not signed in
		if (BankGUI.userID == null || BankGUI.userID.equals("")) {
			JOptionPane.showMessageDialog(null, "Please Sign-in first ", "Customer Validation",
					JOptionPane.PLAIN_MESSAGE);
			// validate User Type
		} else if (BankGUI.userType != null && BankGUI.userType.substring(0, 4).equals("Empl")) {
			retVal = true;
		} else {
			JOptionPane.showMessageDialog(null, "Employee only feature", "Customer Validation",
					JOptionPane.PLAIN_MESSAGE);
		}
		return retVal;
	}

	// Validate Accounts

	public static boolean validAccount() {
		boolean retVal = true;
		// checks GIC.txt to see if userID is registered
		String accRec = Utility.getRecord("GIC.txt", 1, BankGUI.userID);
		if (accRec.equals("")) {
			JOptionPane.showMessageDialog(null, "You don't have accounts with us!", "Account Validation",
					JOptionPane.PLAIN_MESSAGE);
			retVal = false;
		}

		return retVal;
	}

	// balance amount validation
	public static boolean validBalance(String balance) {
		boolean retVal = false;
		try {
			// convert string value to double
			double bal = Double.parseDouble(balance);
			// if number is greater than 0 or valid return true
			if (bal > 0) {
				retVal = true;
			}
		} catch (Exception e) {

		}
		return retVal;
	}

	/*
	 * default constructor
	 */
	public Utility() {
		super();
	}

	// self-testing main method
	public static void main(String[] args) {
		// testing saveFile method
		String content;
		content = "User1|Mark|Thompson|123 Apple Road, Missisauga, ON| 905-295-4923";
		Utility.saveFile("customers.txt", content, true);
		content = "User2|Josh|Thompson|321 Apple Road, Missisauga, ON| 905-295-4923";
		Utility.saveFile("customers.txt", content, true);

		// testing contentUpdate method
		BankGUI.userID = "User1";
		content = "User1|Bob|Thompson|123 Apple Road, Missisauga, ON| 905-295-4923";
		Utility.contentUpdate("customers.txt", content);

		// testing encode & decode methods
		String password = "unicorn1", encodedPassword = "";
		encodedPassword = Utility.encode(password);
		System.out.println("Encoded password is : " + encodedPassword);
		System.out.println("Decoded password is : " + Utility.decode(encodedPassword));

		// testing dateToString & stringToDate methods
		Date d = new Date();
		System.out.println("Date to String : " + Utility.dateToString(d));
		System.out.println("String to Date : " + Utility.stringToDate("2020-06-11"));

		// testing getRecord method
		String record = Utility.getRecord("customers.txt", 1, "User1");
		System.out.println("Record : " + record);
		String record2 = Utility.getRecord("customers.txt", 1, "User4");
		System.out.println("Record : " + record2);

		// testing dupRecord method
		Utility.dupRecord("Login.txt", 1, "s2");
		System.out.println("Duplicated Login ID :" + Utility.dupRecord("Login.txt", 1, "s2"));

		// testing validCustomer method
		// creating credentials and type to see if it matches login in file
		BankGUI.userID = "s1";
		BankGUI.userType = "Customer";
		System.out.println("Customer validity : " + Utility.validCustomer());

		BankGUI.userID = "s2";
		BankGUI.userType = "abcdef";
		System.out.println("Customer validity : " + Utility.validCustomer());

		// testing validEmployee method
		// creating credentials and type to see if it matches login in file
		BankGUI.userID = "s3";
		BankGUI.userType = "Employee";
		System.out.println("Employee validity : " + Utility.validEmployee());

		BankGUI.userID = "s2";
		BankGUI.userType = "abcdef";
		System.out.println("Employee validity : " + Utility.validEmployee());

		// testing validAccounts method
		BankGUI.userID = "s1";
		System.out.println("Account validity : " + Utility.validAccount());

		BankGUI.userID = "s2";
		System.out.println("Account validity : " + Utility.validAccount());

		// testing validBalance method
		System.out.println("Valid Amount : " + Utility.validAmount("100"));
		System.out.println("Valid Amount : " + Utility.validAmount("-100"));
		System.out.println("Valid Amount : " + Utility.validAmount("abc"));

	}

}
