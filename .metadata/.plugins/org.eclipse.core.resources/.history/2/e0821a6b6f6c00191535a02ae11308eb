package data;

import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum TowerType {

	CannonBullet(new Texture[] { quickLoad("cannonBaseBullet"), quickLoad("cannonGunBullet") }, 10, 500, 1),
	CannonIce(new Texture[] { quickLoad("cannonBaseIce"), quickLoad("cannonGunIce") }, 3, 1000, 3);

	Texture[] textures;
	int damage, range;
	float firingSpeed;

	TowerType(Texture[] textures, int damage, int range, float firingSpeed) {
		this.textures = textures;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
	}

}
