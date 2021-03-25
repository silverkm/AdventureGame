import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel implements ActionListener {

	private JButton b1, b2, b3, b4;
	private JLabel label;
	private GridBagLayout layout;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public ControlPanel() {
		

		layout = new GridBagLayout();
		layout.columnWidths = new int[] {100,100,100,100,100,100,100,100};
		layout.rowHeights = new int[] {50};
		setLayout(layout);
		label = new JLabel("Player Name:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx=0.0;
		gbc.gridwidth=2;
		gbc.insets = new Insets(2,2,2,2);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		label.setAlignmentX(LEFT_ALIGNMENT);
		add(label, gbc);
		
		b1 = new JButton("North");
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.weightx=0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		b1.addActionListener(this);
		add(b1, gbc);
		
		b2 = new JButton("East");
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.weightx=0;
		
		b2.addActionListener(this);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(b2, gbc);
		
		b3 = new JButton("South");
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.weightx=0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		b3.addActionListener(this);
		add(b3, gbc);
		
		b4 = new JButton("West");
		gbc.gridx = 6;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx=0;
		b4.addActionListener(this);
		add(b4, gbc);
		disableControl();
		
	}
	public void setPlayerName(String s) {
		this.label.setText("Player Name: " + s);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			if (e.getSource() == b1)
				Main.gui.movePlayer('N');
			else if (e.getSource() == b2)
				Main.gui.movePlayer('E');
			else if (e.getSource() == b3)
				Main.gui.movePlayer('S');
			else if (e.getSource() == b4)
				Main.gui.movePlayer('W');

	}
	public void disableControl() {
		b1.setEnabled(false);
		b2.setEnabled(false);
		b3.setEnabled(false);
		b4.setEnabled(false);
	}
	public void enableControl() {
		b1.setEnabled(true);
		b2.setEnabled(true);
		b3.setEnabled(true);
		b4.setEnabled(true);
	}
	
}