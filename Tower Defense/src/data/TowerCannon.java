package data;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon {

	private float x, y, timeSinceLastShot, rateOfFire, angle;
	private int width, height, damage;
	private Texture baseTexture, cannonTexture;
	private Tile startTile;
	private ArrayList<Projectile> projectiles;
	private ArrayList<Enemy> enemies;
	private Enemy target;
	
	public TowerCannon(Texture baseTexture, Tile startTile, int damage, ArrayList<Enemy> enemies) {
		this.baseTexture = baseTexture;
		this.cannonTexture = quickLoad("cannonGun");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.damage = damage;
		this.rateOfFire = 3;
		this.timeSinceLastShot = 0;
		this.projectiles = new ArrayList<Projectile>();
		this.enemies = enemies;
		this.target = acquireTarget();
		this.angle = calculateAngle();
	}
	
	private Enemy acquireTarget() {
		return enemies.get(0);
	}
	
	private float calculateAngle() {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;
	}
	
	private void shoot() {
		projectiles.add(new Projectile(quickLoad("bullet"), target, x + 32, y + 32, 900, 10));
	}
	
	public void update() {
		timeSinceLastShot += delta();
		if (timeSinceLastShot > rateOfFire) {
			shoot();
			timeSinceLastShot = 0;
		}

		for (Projectile p : projectiles)
			p.update();
		
		angle = calculateAngle();

		Draw();
	}
	
	public void Draw() {
		drawQuadTex(baseTexture, x, y, width, height);
		drawQuadTexRot(cannonTexture, x, y, width, height, angle);
	}
	
}
