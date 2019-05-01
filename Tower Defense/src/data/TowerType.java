package data;

import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum TowerType {

	CannonRed(new Texture[] { quickLoad("cannonBase"), quickLoad("cannonGun") }, 10, 1000, 3),
	CannonBlue(new Texture[] { quickLoad("cannonBaseBlue"), quickLoad("cannonGunBlue") }, 30, 1000, 3);

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
