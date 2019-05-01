package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.quickLoad;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

public class Player {

	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private ArrayList<TowerCannon> towerList;
	private boolean leftMouseButtonDown;

	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.waveManager = waveManager;
		this.towerList = new ArrayList<TowerCannon>();
		this.leftMouseButtonDown = false;
	}

	public void update() {
		for (TowerCannon t : towerList) {
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
			t.update();
		}

//		Handle mouse input
		if (Mouse.isButtonDown(0) && !leftMouseButtonDown) {
			towerList.add(new TowerCannon(quickLoad("cannonBase"),
					grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE), 10, 1000,
					waveManager.getCurrentWave().getEnemyList()));
		}

		leftMouseButtonDown = Mouse.isButtonDown(0);

//		Handle keyboard input
		while (Keyboard.next()) { // if the key is pressed/released
			if (Keyboard.getEventKeyState()) { // if the key is pressed
				switch (Keyboard.getEventKey()) {
				case Keyboard.KEY_RIGHT:
					Clock.changeMultiplier(0.1f);
					break;
				case Keyboard.KEY_LEFT:
					Clock.changeMultiplier(-0.1f);
					break;
				case Keyboard.KEY_T:
					towerList.add(new TowerCannon(quickLoad("cannonBase"), grid.getTile(18, 9), 10, 1000,
							waveManager.getCurrentWave().getEnemyList()));
					break;
				}
			}
		}
	}

}
