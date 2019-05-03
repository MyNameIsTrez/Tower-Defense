package data;

import static helpers.Artist.HEIGHT;
import static helpers.Artist.TILE_SIZE;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

public class Player {

	private TileGrid grid;
	private TileType[] types;
	private WaveManager waveManager;
	private CopyOnWriteArrayList<Tower> towerList;
	public static boolean leftMouseButtonDown = false, rightMouseButtonDown = false;
	public static int Cash, Lives;
	private TowerType selectedTowerType = TowerType.Cannon;

	public Player(TileGrid grid, WaveManager waveManager) {
		this.grid = grid;
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		this.waveManager = waveManager;
		this.towerList = new CopyOnWriteArrayList<Tower>();
	}

	public void setup() {
		Cash = 5000;
		Lives = 10;
	}

	public static boolean checkModifyCash(int amount) {
		if (Cash + amount >= 0) { // If you can afford it.
			return true;
		}
		System.out.println("You don't have $" + Math.abs(amount) + "!");
		return false;
	}

	public static void modifyCash(int amount) {
		Cash += amount;
		System.out.println("You have $" + Cash);
	}

	public static void modifyLives(int amount) {
		Lives += amount;
		if (Lives <= 0) {
			System.out.println("You've died!");
			System.exit(0);
		} else
			System.out.println("Lives left: " + Lives + ".");
	}

	public void update() {
		for (Tower t : towerList) {
			t.updateEnemyList(waveManager.getCurrentWave().getEnemyList());
			t.update();
			t.draw();
		}

//		Handle mouse input.
		Tile mouseClickedTile = grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE);

//		If the mouse is pressed at a buildable tile with no turret on it yet.
		if (mouseClickedTile.getType().buildable)
			if (!isTowerHere(mouseClickedTile.getX(), mouseClickedTile.getY())) {
				if (Mouse.isButtonDown(0) && !leftMouseButtonDown && checkModifyCash(-selectedTowerType.cost)) {
					modifyCash(-selectedTowerType.cost);
					switch (selectedTowerType) {
					case Cannon:
						towerList.add(new TowerCannon(TowerType.Cannon,
								grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
								waveManager.getCurrentWave().getEnemyList()));
						break;
					case Ice:
						towerList.add(new TowerIce(TowerType.Ice,
								grid.getTile(Mouse.getX() / TILE_SIZE, (HEIGHT - Mouse.getY() - 1) / TILE_SIZE),
								waveManager.getCurrentWave().getEnemyList()));
						break;
					}
				}
			} else if (Mouse.isButtonDown(1) && !rightMouseButtonDown)
				removeTowerFromList(mouseClickedTile.getX(), mouseClickedTile.getY());

		leftMouseButtonDown = Mouse.isButtonDown(0);
		rightMouseButtonDown = Mouse.isButtonDown(1);

//		Handle keyboard input.
		while (Keyboard.next()) { // If the key is pressed/released.
			if (Keyboard.getEventKeyState()) { // If the key is pressed.
				switch (Keyboard.getEventKey()) {
				case Keyboard.KEY_RIGHT:
					Clock.changeMultiplier(0.1f);
					break;
				case Keyboard.KEY_LEFT:
					Clock.changeMultiplier(-0.1f);
					break;
				case Keyboard.KEY_B: // bullet
					selectedTowerType = TowerType.Cannon;
					break;
				case Keyboard.KEY_I: // ice
					selectedTowerType = TowerType.Ice;
					break;
				}
			}
		}
	}

	public boolean isTowerHere(int xGrid, int yGrid) {
		for (Tower t : towerList)
			if (t.x == xGrid && t.y == yGrid)
				return true;
		return false;
	}

	public void removeTowerFromList(int xGrid, int yGrid) {
		for (Tower t : towerList)
			if (t.x == xGrid && t.y == yGrid) {
				towerList.remove(t);
			}
	}

}
