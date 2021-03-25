import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class GUI {
	
	private ButtonPanel bPanel;
	private MainFrame mFrame;
	private TopPanel tPanel;
	private MapPanel mPanel;
	private ControlPanel cPanel;
	private ArrowKeyListener aListen;
	private Player player;
	private TreasureChest[] treasureChests;
	private int treasureCount;
	private int treasureFound;
	private int stage;
	
	
	private final String foundTreasureImage = "images/hero-found-treasure.png";
	private final String stageCompleteImage = "images/stageComplete.png";
	private final String levelUpImage = "images/levelUp.png";
	

	public GUI() {
		mFrame = new MainFrame();
		bPanel = new ButtonPanel();
		tPanel = new TopPanel();
		mPanel = new MapPanel();
		cPanel = new ControlPanel();
		
		aListen = new ArrowKeyListener();
		
		Container mainContainer = mFrame.getContentPane();
		mainContainer.setBackground(Color.GRAY);
		mFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(4,4,4,4, Color.BLACK));
		mFrame.setVisible(true);
		mFrame.add(tPanel, BorderLayout.NORTH);
		mFrame.add(bPanel, BorderLayout.WEST);
		mFrame.add(mPanel, BorderLayout.CENTER);
		mFrame.add(cPanel, BorderLayout.SOUTH);
		mFrame.pack();
	}
	
	public void startGame() {
		mPanel.clearMap();
		String name = JOptionPane.showInputDialog(mPanel,"Enter Player Name");
		this.player = new Player(name);
		cPanel.setPlayerName(name);
		cPanel.enableControl();
		mPanel.drawMap();
		initTreasure();
		mPanel.addFog();
		mPanel.setPlayerLocation(player.getPlayerLocationX(), player.getPlayerLocationY());
		stage=1;
		bPanel.updateStats(player);
		mPanel.addKeyListener(aListen);
		mPanel.requestFocus();
		
	}
	public void restartGame() {
		mPanel.addFog();
		cPanel.setPlayerName("");
		bPanel.clearStats();
		mPanel.removeKeyListener(aListen);
		player = null;
		treasureChests = null;
	}
	
	public void initTreasure() {
		final int MAX_TREASURE = 10;
		final int MIN_TREASURE = 5;
		final int rows = 11;
		final int cols = 11;
		int numberOfTreasure = (int)(Math.random() * (MAX_TREASURE - MIN_TREASURE + 1) + MIN_TREASURE);
		treasureCount = numberOfTreasure;
		treasureFound = 0;
		treasureChests = new TreasureChest[numberOfTreasure];
		for (int i=0; i < numberOfTreasure; i++) {
			int x, y;
			do {
				x = (int)(Math.random() * (cols+1));
				y = (int)(Math.random() * (rows+1));
			} while (isOverlap(x,y));
			treasureChests[i] = new TreasureChest(x,y);
			mPanel.drawTreasure(treasureChests[i]);
		}
	}
	
	public boolean isOverlap(int x, int y) {
		for (TreasureChest t : treasureChests) {
			if (t != null) {
				if (t.getLocationX() == x && t.getLocationY() == y)
					return true;
			}
		}
		if (x == player.getPlayerLocationX() && y == player.getPlayerLocationY())
			return true;
		
		return false;
	}

	private TreasureChest getFoundTreasure() {
		int playerX = player.getPlayerLocationX();
		int playerY = player.getPlayerLocationY();		
		for (TreasureChest t : treasureChests) {
			if (t != null) {
				if ((t.getLocationX() == playerX && t.getLocationY() == playerY) && t.getStatus().equalsIgnoreCase("closed"))
					return t;
			}
		}		
		return null;
	}
	
	public void foundTreasure(TreasureChest t) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource(foundTreasureImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(125, 125,
		        Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(dimg);
		JOptionPane.showMessageDialog(mPanel,"You found treasure!\nCoins: " + t.getCoins(), "Treasure Found",JOptionPane.INFORMATION_MESSAGE,icon);
		treasureFound++;
		player.addCoins(t.takeCoins());
		mPanel.drawTreasure(t);
		bPanel.updateStats(player);
		if (treasureFound >= treasureCount) {
			nextLevel();
		}
	}
	
	public String getPlayerImage() {
		return player.getPlayerImage();
	}
	
	public void movePlayer(char direction) {
		int currentX, currentY;
		currentX = this.player.getPlayerLocationX();
		currentY = this.player.getPlayerLocationY();
		if ((direction == 'N' || direction == 'n') && (currentY < 11)) {
			mPanel.setTraveledLocation(currentX, currentY);
			player.setPlayerLocationY(currentY+1);
			mPanel.setPlayerLocation(player.getPlayerLocationX(), player.getPlayerLocationY());
		}
		else if ((direction == 'E' || direction == 'e') && (currentX < 11)) {	
			mPanel.setTraveledLocation(currentX, currentY);
			player.setPlayerLocationX(currentX+1);
			mPanel.setPlayerLocation(player.getPlayerLocationX(), player.getPlayerLocationY());
		}
		else if ((direction == 'S' || direction == 's') && (currentY > 0)) {
			mPanel.setTraveledLocation(currentX, currentY);
			player.setPlayerLocationY(currentY-1);
			mPanel.setPlayerLocation(player.getPlayerLocationX(), player.getPlayerLocationY());
		}
		else if ((direction == 'W' || direction == 'w') && (currentX > 0)) {
			mPanel.setTraveledLocation(currentX, currentY);
			player.setPlayerLocationX(currentX-1);
			mPanel.setPlayerLocation(player.getPlayerLocationX(), player.getPlayerLocationY());
		}
		if (mPanel.containsTreasure(player.getPlayerLocationX(), player.getPlayerLocationY())) {
			TreasureChest t = getFoundTreasure(); 
			foundTreasure(t);
		}
		mPanel.requestFocus();
	}
	
	public int getPlayerHealth() {
		return player.getPlayerHealth();
	}
	private void nextLevel() {
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource(stageCompleteImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(150,150,
		        Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(dimg);
		JOptionPane.showMessageDialog(mPanel,"You Finished Stage: " + stage,  "Stage Complete",JOptionPane.INFORMATION_MESSAGE,icon);
		stage++;
		player.levelUp();
		img = null;
		try {
		    img = ImageIO.read(getClass().getResource(levelUpImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		dimg = img.getScaledInstance(125, 125,
		        Image.SCALE_SMOOTH);
		icon = new ImageIcon(dimg);
		JOptionPane.showMessageDialog(mPanel,"You Leveled Up!\nNow Level: " + player.getPlayerLevel(),  "Stage Complete",JOptionPane.INFORMATION_MESSAGE,icon);
		
		
		mPanel.clearMap();
		mPanel.drawMap();
		int x = (int)(Math.random() * 12);
		int y = (int)(Math.random() * 12);
		player.setPlayerLocation(x, y);
		treasureChests = null;
		initTreasure();
		mPanel.addFog();
		mPanel.setPlayerLocation(x, y);
		bPanel.updateStats(player);
		mPanel.requestFocus();
		
	}

	public int getStage() {
		// TODO Auto-generated method stub
		return stage;
	}
}
