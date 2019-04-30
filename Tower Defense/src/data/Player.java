package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static helpers.Artist.*;

public class Player {

	private TileGrid grid;
	private TileType[] types;
	private int index;
	
	public Player(TileGrid grid) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.index = 0;
	}
	
	public void SetTile() {
		grid.SetTile((int) Mouse.getX() / 64,
				(int) (HEIGHT - Mouse.getY() - 1) / 64, types[index]);
	}
	
	public void Update() {
		if (Mouse.isButtonDown(0)) {
			SetTile();
		}

		while (Keyboard.next()) { // if the key is pressed/released
			if (Keyboard.getEventKeyState()) { // if the key is pressed
				switch (Keyboard.getEventKey()) {
					case Keyboard.KEY_RIGHT:
						MoveIndex();
						break;
				}
			}
		}
	}
	
	private void MoveIndex() {
		index++;
		if (index == types.length - 1) {
			index = 0;
		}
	}
	
}