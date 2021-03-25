import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.LineBorder;

public class MapSquare extends JPanel {
	private JLabel fogLayer;
	private JLabel itemLayer;
	private JLabel playerLayer;
	private JLabel groundLayer;
	private String groundImg = "images/grasstile.png";
	private boolean containsTreasure;
	private boolean containsEnemy;
	
	public MapSquare() {
		setLayout(new OverlayLayout(this));
		fogLayer = new JLabel();
		itemLayer = new JLabel();
		playerLayer = new JLabel();
		groundLayer = new JLabel();
		
		fogLayer.setPreferredSize(new Dimension(73,73));
		itemLayer.setPreferredSize(new Dimension(73,73));
		playerLayer.setPreferredSize(new Dimension(65,65));
		groundLayer.setPreferredSize(new Dimension(73,73));
		
		containsTreasure = false;
		containsEnemy = false;
		
		this.add(fogLayer);
		this.add(playerLayer);
		this.add(itemLayer);
		this.add(groundLayer);

	}
	
	public void setItemImage(String location, String type, String status) {
		//ensure no image will be bigger then the label
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource(location));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(70, 70,
		        Image.SCALE_SMOOTH);
		itemLayer.setIcon(new ImageIcon(dimg));
		if (type.equalsIgnoreCase("treasure")) {
			if(status.equalsIgnoreCase("CLOSED"))
				containsTreasure = true;
			else
				containsTreasure = false;
		}
		else if(type.equalsIgnoreCase("enemy"))
			containsEnemy=true;
	}
	public void addGround() {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource(groundImg));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(73, 73,
		        Image.SCALE_SMOOTH);
		groundLayer.setIcon(new ImageIcon(dimg));
	}
	public void setPlayerImage() {
		String playerImage = Main.gui.getPlayerImage();
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource(playerImage));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(65, 65,
		        Image.SCALE_SMOOTH);
		playerLayer.setIcon(new ImageIcon(dimg));
	}
	public void clearPlayerImage() {
		playerLayer.setIcon(null);
	}
	
	public void clearContents() {
		fogLayer.setText(null);
		itemLayer.setIcon(null);
		playerLayer.setIcon(null);
		groundLayer.setIcon(null);
		fogLayer.setBackground(Color.GRAY);
	}
	public void addFog() {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource("images/fog.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(73, 73,
		        Image.SCALE_SMOOTH);
		fogLayer.setIcon(new ImageIcon(dimg));

	}
	public void removeFog() {
		fogLayer.setIcon(null);
	}
	public boolean containsTreasure() {
		return containsTreasure;
	}
	
	
}
