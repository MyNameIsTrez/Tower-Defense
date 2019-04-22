package data;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon {

	private float x, y, cooldown, rateOfFire;
	private int width, height, damage;
	private Texture baseTexture, cannonTexture;
	private Tile startTile;
	private ArrayList<Projectile> projectiles;
	
	public TowerCannon(Texture baseTexture, Tile startTile) {
		this.baseTexture = baseTexture;
		this.cannonTexture = QuickLoad("cannonGun");
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = startTile.getWidth();
		this.height = startTile.getHeight();
		this.damage = 10;
		this.rateOfFire = 30;
		this.cooldown = 0;
		this.projectiles = new ArrayList<Projectile>();
	}
	
	private void Shoot() {
		projectiles.add(new Projectile(QuickLoad("bullet"), x + 32, y + 32, 5, 10));
	}
	
	public void Update() {
		cooldown -= Delta();
		if (cooldown <= 0) {
			Shoot();
			cooldown = rateOfFire;
		}

		for (Projectile p : projectiles) {
			p.Update();
		}
		
		Draw();
	}
	
	public void Draw() {
		DrawQuadTex(baseTexture, x, y, width, height);
		DrawQuadTexRot(cannonTexture, x, y, width, height, 120);
	}
	
}
