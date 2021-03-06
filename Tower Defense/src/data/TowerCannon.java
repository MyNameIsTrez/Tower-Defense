package data;

import java.util.concurrent.CopyOnWriteArrayList;
import static helpers.Artist.TILE_SIZE;

public class TowerCannon extends Tower {

	public TowerCannon(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemyList) {
		super(type, startTile, enemyList);
	}

	@Override
	public void shoot(Enemy target) {
		super.projectiles.add(new ProjectileCannonball(super.type.projectileType, super.target, super.getX() + TILE_SIZE / 4, super.getY() + TILE_SIZE / 4, TILE_SIZE / 2, TILE_SIZE / 2));
	}

}
