import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 *  Author : Sithum Egodawatte
 *  Date : June 12, 2020
*   Description : Customer GUI class created for customer to edit profile
*   
*   	 
*   Method List:
*   	showProfile - Method used to create UI layout (buttons, size, etc). Does not pass in or return anything.
*   	
*   	actionPerformed - Method used to handle buttons in UI.   
*   
*   	
*   
*   Constructor(s):
*   	CustomerGUI() - Default constructor used for header in UI
*   	
*   	
 */

public class CustomerGUI extends JFrame implements ActionListener {

	// variables for GUI components
	private JPanel displayPanel, controlPanel; // panel for components
	private JLabel lblFirstName, lblLastName, lblAddress, lblPhone, lblGICAccNo, lblSavingAccNo, lblGICBal,
			lblSavingBal;
	private JTextField txtFirstName, txtLastName, txtAddress, txtPhone, txtGICAccNo, txtSavingAccNo, txtGICBal,
			txtSavingBal;
	private JButton btnSave, btnClose; // buttons used in GUI
	private GIC gicAcc;
	private Savings savAcc;
	private Customer cust;

	// dispaly customer profile
	public void showProfile() {
		// width and height for frame
		int width = 350;
		int height = 350;
		setResizable(false);

		setLayout(null); // layout for my frame

		setBounds(400, 400, width, height); // set the size of the frame

		displayPanel = new JPanel(); // new panel for components
		displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		// set the size and position of the displayPanel panel
		displayPanel.setBounds(0, 0, width, height - 100);
		// initializing labels
		lblFirstName = new JLabel("First Name:");
		txtFirstName = new JTextField("", 20);

		lblLastName = new JLabel("Last Name:");
		txtLastName = new JTextField("", 20);

		lblAddress = new JLabel("Address:");
		txtAddress = new JTextField("", 20);

		lblPhone = new JLabel("Phone Number:");
		txtPhone = new JTextField("", 20);

		lblGICAccNo = new JLabel("GIC Acc No:");
		txtGICAccNo = new JTextField("", 20);
		txtGICAccNo.setEditable(false);
		txtGICAccNo.setBackground(BankGUI.bktxtColor);

		lblGICBal = new JLabel("GIC Balance:");
		txtGICBal = new JTextField("", 20);

		lblSavingAccNo = new JLabel("Savings Acc No:");
		txtSavingAccNo = new JTextField("", 20);

		txtSavingAccNo.setEditable(false);
		txtSavingAccNo.setBackground(BankGUI.bktxtColor);

		lblSavingBal = new JLabel("Savings Balance:");
		txtSavingBal = new JTextField("", 20);

		btnSave = new JButton("Save");
		btnClose = new JButton("Close");

		// adding buttons to panels
		displayPanel.add(lblFirstName);
		displayPanel.add(txtFirstName);
		displayPanel.add(lblLastName);
		displayPanel.add(txtLastName);
		displayPanel.add(lblAddress);
		displayPanel.add(txtAddress);
		displayPanel.add(lblPhone);
		displayPanel.add(txtPhone);

		displayPanel.add(lblGICAccNo);
		displayPanel.add(txtGICAccNo);

		displayPanel.add(lblGICBal);
		displayPanel.add(txtGICBal);

		displayPanel.add(lblSavingAccNo);
		displayPanel.add(txtSavingAccNo);

		displayPanel.add(lblSavingBal);
		displayPanel.add(txtSavingBal);

		// creating control panel and setting bounds
		controlPanel = new JPanel();
		controlPanel.setBounds(0, height - 100, width, 100);

		controlPanel.add(btnSave);
		controlPanel.add(btnClose);

		// adding buttons to actionlistener
		btnSave.addActionListener(this);
		btnClose.addActionListener(this);

		// check customer record from text file then assign to customer object
		cust = new Customer();
		String custRec = cust.getCustomer("Customer.txt", BankGUI.userID);
		if (!custRec.equals("")) {
			String custArray[] = custRec.split("\\|");
			txtFirstName.setText(custArray[1]);
			txtLastName.setText(custArray[2]);
			txtAddress.setText(custArray[3]);
			txtPhone.setText(custArray[4]);

			cust.setFirstName(custArray[1]);
			cust.setLastName(custArray[2]);
			cust.setAddress(custArray[3]);
			cust.setPhone(custArray[4]);

			// create accounts with that customer
			gicAcc = new GIC(cust);
			savAcc = new Savings(cust);

		} else {
			// default
			gicAcc = new GIC();
			savAcc = new Savings();
		}

		// get GIC record and assign values
		String gicRec = Utility.getRecord("GIC.txt", 1, BankGUI.userID);

		if (gicRec.equals("")) {
			// set properties for new GICobject
			gicAcc.setAccountNumber(gicAcc.generateAccountNumber());
			gicAcc.setAccountBalance(0.0);// account balance
			// set investment date as today
			Date dt = new Date();
			gicAcc.setInvestmentDate(dt);

			// set maturity date in one year
			Date matDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(matDate);
			cal.add(Calendar.YEAR, 1); // 1 year maturity date
			matDate = cal.getTime();
			gicAcc.setMaturityDate(matDate);
			// Set interest Rate and Maturity Rate
			gicAcc.setInterestRate(3.0);
			gicAcc.setPenaltyRate(20);

		} else {
			String gicArr[] = gicRec.split("\\|");
			txtGICBal.setText(gicArr[3]); // account balance

			// set gicAcc properties from read record
			gicAcc.setAccountNumber(Long.parseLong(gicArr[2]));// account number
			gicAcc.setAccountBalance(Double.parseDouble(gicArr[3]));// account balance
			gicAcc.setInvestmentDate(Utility.stringToDate(gicArr[4]));
			gicAcc.setMaturityDate(Utility.stringToDate(gicArr[5]));
			gicAcc.setInterestRate(Double.parseDouble(gicArr[6]));
			gicAcc.setPenaltyRate(Double.parseDouble(gicArr[7]));

		}
		txtGICAccNo.setText(String.valueOf(gicAcc.getAccountNumber())); // account number

		// get SAVING record and assign to text fields

		savAcc = new Savings(cust);

		String savRec = Utility.getRecord("SAVINGS.txt", 1, BankGUI.userID);

		if (savRec.equals("")) {
			savAcc.setAccountNumber(savAcc.generateAccountNumber());
			savAcc.setAccountBalance(0.0);// account balance
			savAcc.setMinBalance(4000.00);
			savAcc.setFee(4.25);

		} else {

			String savArr[] = savRec.split("\\|");
			txtSavingBal.setText(savArr[3]); // account balance

			// set gicAcc properties from read record
			savAcc.setAccountNumber(Long.parseLong(savArr[2]));// account number
			savAcc.setAccountBalance(Double.parseDouble(savArr[3]));// account balance
			savAcc.setMinBalance(Double.parseDouble(savArr[4]));// minimum bal
			savAcc.setFee(Double.parseDouble(savArr[5]));// fee amount
		}

		txtSavingAccNo.setText(String.valueOf(savAcc.getAccountNumber())); // account number

		// add panel to frame add(panel);
		add(displayPanel);
		add(controlPanel);

		// show the frame
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) {
			// validate inputs

			if (txtFirstName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please provide first name", "Customer Profile",
						JOptionPane.PLAIN_MESSAGE);
			} else if (txtLastName.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please provide last name", "Customer Profile",
						JOptionPane.PLAIN_MESSAGE);
			} else if (txtAddress.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please provide address", "Customer Profile",
						JOptionPane.PLAIN_MESSAGE);
			} else if (txtPhone.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please provide phone number", "Customer Profile",
						JOptionPane.PLAIN_MESSAGE);

			} else if (!Utility.validBalance(txtGICBal.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid GIC Balance", "Customer Profile",
						JOptionPane.PLAIN_MESSAGE);
			} else if (!Utility.validBalance(txtSavingBal.getText())) {
				JOptionPane.showMessageDialog(null, "Invalid Savings Balance", "Customer Profile",
						JOptionPane.PLAIN_MESSAGE);
			} else {

				// set account balances
				gicAcc.setAccountBalance(Double.parseDouble(txtGICBal.getText()));
				savAcc.setAccountBalance(Double.parseDouble(txtSavingBal.getText()));

				// update Customer Profile
				String content = BankGUI.userID + "|" + txtFirstName.getText() + "|" + txtLastName.getText() + "|"
						+ txtAddress.getText() + "|" + txtPhone.getText();

				Utility.contentUpdate("Customer.txt", content);
				// update GIC Account Profile
				// Format : loginID|firstName
				// lastName|accountNumber|accountBalance|investDate|maturityDate|interestRate|penaltyRate

				content = BankGUI.userID + "|" + txtFirstName.getText() + " " + txtLastName.getText() + "|"
						+ gicAcc.getAccountNumber() + "|" + gicAcc.getAccountBalance() + "|"
						+ Utility.dateToString(gicAcc.getInvestmentDate()) + "|"
						+ Utility.dateToString(gicAcc.getMaturityDate()) + "|" + gicAcc.getInterestRate() + "|"
						+ gicAcc.getPenaltyRate();

				Utility.contentUpdate("GIC.txt", content);

				// update Savings Account

				content = BankGUI.userID + "|" + txtFirstName.getText() + " " + txtLastName.getText() + "|"
						+ savAcc.getAccountNumber() + "|" + savAcc.getAccountBalance() + "|" + savAcc.getMinBalance() // Minimum
																														// Balance
						+ "|" + savAcc.getFee(); // Transaction fee

				Utility.contentUpdate("SAVINGS.txt", content);
				dispose();

			}
		} else if (e.getSource() == btnClose) { // btnClose click
			dispose();
		}

	}

	// default constructor
	public CustomerGUI() {
		super("User Profile");
	}

	// self testing main method
	public static void main(String[] args) {
		BankGUI.userID = "s1";
		CustomerGUI gui = new CustomerGUI();
		gui.showProfile();
	}

}
