package data;

import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.quickLoad;

import java.util.ArrayList;

public class TowerIce extends Tower {

	public TowerIce(TowerType type, Tile startTile, ArrayList<Enemy> enemyList) {
		super(type, startTile, enemyList);
	}
	
	@Override
	public void shoot() {
//		The projectile gets centered on the tile with x + Game.TILE_SIZE / 4.
		projectiles.add(new ProjectileIceball(quickLoad("iceball"), target, x + TILE_SIZE / 4, y + TILE_SIZE / 4,
				TILE_SIZE / 2, TILE_SIZE / 2, 900, damage));
	}

}
