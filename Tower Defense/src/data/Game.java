package data;

import static helpers.Artist.QuickLoad;

public class Game {
	
	private TileGrid grid;
	private Player player;
	private WaveManager waveManager;
	
//	Temporary Variables
	TowerCannon tower;
	
	public Game(int[][] map) {
		grid = new TileGrid(map);
		player = new Player(grid);
		waveManager = new WaveManager(new Enemy(QuickLoad("ufo"), grid.GetTile(10, 8), grid, 64, 64, 40),
				4, 5);
		
		tower = new TowerCannon(QuickLoad("cannonBase"), grid.GetTile(14, 7));
	}
		
	
	public void Update() {		
		grid.Draw();
		waveManager.update();
		player.Update();
		
		tower.Update();
	}

}
