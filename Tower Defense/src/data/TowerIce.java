package data;

import static helpers.Artist.TILE_SIZE;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerIce extends Tower {

	public TowerIce(TowerType type, Tile startTile, CopyOnWriteArrayList<Enemy> enemyList) {
		super(type, startTile, enemyList);
	}

	@Override
	public void shoot(Enemy target) {
		super.projectiles.add(new ProjectileIceball(super.type.projectileType, super.target,
				super.getX() + TILE_SIZE / 4, super.getY() + TILE_SIZE / 4, TILE_SIZE / 2, TILE_SIZE / 2));
	}

	@Override
	public void checkForTarget() {
//		Gets the fastest target and doesn't lock onto it.
		target = acquireTarget();
	}

	@Override
	public Enemy acquireTarget() {
//		Gets the fastest target that's in range.
		Enemy fastest = null;
		float highestSpeed = 0; // impossibly small number
		for (Enemy e : enemyList)
			if (super.isInRange(e) && e.getSpeed() > highestSpeed) {
				highestSpeed = e.getSpeed();
				fastest = e;
			}
		if (fastest != null)
			targeted = true;
		else
			targeted = false;
		return fastest;
	}

}
