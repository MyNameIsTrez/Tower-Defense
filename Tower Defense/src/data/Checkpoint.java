package data;

public class Checkpoint {

	private Tile tile;
	private int xDirection, yDirection;
	
	public Checkpoint(Tile tile, int[] dir) {
		this.tile = tile;
		this.xDirection = dir[0];
		this.yDirection = dir[1];
	}

	public Tile getTile() {
		return tile;
	}

	public int getXDirection() {
		return xDirection;
	}

	public int getYDirection() {
		return yDirection;
	}
	
}
