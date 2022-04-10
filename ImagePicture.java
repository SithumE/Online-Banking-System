import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author 629705
 *
 */
public class ImagePicture extends Picture {

	/*
	 * Private instance data
	 */
	private ImageIcon image;

	/**
	 * first constructor
	 */
	public ImagePicture(ImageIcon img) {
		// super(); // calls the Picture() constructor
		this.image = img;
		setMyWidth(img.getIconWidth());
		setMyHeight(img.getIconHeight());
		repaint();

	}

	/*
	 * Overloaded Constructor with x, y, and image
	 */
	public ImagePicture(ImageIcon img, int x, int y) {
		super(x, y, 0, 0, null, null);
		this.image = img;
		setMyWidth(img.getIconWidth());
		setMyHeight(img.getIconHeight());
		repaint();
	}

	/*
	 * Override the paint method in Picture class
	 */
	public void paint(Graphics g) {
		this.image.paintIcon(this, g, getxPos(), getyPos());
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(ImageIcon image) {
		this.image = image;
		repaint();
	}

	/**
	 * @param args self-testing
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Testing");

		f.setSize(400, 350);
		f.setVisible(true);

		// create an ImagePicture object
		ImagePicture p = new ImagePicture(new ImageIcon("galaxy.png"));
		f.add(p);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "Wait");
		p.setxPos(50);
		p.setyPos(200);

		JOptionPane.showMessageDialog(null, "Wait");
		ImagePicture p1 = new ImagePicture(new ImageIcon("r2d2_1.png"), 200, 50);
		f.add(p1);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null, "Wait");
		p1.setImage(new ImageIcon("minion.png"));

	}

}
