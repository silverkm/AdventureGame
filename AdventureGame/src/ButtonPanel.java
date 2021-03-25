import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel implements ActionListener {
	private JButton b1, b2, b3, b4;
	private GridBagConstraints gbc = new GridBagConstraints();
	private StatPanel sPanel;
	public ButtonPanel() {
		
		setLayout(new GridBagLayout());
		
		
		b1 = new JButton("New Game");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty=0.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		b1.addActionListener(this);
		add(b1, gbc);
		
		b2 = new JButton("Restart Game");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty=0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		b2.setEnabled(false);
		b2.addActionListener(this);
		add(b2, gbc);
		
		b3 = new JButton("Shop");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weighty=0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		b3.addActionListener(this);
		b3.setEnabled(false);
		add(b3, gbc);
		
		b4 = new JButton("Inventory");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty=0.0;
		b4.setEnabled(false);
		b4.addActionListener(this);
		add(b4, gbc);
		
		sPanel = new StatPanel();
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty=1;
		add(sPanel, gbc);
		

	}
	public void updateStats(Player p) {
		sPanel.updateStats(p);
	}
	public void clearStats() {
		sPanel.clearStats();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == b1) {
			
			Main.gui.startGame();
			b1.setEnabled(false);
			b2.setEnabled(true);
			b3.setEnabled(true);
			b4.setEnabled(true);
		}
		
		else if (e.getSource() == b2) {
			b1.setEnabled(true);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			Main.gui.restartGame();
		}
						
	}

}
