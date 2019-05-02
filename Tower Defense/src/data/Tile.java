package data;

import static helpers.Artist.TILE_SIZE;
import static helpers.Artist.drawQuadTex;
import static helpers.Artist.quickLoad;

import org.newdawn.slick.opengl.Texture;

public class Tile {

	private int x, y, width, height;
	private Texture texture;
	private TileType type;

	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.width = TILE_SIZE;
		this.height = TILE_SIZE;
		this.type = type;
		this.texture = quickLoad(type.textureName);
	}

	public void draw() {
		drawQuadTex(texture, x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getXTile() {
		return x / TILE_SIZE;
	}

	public int getYTile() {
		return y / TILE_SIZE;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

}
