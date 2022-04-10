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
*   Description : Sign up GUI class
*   
*   
*   Method List:
*   	displaySignUp - Method used to create UI layout (buttons, size, etc). Does not pass in or return anything.
* 
* 
*   	actionPerformed - Method used to handle buttons in UI.  
*   
*   Constructor(s):
*   	SignInGUI() - Default constructor used for header in Main UI
*   
 */
public class SignUpGUI extends JFrame implements ActionListener {
	// private variables for sign up class
	private JPanel panel;
	private JLabel lblLoginID, lblPassword, lblUserType;
	private JTextField txtLoginID;
	private JPasswordField txtPassword;
	private JButton btnSave, btnClose;
	private JComboBox<String> cmbUserType;

	public void displaySignUp() {
		setBounds(400, 400, 350, 150);
		panel = new JPanel();

		// setting up components
		lblLoginID = new JLabel("Login ID:");
		lblPassword = new JLabel("Password");
		txtLoginID = new JTextField("", 20);
		txtPassword = new JPasswordField("", 20);
		lblUserType = new JLabel("User Type:");

		String userType[] = { "Customer", "Employee" };
		cmbUserType = new JComboBox<String>(userType);

		btnSave = new JButton("Save");
		btnClose = new JButton("Close");

		// adding components to panel
		panel.add(lblLoginID);
		panel.add(txtLoginID);
		panel.add(lblPassword);
		panel.add(txtPassword);
		panel.add(lblUserType);
		panel.add(cmbUserType);
		panel.add(btnSave);
		panel.add(btnClose);

		panel.setBounds(0, 0, 250, 100);
		add(panel); // adding panel to JFrame

		btnClose.addActionListener(this);
		btnSave.addActionListener(this);
		// show frame
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSave) {
			String passText = new String(txtPassword.getPassword());
			// validate user input
			if (txtLoginID.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter Login ID", "Input Required",
						JOptionPane.PLAIN_MESSAGE);
				// if password is empty
			} else if (passText.equals("")) {
				JOptionPane.showMessageDialog(null, "Please enter Password", "Input Required",
						JOptionPane.PLAIN_MESSAGE);

				// if user input is valid write to file
			} else {
				if (Utility.dupRecord("Login.txt", 1, txtLoginID.getText())) {
					JOptionPane.showMessageDialog(null, "Login ID already exists. Please try with a new one");
				} else {
					Utility.saveFile("Login.txt", txtLoginID.getText() + "|" + Utility.encode(passText) + "|"
							+ cmbUserType.getSelectedItem().toString(), true);
					JOptionPane.showMessageDialog(null, "Record saved.", "User Login", JOptionPane.PLAIN_MESSAGE);
					dispose();
				}
			}
		} else if (e.getSource() == btnClose) {
			dispose(); // close
		}
	}

	public SignUpGUI() {
		super("User Sign-Up");
	}

	// self testing main method
	public static void main(String[] args) {
		SignUpGUI gui = new SignUpGUI();
		gui.displaySignUp();
	}

}
