import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatPanel extends JPanel {
	private JLabel health;
	private JLabel coins;
	private JLabel playerLevel;
	private JLabel stage;
	
	public StatPanel () {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		stage = new JLabel("Stage:");
		playerLevel = new JLabel("Player Level:");
		health = new JLabel("Health:");
		coins = new JLabel("Coins:");
		
		health.setAlignmentX(Component.LEFT_ALIGNMENT);
		coins.setAlignmentX(Component.LEFT_ALIGNMENT);
		playerLevel.setAlignmentX(Component.LEFT_ALIGNMENT);
		stage.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		add(stage);
		add(playerLevel);
		add(health);
		add(coins);
	}
	public void updateStats(Player p) {
		health.setText("Health: " + p.getPlayerHealth());
		coins.setText("Coins: " + p.getCoins()); 
		playerLevel.setText("Level: " + p.getPlayerLevel());
		stage.setText("Stage: " + Main.gui.getStage());
	}
	public void clearStats() {
		health.setText("");
		coins.setText("");
		playerLevel.setText("");
		stage.setText("");
	}
}
