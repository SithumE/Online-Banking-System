import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 *  Author : Sithum Egodawatte
 *  Date : June 13, 2020
*   Description : Handling transaction list
*   
*   
*   Method List:
*   	showDisplay - Method used to create UI layout (buttons, size, etc). Does not pass in or return anything.
*   
*   	displayList - Method to show transactionList using formaattedString method to employees
* 
* 
*   	actionPerformed - Method used to handle buttons in UI.  
*   
*   Constructor(s):
*   	TramsListGUI() - Default constructor used for header in Main UI
*   
*   	
*   
 */
public class TransListGUI extends JFrame implements ActionListener {

	// variables for GUI components
	JPanel displayPanel, controlPanel; // panel for components
	JLabel lblAccType, lblTranType, lblAmount, lblTranCount, lblStartBal, lblEndBal;
	JTextField txtTranAmt, txtTranCount, txtStartBal, txtEndBal;
	JButton btnSave, btnAdd, btnList, btnSort, btnClose; // buttons used in GUI
	JComboBox<String> cmbTranType, cmbAccType; // combo boxes
	TransactionRecord list[];
	TransactionRecord recGIC;
	TransactionRecord recSav;

	TransactionList transList;

	// dispaly customer profile
	public void showDisplay() {
		// width and height for frame
		int width = 350;
		int height = 250;
		setResizable(false);
		setLayout(null); // layout for my frame

		setBounds(400, 400, width, height); // set the size of the frame

		displayPanel = new JPanel(); // new panel for components
		displayPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		// set the size and position of the displayPanel panel
		displayPanel.setBounds(0, 0, width, height - 100);

		lblAccType = new JLabel("Account Type:");

		// fill out account combo-box
		String accType[] = { "GIC", "SAV" };
		cmbAccType = new JComboBox<String>(accType);
		cmbAccType.setSize(150, 10);
		cmbAccType.setBackground(Color.WHITE);

		lblTranType = new JLabel("Tranaction Type:");

		String tranType[] = { "Deposit", "Withdraw" };

		cmbTranType = new JComboBox<String>(tranType);
		cmbTranType.setSize(150, 10);
		cmbTranType.setBackground(Color.WHITE);

		lblAmount = new JLabel("Transaction Amount:");
		txtTranAmt = new JTextField("", 10);

		lblTranCount = new JLabel("Total Transactions Count:");
		txtTranCount = new JTextField("0", 15);
		txtTranCount.setEditable(false);
		txtTranCount.setBackground(BankGUI.bktxtColor);

		lblStartBal = new JLabel("Start Bal:");
		txtStartBal = new JTextField("0", 8);
		txtStartBal.setEditable(false);
		txtStartBal.setBackground(BankGUI.bktxtColor);

		lblEndBal = new JLabel("End Bal:");
		txtEndBal = new JTextField("0", 8);
		txtEndBal.setEditable(false);
		txtEndBal.setBackground(BankGUI.bktxtColor);

		// buttons
		btnAdd = new JButton("Add");
		btnSave = new JButton("Save");
		btnList = new JButton("List");
		btnSort = new JButton("Sort");
		btnClose = new JButton("Close");

		displayPanel.add(lblAccType);
		displayPanel.add(cmbAccType);
		displayPanel.add(lblTranType);
		displayPanel.add(cmbTranType);
		displayPanel.add(lblAmount);
		displayPanel.add(txtTranAmt);
		displayPanel.add(lblTranCount);
		displayPanel.add(txtTranCount);

		displayPanel.add(lblStartBal);
		displayPanel.add(txtStartBal);
		displayPanel.add(lblEndBal);
		displayPanel.add(txtEndBal);

		controlPanel = new JPanel();
		controlPanel.setBounds(0, height - 100, width, 100);

		controlPanel.add(btnAdd);
		controlPanel.add(btnSave);
		controlPanel.add(btnList);
		controlPanel.add(btnSort);
		controlPanel.add(btnClose);

		btnAdd.addActionListener(this);
		btnSave.addActionListener(this);
		btnList.addActionListener(this);
		btnSort.addActionListener(this);
		btnClose.addActionListener(this);

		cmbAccType.addActionListener(this);
		cmbTranType.addActionListener(this);

		// add panel to frame add(panel);
		add(displayPanel);
		add(controlPanel);

		// show the frame
		setVisible(true);

	}

	// displayList method
	public void displayList(String heading) {
		JScrollPane scrolltxt;
		JFrame frame = new JFrame(heading);
		frame.setBounds(400, 400, 400, 400);
		// Define font for output file
		Font font = new Font("Courier", Font.PLAIN, 12);
		// light green color for text field
		// Color bkColor=new Color(188,252,203);

		// use formattedString method to format output.
		JTextArea area = new JTextArea(transList.formattedString());
		area.setFont(font);
		area.setBounds(0, 0, 375, 400);
		area.setEditable(false);

		scrolltxt = new JScrollPane(area);
		area.setBackground(BankGUI.bktxtColor);
		scrolltxt.setBounds(0, 0, 375, 400);

		frame.add(scrolltxt);
		frame.setSize(400, 400);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	// perform actions
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) { // if btnAdd click
			// Validate amount
			if (Utility.validAmount(txtTranAmt.getText())) {

				// Transaction Type:Deposit/Withdraw
				String tranType = cmbTranType.getSelectedItem().toString();

				// Convert tranAmt to Double

				double tranAmt = Double.parseDouble(txtTranAmt.getText());

				// Account Type
				char accountType;
				// if GIC
				if (cmbAccType.getSelectedItem().toString().equals("GIC")) {

					// set account type
					accountType = 'g';
					recGIC.setAccountType(accountType);

					// Deposit
					if (tranType.equals("Deposit")) {
						recGIC.depositAcc(tranAmt);
						recGIC.setTransactionType("Deposit");
						// update txtTranCount
						txtTranCount.setText(String.valueOf(Integer.parseInt(txtTranCount.getText()) + 1));
						// assign values and insert into list
						TransactionRecord tr = new TransactionRecord();
						transList.insertMethod(tr.stringToObj(recGIC.toString()));

					} else {// Withdraw
						if (recGIC.withdrawAcc(tranAmt)) {
							recGIC.setTransactionType("Withdraw");
							// update txtTranCount
							txtTranCount.setText(String.valueOf(Integer.parseInt(txtTranCount.getText()) + 1));
							// assign values and insert into list
							TransactionRecord tr = new TransactionRecord();
							transList.insertMethod(tr.stringToObj(recGIC.toString()));

						} else {
							JOptionPane.showMessageDialog(null, "Cannot withdraw requested amount", "Validation",
									JOptionPane.PLAIN_MESSAGE);
						}
					}

					txtStartBal.setText(String.valueOf(recGIC.getStartBal()));
					txtEndBal.setText(String.valueOf(recGIC.getEndBal()));

					// System.out.println(recGIC.toString());
					// end of GIC check
				} else { // if SAV
					// set account type
					accountType = 's';

					recSav.setAccountType(accountType);
					// Deposit
					if (tranType.equals("Deposit")) {
						// set type to deposit and deposit amount
						recSav.setTransactionType("Deposit");
						recSav.depositAcc(tranAmt);
						// update txtTranCount
						txtTranCount.setText(String.valueOf(Integer.parseInt(txtTranCount.getText()) + 1));
						// assign values and insert into list
						TransactionRecord tr = new TransactionRecord();
						transList.insertMethod(tr.stringToObj(recSav.toString()));

					} else {// Withdraw
						if (recSav.withdrawAcc(tranAmt)) {
							// update txtTranCount
							recSav.setTransactionType("Withdraw");
							txtTranCount.setText(String.valueOf(Integer.parseInt(txtTranCount.getText()) + 1));
							// assign values and insert into list
							TransactionRecord tr = new TransactionRecord();
							transList.insertMethod(tr.stringToObj(recSav.toString()));

						} else {
							JOptionPane.showMessageDialog(null, "Insufficient Funds", "Validation",
									JOptionPane.PLAIN_MESSAGE);
						}
					}

					// changing current balances
					txtStartBal.setText(String.valueOf(recSav.getStartBal()));
					txtEndBal.setText(String.valueOf(recSav.getEndBal()));

				}

			} else { // Not Valid Amount
				JOptionPane.showMessageDialog(null, "Invalid amount entered", "Validation", JOptionPane.PLAIN_MESSAGE);
			}

			// btnSave
		} else if (e.getSource() == btnSave) {
			// saves transactions to TransactionList.txt file
			Utility.saveFile("TransactionList.txt", transList.toString(), false);

			// btnList
		} else if (e.getSource() == btnList) {
			// display results
			displayList("Transaction List");

			// btnSort
		} else if (e.getSource() == btnSort) {
			if (transList.getSize() > 1) {
				// sort records according to transaction amount
				transList.insertionSort(transList.getList());
				// display results
				displayList("Transaction List Sorted : Ascending Order");
			} else {
				// if there are less than 2 records before user clicks sort
				JOptionPane.showMessageDialog(null, "Must have a minimum of 2 records to sort", "Validation",
						JOptionPane.PLAIN_MESSAGE);
			}
			// btnClose
		} else if (e.getSource() == btnClose) {
			dispose(); // close program
		}

	}

	// default constructor
	public TransListGUI() {
		super("Transaction List Operations");
		recGIC = new TransactionRecord();// create new transaction records - GIC
		recSav = new TransactionRecord();// create new transaction records - SAV
		transList = new TransactionList();

	}

	public static void main(String[] args) {
		// create customer object
		TransListGUI tranGUI = new TransListGUI();
		// display user interface
		tranGUI.showDisplay();

	}
}
