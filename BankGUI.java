import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 *  Author : Sithum Egodawatte
 *  Date :June 15, 2020
*   Description : BankGUI class used as a UI for users in software
*   
*   
*   Method List:
*   		setWindow - Method used to create UI layout (buttons, size, etc). Does not pass in or return anything.
*   
*   		actionPerformed - Method used to handle buttons in UI. 
*   
*   Construcor(s):
*   		BankGUI() - Default constructor used to set header of UI : "Bank User Interface"		
*   
*/
public class BankGUI extends JFrame implements ActionListener {

	private JPanel controlPanel, drawingPanel; // panels used
	private JButton btnCust, btnTranList, btnTrans, btnCheckBal, btnClose;
	private int width, height; // height and width of default frame
	public static String userID;
	public static String userPWD;
	public static String userType;
	private ImagePicture picBackground; // variable for Background Image

	// light green color for text field
	public static Color bktxtColor = new Color(188, 252, 203);

	public void setWindow() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// initialize the width of the frame
		width = 1000;
		height = 700;
		setResizable(false);
		setLayout(null);

		controlPanel = new JPanel(); // panel for buttons
		drawingPanel = new JPanel();

		drawingPanel.setBounds(0, 0, width, height - 100);
		drawingPanel.setLayout(null); //
		picBackground = new ImagePicture(new ImageIcon("bank.jpg"), 0, 0); // create the background image
		picBackground.setBounds(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight()); // set its boundaries

		drawingPanel.add(picBackground);

		// create buttons which go to control panel
		btnCust = new JButton("Customer Profile");
		btnCheckBal = new JButton("Account Info");
		btnTrans = new JButton("Deposit/Withdraw");
		btnTranList = new JButton("Transaction List");
		btnClose = new JButton("Close");

		// add butt0ns to action Listener
		btnCust.addActionListener(this);
		btnTranList.addActionListener(this);
		btnTrans.addActionListener(this);
		btnCheckBal.addActionListener(this);
		btnClose.addActionListener(this);

		// set the size and position of the controlPanel panel
		controlPanel.setBounds(0, 650, width, 50);

		// add components to panels
		controlPanel.add(btnCust);
		controlPanel.add(btnCheckBal);
		controlPanel.add(btnTrans);
		controlPanel.add(btnTranList);
		controlPanel.add(btnClose);

		// add the buttons, title and drawing panels to the frame
		add(drawingPanel);
		add(controlPanel);

		// set size and location of frame
		setSize(width, height + 50);
		setLocation(100, 100);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTrans) { // if btnTrans is clicked
			// Check Valid Customer
			if (Utility.validCustomer()) {
				// Check Valid Accounts
				if (Utility.validAccount()) {
					// display GUI
					TransactionGUI trans = new TransactionGUI();
					trans.showDisplay();
				}
			}
		} else if (e.getSource() == btnCheckBal) { // if btnCheckBal is clicked
			// Check Valid Customer
			if (Utility.validCustomer()) {
				// Check Valid Accounts
				if (Utility.validAccount()) {
					// display GUI
					CheckBalGUI checkBal = new CheckBalGUI();
					checkBal.showProfile();
				}
			}
		} else if (e.getSource() == btnCust) { // if btnCust is clicked
			// Check Valid Customer
			if (Utility.validCustomer()) {
				// show customer profile page
				CustomerGUI custGUI = new CustomerGUI();
				custGUI.showProfile();

			}
		} else if (e.getSource() == btnTranList) { // if btnTranList is clicked

			if (Utility.validEmployee()) {
				TransListGUI listGUI = new TransListGUI();
				listGUI.showDisplay();
			}

		} else if (e.getSource() == btnClose) { // if btnClose is clicked
			System.exit(0);
		}
	}

	/*
	 * default constructor
	 */
	public BankGUI() {
		super("Bank User Interface"); // title for the frame

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		BankGUI bank = new BankGUI();
		bank.setWindow();

	}

}
