package data;

import static helpers.Artist.drawQuadTex;

import org.newdawn.slick.opengl.Texture;

public abstract class Tower implements Entity {

	private Texture texture;
	private float x, y;
	private int width, height, damage;
	private Enemy target;
	
	public Tower(Texture texture, float x, float y, int width, int height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void update() {
		
	}

	public void draw() {
		drawQuadTex(texture, x, y, width, height);
	}
	
}
