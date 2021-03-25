import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MapPanel extends JPanel {

	private GridBagConstraints gbc = new GridBagConstraints();
	private GridBagLayout layout;
	private final int NUM_ROWS = 12;
	private final int NUM_COLS = 12;
	private final int HEIGHT = 72;
	private final int WIDTH = 72;
	
	
	private MapSquare[][] squares;
	
	public MapPanel() {
		
		this.setBorder(new LineBorder(Color.BLACK, 3));
		layout = new GridBagLayout();
		layout.columnWidths = new int[] {WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH, WIDTH,WIDTH, WIDTH, WIDTH};
		layout.rowHeights = new int[] {HEIGHT,HEIGHT,HEIGHT,HEIGHT,HEIGHT,HEIGHT,HEIGHT,HEIGHT,HEIGHT,HEIGHT,HEIGHT,HEIGHT};
		setLayout(layout);
		
		squares = new MapSquare[NUM_ROWS][NUM_COLS];
		gbc.fill = GridBagConstraints.BOTH;
		for(int i=0; i < NUM_ROWS; i++)  {
			gbc.gridy = NUM_ROWS-1-i;
			for (int j=0; j< NUM_COLS; j++) {
				squares[i][j] = new MapSquare();
				
				gbc.gridx = j;
				this.add(squares[i][j], gbc);
			}
		}
	}
	public void setPlayerLocation(int x, int y) {
		squares[y][x].setPlayerImage();
		squares[y][x].removeFog();
	}
	
	public void setTraveledLocation(int x, int y) {
		squares[y][x].clearPlayerImage();
	}
	public void clearMap() {
		for(int i=0; i < NUM_ROWS; i++)  {
			for (int j=0; j< NUM_COLS; j++) {
				squares[i][j].clearContents();
			}
		}
	}
	public void drawMap() {
		for(int i=0; i < NUM_ROWS; i++)  {
			for (int j=0; j< NUM_COLS; j++) {
				squares[i][j].addGround();
			}
		}
	}
	public void addFog() {
		for(int i=0; i < NUM_ROWS; i++)  {
			for (int j=0; j< NUM_COLS; j++) {
				squares[i][j].addFog();
			}
		}
	}
	public void drawTreasure(TreasureChest t) {
		int i = t.getLocationY();
		int j = t.getLocationX();

		squares[i][j].setItemImage(t.getImage(), "treasure", t.getStatus());

	}
	public boolean containsTreasure(int x, int y) {
		return squares[y][x].containsTreasure();
	}
}