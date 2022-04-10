import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 *  Author : Sithum Egodawatte
 *  Date :June 15, 2020
*   Description : CheckBalGUI class used to check and display all customer account details (name, address, balance, etc)
*   
*   
*   Method List:
*   		showProfile - Method used to display customer profile to customer. Takes in nothing and returns nothing.
*   
*   		actionPerformed - Method used to handle buttons in UI. 
*   
*   
*   Constructor(s):
*   		CheckBalGUI() - Default constructor to display header : "Account Info"
*   		
*/

public class CheckBalGUI extends JFrame implements ActionListener {

	// variables for GUI components
	JPanel displayPanel, controlPanel; // panel for components
	JLabel lblFirstName, lblLastName, lblAddress, lblPhone, lblGICAccNo, lblSavingAccNo, lblGICBal, lblSavingBal,
			lblInvestDT, lblMaturityDT, lblIntRate, lblPenaltyRate, lblMinimumBal, lblFeeAmt;
	JTextField txtFirstName, txtLastName, txtAddress, txtPhone, txtGICAccNo, txtSavingAccNo, txtGICBal, txtSavingBal,
			txtInvestDT, txtMaturityDT, txtIntRate, txtPenaltyRate, txtMinimumBal, txtFeeAmt;
	JButton btnClose; // buttons used in GUI


	// dispaly customer profile
	public void showProfile() {
		// width and height for frame
		int width = 350;
		int height = 450;
		setResizable(false);

		setLayout(null); // layout for my frame

		setBounds(400, 400, width, height); // set the size of the frame

		displayPanel = new JPanel(); // new panel for components
		displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		// set the size and position of the displayPanel panel
		displayPanel.setBounds(0, 0, width, height - 100);

		lblFirstName = new JLabel("First Name:");
		txtFirstName = new JTextField("", 20);
		txtFirstName.setBackground(BankGUI.bktxtColor);
		txtFirstName.setEditable(false);

		lblLastName = new JLabel("Last Name:");
		txtLastName = new JTextField("", 20);
		txtLastName.setBackground(BankGUI.bktxtColor);
		txtLastName.setEditable(false);

		lblAddress = new JLabel("Address:");
		txtAddress = new JTextField("", 20);
		txtAddress.setBackground(BankGUI.bktxtColor);
		txtAddress.setEditable(false);

		lblPhone = new JLabel("Phone Number:");
		txtPhone = new JTextField("", 20);
		txtPhone.setBackground(BankGUI.bktxtColor);
		txtPhone.setEditable(false);

		lblGICAccNo = new JLabel("GIC Acc No:");
		txtGICAccNo = new JTextField("", 20);
		txtGICAccNo.setBackground(BankGUI.bktxtColor);
		txtGICAccNo.setEditable(false);

		lblInvestDT = new JLabel("Investment Date:");
		txtInvestDT = new JTextField("", 20);
		txtInvestDT.setBackground(BankGUI.bktxtColor);
		txtInvestDT.setEditable(false);

		lblMaturityDT = new JLabel("Maturity Date:");
		txtMaturityDT = new JTextField("", 20);
		txtMaturityDT.setBackground(BankGUI.bktxtColor);
		txtMaturityDT.setEditable(false);

		lblIntRate = new JLabel("Interest Rate:");
		txtIntRate = new JTextField("", 20);
		txtIntRate.setBackground(BankGUI.bktxtColor);
		txtIntRate.setEditable(false);

		lblPenaltyRate = new JLabel("Penalty Rate:");
		txtPenaltyRate = new JTextField("", 20);
		txtPenaltyRate.setBackground(BankGUI.bktxtColor);
		txtPenaltyRate.setEditable(false);

		lblGICBal = new JLabel("GIC Balance:");
		txtGICBal = new JTextField("", 20);
		txtGICBal.setBackground(BankGUI.bktxtColor);
		txtGICBal.setEditable(false);

		lblSavingAccNo = new JLabel("Savings Acc No:");
		txtSavingAccNo = new JTextField("", 20);
		txtSavingAccNo.setBackground(BankGUI.bktxtColor);
		txtSavingAccNo.setEditable(false);

		lblMinimumBal = new JLabel("Minimum Balance:");
		txtMinimumBal = new JTextField("", 20);
		txtMinimumBal.setBackground(BankGUI.bktxtColor);
		txtMinimumBal.setEditable(false);

		lblFeeAmt = new JLabel("Minimum Balance:");
		txtFeeAmt = new JTextField("", 20);
		txtFeeAmt.setBackground(BankGUI.bktxtColor);
		txtFeeAmt.setEditable(false);

		lblSavingBal = new JLabel("Savings Balance:");
		txtSavingBal = new JTextField("", 20);
		txtSavingBal.setBackground(BankGUI.bktxtColor);
		txtSavingBal.setEditable(false);

		// buttons
		btnClose = new JButton("Close");

		// adding everything to display panel
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

		displayPanel.add(lblInvestDT);
		displayPanel.add(txtInvestDT);
		displayPanel.add(lblMaturityDT);
		displayPanel.add(txtMaturityDT);

		displayPanel.add(lblIntRate);
		displayPanel.add(txtIntRate);
		displayPanel.add(lblPenaltyRate);
		displayPanel.add(txtPenaltyRate);
		displayPanel.add(lblGICBal);
		displayPanel.add(txtGICBal);

		displayPanel.add(lblSavingAccNo);
		displayPanel.add(txtSavingAccNo);
		displayPanel.add(lblMinimumBal);
		displayPanel.add(txtMinimumBal);
		displayPanel.add(lblFeeAmt);
		displayPanel.add(txtFeeAmt);
		displayPanel.add(lblSavingBal);
		displayPanel.add(txtSavingBal);

		// control panel for buttons
		controlPanel = new JPanel();
		controlPanel.setBounds(0, height - 100, width, 100);
		controlPanel.add(btnClose);

		btnClose.addActionListener(this);

		// get customer record and assign to text fields
		Customer cust = new Customer();
		String custRec = cust.getCustomer("Customers.txt", BankGUI.userID);

		if (!custRec.equals("")) {
			String custArr[] = custRec.split("\\|");

			txtFirstName.setText(custArr[1]);
			txtLastName.setText(custArr[2]);
			txtAddress.setText(custArr[3]);
			txtPhone.setText(custArr[4]);

			cust.setFirstName(custArr[1]);
			cust.setLastName(custArr[2]);
			cust.setAddress(custArr[3]);
			cust.setPhone(custArr[4]);
		}

		// get GIC record and assign to text fields
		Account acc1 = new Account();
		String gicRec = acc1.getAccRec(cust, "GIC.txt");
		String gicArr[] = gicRec.split("\\|");

		txtGICAccNo.setText(gicArr[2]); // account number
		txtGICBal.setText(gicArr[3]); // account balance
		txtInvestDT.setText(gicArr[4]); // investment Date
		txtMaturityDT.setText(gicArr[5]); // maturity Date
		txtIntRate.setText(gicArr[6]); // interest Rate
		txtPenaltyRate.setText(gicArr[7]); // Penalty Rate

		// get SAVING record and assign to text fields
		Account acc2 = new Account();
		String savRec = acc2.getAccRec(cust, "SAVINGS.txt");
		String savArr[] = savRec.split("\\|");

		txtSavingAccNo.setText(savArr[2]); // account number
		txtSavingBal.setText(savArr[3]); // account balance
		txtMinimumBal.setText(savArr[4]); // minimum balance
		txtFeeAmt.setText(savArr[5]); // Fee amount

		// add panel to frame add(panel);
		add(displayPanel);
		add(controlPanel);

		// show the frame
		setVisible(true);

	}

	// perform actions
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnClose) { // btnClose click
			dispose();
		}

	}
	// default constructor
	public CheckBalGUI() {
		super("Account Info");
	}

	public static void main(String[] args) {
		// create customer object
		CheckBalGUI custBal = new CheckBalGUI();
		BankGUI.userID = "s1";
		// display user interface
		custBal.showProfile();

	}
}
