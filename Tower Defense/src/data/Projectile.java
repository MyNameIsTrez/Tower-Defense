package data;

import static helpers.Artist.drawQuadTex;
import static helpers.Clock.delta;

import org.newdawn.slick.opengl.Texture;

public class Projectile {

	private Texture texture;
	private float x, y, speed;
	private int damage;

	public Projectile(Texture texture, float x, float y, float speed, int damage) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.damage = damage;
	}

	public void update() {
		x += delta() * speed;
		draw();
	}

	public void draw() {
		drawQuadTex(texture, x, y, 32, 32);
	}

}
