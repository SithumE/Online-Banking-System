/*
 *  Author : Sithum Egodawatte
 *  Date :June 10, 2020
*   Description : Transaction list class
*   
*   
*   Method List:
*   		extendArray - Method used to extend an array by a certain size. Method takes in a TransactionRecord array and an int for array length. Returns extended array
*   
*   		insertMethod - Method used to insert extended array. Takes in TransactionRecord object and returns true if able to add array.
*   
*   		deleteMethod - Method used to delete a transaction. Takes in TransactionRecord object and returns true if record is found. Returns false if record is not found.
*   
*   		changeMethod - Method used to update a record. Takes in an old TransactionRecord object and replaces it with a new one.
*   
*   		linearSearch - Method used to search through the list to find a record. Returns the record found if it is found.
*   
*   		insertionSort - Method used to sort the list array. Takes in the list and sorts it.
*   
*   		saveRecords - Method used to save records to a file.
*   
*   		formattedString - Method used to format transactions in ascending order in a creative way.
*   
*   Constructor(s):
*   	TransactionList() - Default constructor with default list size
*   
*   	TransactionList(int maxSize) - Overloaded constructor for increased array size if needed.
*   
 */
public class TransactionList {
	// private instance variables
	private final int EXTEND_SIZE = 1;
	private int maxSize, size;
	private TransactionRecord list[];

	// getter method for max size
	public int getMaxSize() {
		return maxSize;
	}

	// setter method for max size
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	// getter method for size
	public int getSize() {
		return size;
	}

	// setter method for max size
	public void setSize(int size) {
		this.size = size;
	}

	// getter method for list
	public TransactionRecord[] getList() {
		return list;
	}

	// setter method for list
	public void setList(TransactionRecord[] list) {
		this.list = list;
	}

	// extendArray method
	private TransactionRecord[] extendArray(TransactionRecord[] array, int length) {
		TransactionRecord[] temp = array.clone();
		array = new TransactionRecord[array.length + length];
		System.arraycopy(temp, 0, array, 0, temp.length);
		return array;
	}

	// insert method
	public boolean insertMethod(TransactionRecord record) {
		// if size is less than max size
		// checks if the size is above the maximum size
		if (size >= maxSize) {
			// add extended size to array
			maxSize = maxSize + EXTEND_SIZE;
			// adding extended list to current list reference
			list = extendArray(list, EXTEND_SIZE);
		}

		list[size] = record; // insert record into array
		size++;
		return true; // returns true if able to add.
	}

	// delete method
	public boolean deleteMethod(TransactionRecord record) {
		int retIndex;
		String strKey = record.getAccountType() + "|" + record.getTransactionType() + "|" + record.getTransactionAmt()
				+ "|" + record.getStartBal() + "|" + record.getEndBal();
		retIndex = linearSearch(list, strKey);
		if (retIndex > -1) { // record found delete
			list[retIndex] = list[size - 1]; // replaces the record from last non blank record
			size--; // decreases size by 1
			return true; // found the record
		}

		return false; // finished the list without finding the record
	}

	// change method
	public boolean changeMethod(TransactionRecord oldRecord, TransactionRecord newRecord) {
		// Deletes the old record
		if (deleteMethod(oldRecord)) {
			insertMethod(newRecord); // inserts the new one
			insertionSort(list); // sort the list after insertion
			return true; // returns true if successful
		}
		return false;
	}

	// the linear search method
	public int linearSearch(TransactionRecord list[], String searchKey) {
		// declaring and initalizing variables for return index
		int retIndex = -1;
		String keyArr[] = searchKey.split("\\|"); // split search keys in array using "|"

		// declare and initialize 3 keys
		char accKey = keyArr[0].charAt(0);
		String transTypeKey = keyArr[1];
		double amountKey = Double.parseDouble(keyArr[2]);
		double startBalance = Double.parseDouble(keyArr[3]);
		double endBalance = Double.parseDouble(keyArr[4]);

		// search for account type
		for (int i = 0; i < list.length; i++) {
			if (list[i].getAccountType() == accKey && list[i].getTransactionType().equals(transTypeKey)
					&& list[i].getTransactionAmt() == amountKey && list[i].getStartBal() == startBalance
					&& list[i].getEndBal() == endBalance) {
				retIndex = i; // searchKey found at location
			}

		}
		return retIndex;

	}// end of linear search

	public void insertionSort(TransactionRecord list[]) {
		// for loop to compare all the list elements
		for (int i = 1; i < list.length; i++) {
			// Start from second item in the list, and store that 'make' as Key
			double key = list[i].getTransactionAmt();
			// Keep the same object (at i th position) in variable
			TransactionRecord tr = list[i];
			// Define another variable j , one before i th position
			int j = i - 1;
			// if current object value is greater when compared, then move the large one to
			// right
			// Decrease the j until it reach 0 and compare 'make' of current object with key

			while (j >= 0 && list[j].getTransactionAmt() > key) {
				list[j + 1] = list[j];
				j = j - 1;
			}
			// once comparison is done insert vr object to correct position of the list
			list[j + 1] = tr;
		}
	}

	public void saveRecords() {
		String content = "";
		for (int i = 0; i < this.getSize(); i++) {
			content = content + getList()[i] + "\n";
		}
		Utility.saveFile("TransactionList.txt", content, false);
	}

	// formatting output when displaying transactions
	public String formattedString() {
		String text = "";
		// displaying records or array elements with formatted output
		// format heading line 1
		text = text + String.format("%1$-5s", "Acc") + String.format("%1$-12s", "Transaction")
				+ String.format("%1$-12s", "Transaction") + String.format("%1$-10s", "Start")
				+ String.format("%1$-10s", "End") + "\n";

		// format heading line 2
		text = text + String.format("%1$-5s", "Type") + String.format("%1$-12s", "Type")
				+ String.format("%1$-12s", "Amount") + String.format("%1$-10s", "Balance")
				+ String.format("%1$-10s", "Balance") + "\n";

		text = text + String.format("%1$-5s", "----")

		// format heading line 3

				+ String.format("%1$-12s", "-----------") + String.format("%1$-12s", "----------")
				+ String.format("%1$-10s", "--------") + String.format("%1$-10s", "--------") + "\n";

		// format data lines for all the elements in the array
		for (int i = 0; i < this.getSize(); i++) {
			TransactionRecord rec = getList()[i];
			String accType;
			if (rec.getAccountType() == 'g') {
				accType = "GIC";
			} else {
				accType = "SAV";
			}
			text = text + String.format("%1$-5s", accType) + String.format("%1$-10s", rec.getTransactionType())
					+ String.format("%1$12s", String.format("%.2f", rec.getTransactionAmt()))
					+ String.format("%1$10s", String.format("%.2f", rec.getStartBal()))
					+ String.format("%1$10s", String.format("%.2f", rec.getEndBal())) + "\n";
		}
		return text;
	}

	/*
	 * default constructor
	 */
	public TransactionList() {
		super();
		this.maxSize = 2;
		this.size = 0;
		this.list = new TransactionRecord[maxSize];
	}

	/*
	 * overloaded constructor
	 */
	public TransactionList(int maxSize) {
		this.maxSize = maxSize;
		this.size = 0;
		this.list = new TransactionRecord[maxSize];
	}

	// self-testing main method
	public static void main(String[] args) {
		System.out.println("Testing insert method");
		TransactionRecord tr; // creating objects
		TransactionList trList = new TransactionList();
		// setting values and printing them using toString and insert method
		tr = new TransactionRecord('s', "deposit", 2500.00, 0.00, 2500.00);
		trList.insertMethod(tr);
		tr = new TransactionRecord('s', "deposit", 1000.00, 2500.00, 3500.00);
		trList.insertMethod(tr);
		tr = new TransactionRecord('s', "withdraw", 500.00, 3500.00, 3000.00);
		trList.insertMethod(tr);
		System.out.println(trList.toString());

		System.out.println("Testing sort method");
		// testing method
		trList.insertionSort(trList.getList());
		System.out.println(trList.toString());

		System.out.println("Testing delete method");
		// deleting a transaction using deletemethod
		tr = new TransactionRecord('s', "deposit", 1000.00, 2500.00, 3500.00);
		if (trList.deleteMethod(tr)) {
			System.out.println(trList.toString());
		} else {
			System.out.println("Record not found");
		}

		// Testing save method (should be saved into file TransactionList.txt)
		trList.saveRecords(); // creates a file

		// testing formattedString method
		System.out.println(trList.formattedString());
	}

	@Override
	public String toString() {
		String text = "";
		// printing records in transaction list
		for (int i = 0; i < this.getSize(); i++) {
			text = text + getList()[i] + "\n";
		}
		return text;
	}

}
