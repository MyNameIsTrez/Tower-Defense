package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.TILE_SIZE;
import static helpers.Leveler.loadMap;
import static helpers.Leveler.saveMap;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Editor {

	private TileGrid grid;
	private int index;
	private TileType[] types;

	public Editor() {
//		this.grid = new TileGrid();
		this.grid = loadMap("newMap1");
		this.index = 0;

		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
	}

	public void update() {
		grid.draw();

//		Handle mouse input
		if (Mouse.isButtonDown(0)) {
			setTile();
		}

//		Handle keyboard input
		while (Keyboard.next()) { // if the key is pressed/released
			if (Keyboard.getEventKeyState()) { // if the key is pressed, do this once
				switch (Keyboard.getEventKey()) {
				case Keyboard.KEY_RIGHT:
					moveIndex();
					break;
				case Keyboard.KEY_S:
					saveMap("newMap1", grid);
					break;
				}
			}
		}
	}

	private void setTile() {
		grid.setTile((int) Mouse.getX() / TILE_SIZE, (int) (HEIGHT - Mouse.getY() - 1) / TILE_SIZE,
				types[index]);
	}

	private void moveIndex() {
		index++;
		if (index > types.length - 1) {
			index = 0;
		}
	}

}
