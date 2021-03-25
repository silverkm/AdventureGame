public class TreasureChest {
	private int[] location;
	private int coins;
	private Status status;
	public enum Status {
		EMPTY,
		FULL,
		CLOSED
	}
	private final String closedTreasureChestImage = "images/treasure-closed.png";
	private final String openTreasureChestImage = "images/treasure-full.png";
	private final String emptyTreasureChestImage = "images/treasure-empty.png";
	
	public TreasureChest(int x, int y) {
		final int MIN = 10;
		final int MAX = 100;
		location = new int[2];
		location[0] = y;
		location[1] = x;
		coins = (int)(Math.random() * (MAX-MIN+1) + MIN);
		status = Status.CLOSED;
	}
	public int takeCoins() {
		int temp = coins;
		coins = 0;
		status = Status.EMPTY;
		return temp;
	}
	public String getStatus() {
		return status.toString();
	}
	public void openTreasure() {
		status = Status.FULL;
	}
	public int getLocationX() {
		return location[1];
	}
	public int getLocationY() {
		return location[0];
	}
	public int[] getLocation() {
		return location;
	}
	public int getCoins() {
		return coins;
	}
	public String getImage() {
		switch (status) {
		case EMPTY:
			return emptyTreasureChestImage;
		case FULL:
			return openTreasureChestImage;
		case CLOSED:
			return closedTreasureChestImage;
		}
		return null;
	}

}
