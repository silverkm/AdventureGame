import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1000,1000));
		this.setResizable(false);
		this.setTitle("Zelda - Link to the Future");
		
		ImageIcon image = new ImageIcon(getClass().getResource("images/triforce.jpg"));
		
		this.setIconImage(image.getImage());
		
		
		
	}
}
