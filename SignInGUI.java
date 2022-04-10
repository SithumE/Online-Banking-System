import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 *  Author : Sithum Egodawatte
 *  Date :June 11, 2020
*   Description : Sign in GUI class
*   
*   
*   Method List:
*   	displaySignIn - Method used to create UI layout (buttons, size, etc). Does not pass in or return anything.
*   
*   	setUser - Method used to store the user details (loginID, password, userType) which is used throughout the application.
*   	
*   	actionPerformed - Method used to handle buttons in UI.   
*   
*   Constructor(s):
*   	SignInGUI() - Default constructor used for header in Main UI
*   	
*   
 */
public class SignInGUI extends JFrame implements ActionListener {
	// private variables for sign in class
	private JPanel panel;
	private JLabel lblLoginID, lblPassword;
	private JTextField txtLoginID;
	private JPasswordField txtPassword;
	private JButton btnLogin, btnClose;
	private String userID, userPassword, userType;

	public void displaySignIn() {
		setBounds(400, 400, 350, 150);
		panel = new JPanel();

		// setting up components
		lblLoginID = new JLabel("Login ID:");
		lblPassword = new JLabel("Password");
		txtLoginID = new JTextField("", 20);
		txtPassword = new JPasswordField("", 20);

		String userType[] = { "Customer", "Employee" };

		btnLogin = new JButton("Login");
		btnClose = new JButton("Close");

		// adding components to panel
		panel.add(lblLoginID);
		panel.add(txtLoginID);
		panel.add(lblPassword);
		panel.add(txtPassword);
		panel.add(btnLogin);
		panel.add(btnClose);

		panel.setBounds(0, 0, 250, 100);
		add(panel); // adding panel to JFrame

		btnClose.addActionListener(this);
		btnLogin.addActionListener(this);
		// show frame
		setVisible(true);

	}

	// method to set user with login details
	public static void setUser(String userID, String userPassword, String userType) {
		BankGUI.userID = userID;
		BankGUI.userPWD = userPassword;
		BankGUI.userType = userType;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			String passText = new String(txtPassword.getPassword());
			// validate user input
			if (txtLoginID.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter Login ID", "Input Required",
						JOptionPane.PLAIN_MESSAGE);
				// if password is empty
			} else if (passText.equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter Password", "Input Required",
						JOptionPane.PLAIN_MESSAGE);

			} else {
				// validate credentials
				String rec = Utility.getRecord("Login.txt", 1, txtLoginID.getText());
				// if login is incorrect
				if (rec.equals("")) {
					JOptionPane.showMessageDialog(null, "Invalid Login. Please try with valid User ID", "User Login",
							JOptionPane.PLAIN_MESSAGE);
				} else {
					// else if login is found in Login.txt file
					// split the record into array
					String recArray[] = rec.split("\\|");
					// password is second element
					String strPass = recArray[1];

					// decode the password to see if it matches Login.txt file
					if (passText.equals(Utility.decode(strPass))) {
						// assign variables to array elements
						userID = recArray[0];
						userPassword = recArray[1];
						userType = recArray[2];

						// assign credentials to BankGUI (main interface)
						setUser(userID, userPassword, userType);
						JOptionPane.showMessageDialog(null, "Login Successful.", "User Login",
								JOptionPane.PLAIN_MESSAGE);
						BankGUI bank = new BankGUI();
						bank.setWindow();
					}
				}

			}
		} else if (e.getSource() == btnClose) {
			dispose(); // close
		}
	}

	/*
	 * default constructor
	 */
	public SignInGUI() {
		super("User Sign-In");
	}

	// self testing main method
	public static void main(String[] args) {
		SignInGUI gui = new SignInGUI();
		gui.displaySignIn();
	}

}
