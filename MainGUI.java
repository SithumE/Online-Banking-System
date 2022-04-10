import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 *  Author : Sithum Egodawatte
 *  Date : June 16, 2020
*   Description : Main UI where user can sign up and sign in
*   
*   
*   Method List:
*   	setWindow - Method used to create UI layout (buttons, size, etc). Does not pass in or return anything.
*   	
*   	actionPerformed - Method used to handle buttons in UI.   
*   
*   Constructor(s):
*   	MainGUI() - Default constructor used for header in Main UI
*   	
*   	
*   
 */

public class MainGUI extends JFrame implements ActionListener {

	private JPanel controlPanel, drawingPanel; // panels used
	private JButton btnSignUP, btnSignIN, btnClose;
	private int width, height; // height and width of default frame
	private ImagePicture picBackground; // variable for Background Image
	BankGUI bankgui;

	public void setWindow() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// initialize the width of the frame
		width = 1000;
		height = 700;
		setResizable(false);
		setLayout(null); // layout for my frame

		controlPanel = new JPanel(); // panel for buttons
		drawingPanel = new JPanel();

		drawingPanel.setBounds(0, 0, width, height - 100);
		drawingPanel.setLayout(null); //
		picBackground = new ImagePicture(new ImageIcon("bank.jpg"), 0, 0); // create the background image
		picBackground.setBounds(0, 0, drawingPanel.getWidth(), drawingPanel.getHeight()); // set its boundaries

		drawingPanel.add(picBackground);

		// create buttons which goes to control panel
		btnSignUP = new JButton("Sign-up");
		btnSignIN = new JButton("Sign-in");
		btnClose = new JButton("Close");

		// add butt0ns to action Listener
		btnSignUP.addActionListener(this);
		btnSignIN.addActionListener(this);
		btnClose.addActionListener(this);

		// set the size and position of the controlPanel panel
		controlPanel.setBounds(0, 650, width, 50);

		// add components to panels
		controlPanel.add(btnSignUP);
		controlPanel.add(btnSignIN);
		controlPanel.add(btnClose);

		// add the button, title and drawing panels to the frame
		add(drawingPanel);
		add(controlPanel);

		// set size and location of frame
		setSize(width, height + 50);
		setLocation(100, 100);

		// show the frame visible to user
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSignUP) { // if btnSignUP is clicked
			SignUpGUI signUp = new SignUpGUI();
			signUp.displaySignUp(); // call displaySignUp method
		} else if (e.getSource() == btnSignIN) { // if btnSignIN is clicked
			SignInGUI signIn = new SignInGUI();
			signIn.displaySignIn(); // call displaySignIn method
		} else if (e.getSource() == btnClose) { // if btnClose is clicked
			System.exit(0);
		}
	}

	public MainGUI() {
		super("Login Account Creation"); // title for the frame

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		MainGUI main = new MainGUI();
		main.setWindow();
	}

}
