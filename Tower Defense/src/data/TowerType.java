package data;

import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public enum TowerType {

	Cannon(new Texture[] { quickLoad("towerBaseCannon"), quickLoad("towerGunCannon") }, ProjectileType.Cannonball, 10,
			250, 1, 45),
	Ice(new Texture[] { quickLoad("towerBaseIce"), quickLoad("towerGunIce") }, ProjectileType.Iceball, 3, 350, 3, 105);

	Texture[] textures;
	ProjectileType projectileType;
	int damage, range;
	float firingSpeed;
	int cost;

	TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingSpeed, int cost) {
		this.textures = textures;
		this.projectileType = projectileType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}

}
