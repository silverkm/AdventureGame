
public class Player {
	private int[] location;
	private String playerName;
	private int playerHealth;
	private String playerImage;
	private int coins;
	private int level;
	
	public Player(String name) {
		this.setPlayerName(name);
		location = new int[2];
		this.setPlayerLocation(5, 0);
		this.setPlayerHealth(100);
		this.playerImage = "images/hero.png";
		coins = 0;
		level = 1;
		
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int[] getPlayerLocation() {
		return location;
	}
	public String getPlayerImage() {
		return playerImage;
	}
	public void setPlayerLocation(int x, int y) {
		location[1] = x;
		location[0] = y;
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}
	public void reduceHealth(int x) {
		this.playerHealth -= x;
	}
	public void increaseHealth(int x) {
		this.playerHealth += x;
	}
	public int getPlayerLocationX() {
		return location[1];
	}
	public int getPlayerLocationY() {
		return location[0];
	}
	public void setPlayerLocationX(int x) {
		location[1] = x;
	}
	public void setPlayerLocationY(int y) {
		location[0] = y;
	}
	public void addCoins(int c) {
		coins += c;
	}
	public int getCoins() {
		return coins;
	}

	public int getPlayerLevel() {
		return level;
	}
	public void levelUp() {
		level++;
	}
}
