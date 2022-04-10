import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author 629705 Creating my own JComponent
 *
 */
public class Picture extends JComponent {
	/*
	 * Private data
	 */
	private Color c;
	private int xPos, yPos, myWidth, myHeight;
	private String txtDisplay;

	/**
	 * default constructor
	 */
	public Picture() {
		this.c = Color.RED;
		this.xPos = 150;
		this.yPos = 50;
		this.myWidth = 100;
		this.myHeight = 50;
		repaint();

	}

	/*
	 * Overloading the constructor with one that allows for different color,
	 * position, and dimensions
	 */
	public Picture(int x, int y, int width, int height, Color c, String txtDisplay) {
		this.c = c;
		this.xPos = x;
		this.yPos = y;
		this.myWidth = width;
		this.myHeight = height;
		this.txtDisplay = txtDisplay;
		repaint();
	}

	/*
	 * Overloading the constructor with one that allows for different color,
	 * position, and dimensions
	 */
	public Picture(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}

	/*
	 * Overloading constructor for RGB colors
	 */
	public Picture(int x, int y, int width, int height) {
		this.xPos = x;
		this.yPos = y;
		this.myWidth = width;
		this.myHeight = height;
		Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		setC(r, g, b);
		repaint();
	}
	// Overriding the JComponent paint method
	// with my own paint method

	public void paint(Graphics g) {
		g.setColor(this.c);
		g.drawRect(this.xPos, this.yPos, this.myWidth, this.myHeight);
		g.drawOval(this.xPos, this.yPos, this.myWidth, this.myHeight);
		g.fillOval(this.xPos + this.myWidth / 4, this.yPos, this.myWidth / 2, this.myHeight);
		// g.drawString("hello", 100, 100);

	}

	/**
	 * @param c the c to set
	 */
	public void setC(Color c) {
		this.c = c;
		repaint();
	}

	/*
	 * Overload the setC method
	 */
	public void setC(int r, int g, int b) {
		this.c = new Color(r, g, b);
		repaint();
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
		repaint();
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
		repaint();
	}

	/**
	 * @param myWidth the myWidth to set
	 */
	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
		repaint();
	}

	/**
	 * @param myHeight the myHeight to set
	 */
	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
		repaint();
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return this.xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return this.yPos;
	}

	public int getMyWidth() {
		return this.myWidth;

	}

	public int getMyHeight() {
		return this.myHeight;
	}

	public Color getC() {
		return this.c;
	}

	/**
	 * @param args self testing main
	 */

	public static void main(String[] args) {
		JFrame f = new JFrame("Testing Frame");

		// create a picture object
		Picture p = new Picture();

		f.setSize(400, 350);
		f.add(p); // add picture to the frame
		f.setVisible(true);

		// make p1 move to the right

		// make p move
		for (int i = 0; i < 100; i++) {
			int y = p.getyPos();
			y = y + 1;
			p.setyPos(y);

		}

	}

}
