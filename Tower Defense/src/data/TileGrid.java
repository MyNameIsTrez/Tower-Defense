package data;

public class TileGrid {

	public Tile[][] map;
	private int tilesWide, tilesHigh;
	
	public TileGrid(int[][] newMap) {
		this.tilesWide = newMap[0].length;
		this.tilesHigh = newMap.length;
		map = new Tile[tilesWide][tilesHigh];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch(newMap[j][i]) { // swap the x-axis and y-axis
					case 0:
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Grass);
						break;
					case 1:
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Dirt);
						break;
					case 2:
						map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.Water);
						break;
				}
			}
		}
	}
	
	public void SetTile(int xCoord, int yCoord, TileType type) {
		map[xCoord][yCoord] = new Tile(xCoord * 64, yCoord * 64, 64, 64, type);
	}
	
	public Tile GetTile(int xTile, int yTile) {
		if (xTile >= 0 && yTile >= 0 && xTile < tilesWide && yTile < tilesHigh)
			return map[xTile][yTile];
		else
			return new Tile(0, 0, 0, 0, TileType.NULL);
	}
	
	public void Draw() {
		for (Tile[] row : map) {
			for (Tile tile : row) {
				tile.Draw();
			}
		}
	}

	public int getTilesWide() {
		return tilesWide;
	}

	public void setTilesWide(int tilesWide) {
		this.tilesWide = tilesWide;
	}

	public int getTilesHigh() {
		return tilesHigh;
	}

	public void setTilesHigh(int tilesHigh) {
		this.tilesHigh = tilesHigh;
	}	
}
