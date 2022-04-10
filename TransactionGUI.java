import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 *  Author : Sithum Egodawatte
 *  Date :June 13, 2020
*   Description : TransactionGUI class used for user transactions
*   
*   
*   Method List:
*   	showDisplay - Method used to create UI layout (buttons, size, etc). Does not pass in or return anything.
* 
* 
*   	actionPerformed - Method used to handle buttons in UI.  
*   
*   Constructor(s):
*   	TramsactionGUI() - Default constructor used for header in Main UI
*   
 */
public class TransactionGUI extends JFrame implements ActionListener {

	// variables for GUI components
	JPanel displayPanel, controlPanel; // panel for components
	JLabel lblFirstName, lblLastName, lblAddress, lblPhone, lblAccount, lblBalance, lblTranAmt;
	JTextField txtFirstName, txtLastName, txtAddress, txtPhone, txtBalance, txtTranAmt;
	JButton btnSave, btnClose; // buttons used in GUI
	JComboBox<String> cmbAccounts, cmbAccType; // combo boxes
	// double gicBal, savBal, savMinBal, savFee;
	// String invDate, matDate, intRate, penaltyRate;
	GIC gicAcc;
	Savings savAcc;

	// dispaly customer profile
	public void showDisplay() {
		// width and height for frame
		int width = 350;
		int height = 300;
		setResizable(false);
		setLayout(null); // layout for my frame

		setBounds(400, 400, width, height); // set the size of the frame

		// light green color for text field
		Color bkColor = new Color(188, 252, 203);

		displayPanel = new JPanel(); // new panel for components
		displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		// set the size and position of the displayPanel panel
		displayPanel.setBounds(0, 0, width, height - 100);

		lblFirstName = new JLabel("First Name:");
		txtFirstName = new JTextField("", 20);
		txtFirstName.setEditable(false);
		txtFirstName.setBackground(bkColor);

		lblLastName = new JLabel("Last Name:");
		txtLastName = new JTextField("", 20);
		txtLastName.setEditable(false);
		txtLastName.setBackground(bkColor);

		lblAddress = new JLabel("Address:");
		txtAddress = new JTextField("", 20);
		txtAddress.setEditable(false);
		txtAddress.setBackground(bkColor);

		lblPhone = new JLabel("Phone Number:");
		txtPhone = new JTextField("", 20);
		txtPhone.setEditable(false);
		txtPhone.setBackground(bkColor);

		lblBalance = new JLabel("Account Balance:");
		txtBalance = new JTextField("", 10);
		txtBalance.setEditable(false);
		txtBalance.setBackground(bkColor);

		lblTranAmt = new JLabel("Amount:");
		txtTranAmt = new JTextField("", 10);

		lblAccount = new JLabel("Account :");
		// find customer

		// BankGUI.userID="u";

		Customer cust = new Customer();
		String custRec = cust.getCustomer("Customers.txt", BankGUI.userID);

		// Set Objects
		if (!custRec.equals("")) {
			String custArr[] = custRec.split("\\|");
			txtFirstName.setText(custArr[1]);
			txtLastName.setText(custArr[2]);
			txtAddress.setText(custArr[3]);
			txtPhone.setText(custArr[4]);

			// Set The Customer Object
			cust.setFirstName(custArr[1]);
			cust.setLastName(custArr[2]);
			cust.setAddress(custArr[3]);
			cust.setPhone(custArr[4]);
		} else {
			cust = null;
		}

		String gicRec = Utility.getRecord("GIC.txt", 1, BankGUI.userID);
		if (cust != null && (gicRec == null || gicRec.equals(""))) {

		} else {
			// New GIC object and set values
			gicAcc = new GIC(cust);

			String gicArr[] = gicRec.split("\\|");
			// System.out.println(gicRec);
			gicAcc.setAccountNumber(Long.parseLong(gicArr[2]));
			gicAcc.setAccountBalance(Double.parseDouble(gicArr[3]));

			gicAcc.setInvestmentDate(Utility.stringToDate(gicArr[4]));
			gicAcc.setMaturityDate(Utility.stringToDate(gicArr[5]));
			gicAcc.setInterestRate(Double.parseDouble(gicArr[6]));
			gicAcc.setPenaltyRate(Double.parseDouble(gicArr[7]));

			// find Saving Info and assign variables
		}

		String savRec = Utility.getRecord("SAVINGS.txt", 1, BankGUI.userID);

		if (cust != null && (savRec == null || savRec.equals(""))) {
			System.out.println("No such Customer");
		} else {
			// New Saving object and set values
			savAcc = new Savings(cust);

			String savArr[] = savRec.split("\\|");

			savAcc.setAccountNumber(Long.parseLong(savArr[2]));
			savAcc.setAccountBalance(Double.parseDouble(savArr[3]));
			savAcc.setMinBalance(Double.parseDouble(savArr[4]));
			savAcc.setFee(Double.parseDouble(savArr[5]));

		}

		// txtBalance.setText(String.valueOf(String.format("%.2f",
		// gicAcc.getAccountBalance())));

		// fill out account combo-box
		String acc[] = { "GIC : " + gicAcc.getAccountNumber(), "SAV : " + savAcc.getAccountNumber() };
		cmbAccounts = new JComboBox<String>(acc);
		cmbAccounts.setSize(150, 10);
		cmbAccounts.setBackground(Color.WHITE);

		String tranType[] = { "Deposit", "Withdraw" };
		cmbAccType = new JComboBox<String>(tranType);
		cmbAccType.setSize(150, 10);
		cmbAccType.setBackground(Color.WHITE);

		// display balance
		if (cmbAccounts.getSelectedItem().toString().contains("GIC")) {
			txtBalance.setText(String.valueOf(String.format("%.2f", gicAcc.getAccountBalance())));
		} else {
			txtBalance.setText(String.valueOf(String.format("%.2f", savAcc.getAccountBalance())));
		}

		// buttons
		btnSave = new JButton("Save");
		btnClose = new JButton("Close");

		displayPanel.add(lblFirstName);
		displayPanel.add(txtFirstName);
		displayPanel.add(lblLastName);
		displayPanel.add(txtLastName);
		displayPanel.add(lblAddress);
		displayPanel.add(txtAddress);
		displayPanel.add(lblPhone);
		displayPanel.add(txtPhone);
		displayPanel.add(lblAccount);
		displayPanel.add(cmbAccounts);
		displayPanel.add(txtBalance);
		displayPanel.add(cmbAccType);
		displayPanel.add(lblTranAmt);
		displayPanel.add(txtTranAmt);

		// displayPanel.add(btnSave);
		// displayPanel.add(btnClose);

		controlPanel = new JPanel();
		controlPanel.setBounds(0, height - 100, width, 100);

		controlPanel.add(btnSave);
		controlPanel.add(btnClose);

		btnSave.addActionListener(this);
		btnClose.addActionListener(this);
		cmbAccounts.addActionListener(this);

		// get GIC record and assign to text fields

		// add panel to frame add(panel);
		add(displayPanel);
		add(controlPanel);

		// show the frame
		setVisible(true);

	}

	// perform actions
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) {
			// validate inputs
			String content = "";

			// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// LocalDateTime dt = LocalDateTime.now();
			Date toDay = new Date();
			String tranDate = Utility.dateToString(toDay);

			// Validate tranAmt

			if (Utility.validAmount(txtTranAmt.getText())) {

				// if Transaction Type is Deposit : Update the amounts
				if (cmbAccType.getSelectedItem().toString().equals("Deposit")) {
					// Update Accounts

					// String accNo = cmbAccounts.getSelectedItem().toString().substring(6);

					Double tranAmount = Double.parseDouble(txtTranAmt.getText());

					// GIC
					if (cmbAccounts.getSelectedItem().toString().contains("GIC")) {
						// Update GIC.txt

						gicAcc.deposit(tranAmount);

						// Format: loginID|firstName
						// lastName|accNumber|accBalance|investDate|maturityDate|interestRate|PenaltyRate

						content = BankGUI.userID + "|" + txtFirstName.getText() + " " + txtLastName.getText() + "|"
								+ gicAcc.getAccountNumber() + "|" + gicAcc.getAccountBalance() + "|"
								+ Utility.dateToString(gicAcc.getInvestmentDate()) + "|"
								+ Utility.dateToString(gicAcc.getMaturityDate()) + "|" + gicAcc.getInterestRate() + "|"
								+ gicAcc.getPenaltyRate();
						Utility.contentUpdate("GIC.txt", content);

						// insert transaction record into TRANS.txt file when
						// Format: firstName lastName|tranDate|accNumber|accType|tranType|tranAmount

						content = txtFirstName.getText() + " " + txtLastName.getText() // customerName

								+ "|" + tranDate // trnasction date
								+ "|" + gicAcc.getAccountNumber() // account number
								+ "|" + "GIC" // account Type
								+ "|" + "deposit" // trnaction type
								+ "|" + tranAmount + "\n"; // trnactionAmount

						Utility.saveFile("TRANS.txt", content, true);

						JOptionPane.showMessageDialog(null, "Successful");

					} else {
						// Update SAVINGS.txt
						Double accBal = savAcc.getAccountBalance() + tranAmount;

						savAcc.deposit(tranAmount);

						// Format: loginID|firstName lastName|accNumber|accBalance|minimalBal|tranFee
						content = BankGUI.userID + "|" + txtFirstName.getText() + " " + txtLastName.getText() + "|"
								+ savAcc.getAccountNumber() + "|" // account number
								+ savAcc.getAccountBalance() + "|" + savAcc.getMinBalance() + "|" + savAcc.getFee();

						Utility.contentUpdate("SAVINGS.txt", content);

						// insert transaction record into TRANS.txt file when
						// Format: firstName lastName|tranDate|accNumber|accType|tranType|tranAmount

						content = txtFirstName.getText() + " " + txtLastName.getText() // customerName

								+ "|" + tranDate // trnasction date
								+ "|" + savAcc.getAccountNumber() // account number
								+ "|" + "Savings" // account Type
								+ "|" + "deposit" // trnaction type
								+ "|" + tranAmount + "\n"; // trnactionAmount

						Utility.saveFile("TRANS.txt", content, true);
						JOptionPane.showMessageDialog(null, "Successful");
					}

				}

			} else {
				JOptionPane.showMessageDialog(null, "Transaction Amount is not accepted", "Deposit/Withdrawl",
						JOptionPane.PLAIN_MESSAGE);
			}
			// Withdrawals
			if (cmbAccType.getSelectedItem().toString().equals("Withdrawl")) {

				// Withdrawls from Savings Account

				if (cmbAccounts.getSelectedItem().toString().contains("SAV")) {
					double tranAmpount = Double.parseDouble(txtTranAmt.getText());
					if (tranAmpount > savAcc.getAccountBalance()) {
						JOptionPane.showMessageDialog(null, "Insufficent Balance to withdraw such amount",
								"Deposit/Withdrawl", JOptionPane.PLAIN_MESSAGE);
					} else {
						// create Saving object based on retrieved value

						if (savAcc.withdraw(tranAmpount)) {
							// Update information in SAVINGS.txt file

							content = BankGUI.userID + "|" + txtFirstName.getText() + " " + txtLastName.getText() + "|"
									+ savAcc.getAccountNumber() + "|" + savAcc.getAccountBalance() + "|"
									+ savAcc.getMinBalance() + "|" + savAcc.getFee();

							Utility.contentUpdate("SAVINGS.txt", content);

							// insert transaction record into TRANS.txt file
							// Format: firstName lastName|tranDate|accNumber|accType|tranType|tranAmount

							content = txtFirstName.getText() + " " + txtLastName.getText() // customerName
									+ "|" + tranDate // trnasction date
									+ "|" + savAcc.getAccountNumber() // account number
									+ "|" + "Savings" // account Type
									+ "|" + "withdrawl" // trnaction type
									+ "|" + tranAmpount; // trnaction amount

							Utility.saveFile("TRANS.txt", content, true);
							JOptionPane.showMessageDialog(null, "Successful");

							// insert transaction record into TRANS.txt file
							// when account balance is below minimalBal
							// Format: firstName lastName|tranDate|accNumber|accType|tranType|tranAmount
							if (savAcc.getAccountBalance() < savAcc.getMinBalance()) {
								content = txtFirstName.getText() + " " + txtLastName.getText() // customer name
										+ "|" + tranDate // trnasction date
										+ "|" + savAcc.getAccountNumber() // account number

										+ "|" + "Savings" // account Type
										+ "|" + "fee" // trnaction type
										+ "|" + savAcc.getFee(); // trnaction amount

								Utility.saveFile("TRANS.txt", content, true);

							}
						}
					}
				}

				// withdrawl from GIC account
				if (cmbAccounts.getSelectedItem().toString().contains("GIC")) {

					if (Double.parseDouble(txtTranAmt.getText()) > gicAcc.getAccountBalance()) {
						JOptionPane.showMessageDialog(null, "Insufficent Balance", "Deposit/Withdrawl",
								JOptionPane.PLAIN_MESSAGE);
					} else {
						// create Saving object based on retrieved value
						Double tranAmt = Double.parseDouble(txtTranAmt.getText());
						if (gicAcc.withdraw(tranAmt)) {

							// Update information in GIC.txt file

							content = BankGUI.userID + "|" + txtFirstName.getText() + " " + txtLastName.getText() + "|"
									+ gicAcc.getAccountNumber() + "|" + gicAcc.getAccountBalance() + "|"
									+ Utility.dateToString(gicAcc.getInvestmentDate()) + "|"
									+ Utility.dateToString(gicAcc.getMaturityDate()) + "|" + gicAcc.getInterestRate()
									+ "|" + gicAcc.getPenaltyRate();

							Utility.contentUpdate("GIC.txt", content);

							// insert transaction record into TRANS.txt file when
							// Format: firstName lastName|tranDate|accNumber|accType|tranType|tranAmount

							content = txtFirstName.getText() + " " + txtLastName.getText() // customerName

									+ "|" + tranDate // trnasction date
									+ "|" + gicAcc.getAccountNumber() // account number
									+ "|" + "GIC" // account Type
									+ "|" + "withdrawl" // trnaction type
									+ "|" + Double.parseDouble(txtTranAmt.getText()); // trnactionAmount

							Utility.saveFile("TRANS.txt", content, true);
							JOptionPane.showMessageDialog(null, "Successful");

							// insert transaction record into TRANS.txt file with penalty
							// Format: firstName lastName|tranDate|accNumber|accType|tranType|tranAmount

							if (gicAcc.getPenaltyAmount() > 0) {

								content = txtFirstName.getText() + " " + txtLastName.getText() // customerName

										+ "|" + tranDate // trnasction date
										+ "|" + gicAcc.getAccountNumber() // account number
										+ "|" + "GIC" // account Type
										+ "|" + "penalty" // trnaction type
										+ "|" + gicAcc.getPenaltyAmount(); // penaltyAmount

								Utility.saveFile("TRANS.txt", content, true);

							}
						}
					}
				}
			}

			dispose();
		}
		if (e.getSource() == cmbAccounts) { // cmbAccounts click
			// depending on account display the balance
			if (cmbAccounts.getSelectedItem().toString().contains("GIC")) {
				txtBalance.setText(String.valueOf(String.format("%.2f", gicAcc.getAccountBalance())));
			} else {
				txtBalance.setText(String.valueOf(String.format("%.2f", savAcc.getAccountBalance())));
			}
		}
		if (e.getSource() == btnClose) { // btnClose click
			dispose();
		}

	}

	// default constructor
	public TransactionGUI() {
		super("Account - Deposit/Withdraw");

	}

	// self-testing main method
	public static void main(String[] args) {
		// create customer object
		TransactionGUI accUI = new TransactionGUI();
		BankGUI.userID = "s1";
		// display user interface
		accUI.showDisplay();

	}
}
