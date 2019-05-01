package data;

import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawQuadTexRot;
import static helpers.Artist.quickLoad;
import static helpers.Clock.delta;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon {

	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, damage, range;
	private Texture baseTexture, cannonTexture;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemyList;
	private Enemy target;
	private boolean targeted;

	public TowerCannon(Texture baseTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemyList) {
		this.baseTexture = baseTexture;
		this.damage = damage;
		this.range = range;
		this.enemyList = enemyList;

		this.cannonTexture = quickLoad("cannonGun");
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.firingSpeed = 1; // rounds/s
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.targeted = false;
	}

	private Enemy acquireTarget() {
		Enemy closest = null;
		float closestDistance = 10000; // impossibly big number
		for (Enemy e : enemyList)
			if (isInRange(e) && findDistance(e) < closestDistance && e.isAlive()) {
				closestDistance = findDistance(e);
				closest = e;
			}
		if (closest != null)
			targeted = true;
		return closest;
	}

	private boolean isInRange(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range)
			return true;
		return false;
	}

	private float findDistance(Enemy e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance; // Shouldn't this use Pythagorean theorem instead for the total pixels away the
										// enemy is from the tower?
	}

	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}

	private void shoot() {
//		The projectile gets centered on the tile with x + Game.TILE_SIZE / 4.
		projectiles.add(new Projectile(quickLoad("bulletIce"), target, x + TILE_SIZE / 4, y + TILE_SIZE / 4, TILE_SIZE / 2,
				TILE_SIZE / 2, 900, damage));
	}

	public void updateEnemyList(ArrayList<Enemy> newEnemyList) {
		enemyList = newEnemyList;
	}

	public void update() {
		if (target == null || target.isAlive() == false)
			targeted = false;

		if (!targeted) {
			target = acquireTarget();
		}

		timeSinceLastShot += delta();
		if (timeSinceLastShot > firingSpeed && target != null) {
			shoot();
			timeSinceLastShot = 0;
		}

		for (Projectile p : projectiles)
			p.update();

		if (target != null)
			angle = calculateAngle();

		draw();
	}

	public void draw() {
		drawQuadTex(baseTexture, x, y, width, height);
		drawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}

}
