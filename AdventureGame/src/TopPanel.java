import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TopPanel extends JPanel {
	private JLabel title;
	public TopPanel() {
		//this.setBorder(new LineBorder(Color.BLACK, 3));
		this.setBackground(Color.darkGray);
		title = new JLabel();
		title.setText("Adventure Game");
		title.setAlignmentX(Label.LEFT);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Serif", Font.PLAIN, 24));
		
		this.add(title);
	}
}
