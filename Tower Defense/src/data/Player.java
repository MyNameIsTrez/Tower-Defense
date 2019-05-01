package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

import static helpers.Artist.*;

import java.util.ArrayList;

public class Player {

	private TileGrid grid;
	private TileType[] types;
	private int index;
	private WaveManager waveManager;
	private ArrayList<TowerCannon> towerList;
	private boolean leftMouseButtonDown;

	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.index = 0;
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
					grid.getTile(Mouse.getX() / Game.TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / Game.TILE_SIZE), 10, 1000,
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
