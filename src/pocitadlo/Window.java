package pocitadlo;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -864293290455869123L;

	public Window() throws IOException {
		// TODO Auto-generated constructor stub
		double deviceScale = GraphicsEnvironment
				.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice()
				.getDefaultConfiguration()
				.getDefaultTransform()
				.getScaleX();
		int trueWidth = (int) (Toolkit
				.getDefaultToolkit()
				.getScreenSize()
				.getWidth() / deviceScale / 2);
		int trueHeight = (int) (Toolkit
				.getDefaultToolkit()
				.getScreenSize()
				.getHeight() / deviceScale / 2);
		
		BufferedImage icon = ImageIO.read(new File(Pocitadlo.path + Pocitadlo.iconName));
		
		setIconImage(icon);
		setSize(trueWidth, trueHeight);
		setTitle("Počítadlo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(160, 160));
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) throws IOException {
		new Pocitadlo();
	}
}
