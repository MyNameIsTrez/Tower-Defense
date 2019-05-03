package data;

import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum ProjectileType {

	Cannonball(quickLoad("cannonball"), 10, 500), Iceball(quickLoad("iceball"), 6, 350);

	Texture texture;
	int damage;
	float speed;

	ProjectileType(Texture texture, int damage, float speed) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}

}
