package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

public class Enemy {

	private int width, height, health, currentCheckpoint;
	private float x, y, speed;
	private Texture texture;
	private Tile startTile;
	private boolean first = true;
	private TileGrid grid;
	
	private ArrayList<Checkpoint> checkpoints;
	private int[] directions;
	
	public Enemy(Texture texture, Tile startTile, TileGrid grid, int width, int height, float speed) {
		this.texture = texture;
		this.startTile = startTile;
		this.grid = grid;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int[2];
//		X direction
		this.directions[0] = 0;
//		Y direction
		this.directions[1] = 0;
		directions = FindNextDirections(startTile);
		this.currentCheckpoint = 0;
		PopulateCheckpointList();
	}
	
	public void Update() {
		if (first)
			first = false;
		else {
			if (CheckpointReached()) {
				if (currentCheckpoint + 1 == checkpoints.size())
//					The enemy reached the end of the maze.
					System.out.println("ENEMY REACHED THE END");
				else {
//					Go to the next checkpoint.
					currentCheckpoint++;
				}
			} else {
//				Move to the next checkpoint.
				x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
			}
		}
	}
	
	private boolean CheckpointReached() {
		boolean reached = false;
//		Get the tile of the current checkpoint.
		Tile t = checkpoints.get(currentCheckpoint).getTile();
//		If the enemy is within a box a little larger than the size of the checkpoint tile.
//		This should probably be rewritten, as I think it can cause bugs in edge cases (haha).
		if (x > t.getX() - 3 &&
				x < t.getX() + 3 &&
				y > t.getY() - 3 &&
				y < t.getY() + 3) {
			
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		
		return reached;
	}
	
	private void PopulateCheckpointList() {
//		Add the first checkpoint from the starting tile.
		checkpoints.add(FindNextCheckpoint(startTile, directions = FindNextDirections(startTile)));
		
		int counter = 0;
		boolean loop = true;
		while (loop) {
//			Look at the currentDirections from a checkpoint.
			int[] currentDirections = FindNextDirections(checkpoints.get(counter).getTile());
//			If the enemy has nowhere to go, stop adding checkpoints.
//			If 20 checkpoints are checked, stop. (20 is arbitrary)
			if (currentDirections[0] == 2 || counter == 20) {
				loop = false;
			} else { // Add the checkpoint.
//				This keeps track of a tile from a checkpoint, that's gotten using the counter.
				Tile currentCheckpointTile = checkpoints.get(counter).getTile();
//				Add the next checkpoint based on the currentCheckpointTile and the directions you can go from currentCheckpointTile.
				checkpoints.add(FindNextCheckpoint(currentCheckpointTile, directions = FindNextDirections(currentCheckpointTile)));
			}
			counter++;
		}
	}
	
	private Checkpoint FindNextCheckpoint(Tile start, int[] dir) {
		Tile next = null;
		Checkpoint checkpoint = null;
		
//		Boolean to decide if the next checkpoint is found.
		boolean found = false;
		
//		Integer to increment each loop.
		int counter = 1;
		
		while (!found) {
//			If the tile we're looking at is outside of the grid or if it's not the same type as the start tile.
			boolean outsideGridX = start.getXTile() + dir[0] * counter == grid.getTilesWide();
			boolean outsideGridY = start.getYTile() + dir[1] * counter == grid.getTilesHigh();
			boolean differentType = start.getType() != grid.GetTile(start.getXTile() + dir[0] * counter, start.getYTile() + dir[1] * counter).getType();
			if (outsideGridX ||	outsideGridY ||	differentType) {
				found = true;
//				Move the counter back, so we can look at the previous tile where we have to turn.
				counter--;
				next = grid.GetTile(start.getXTile() + dir[0] * counter,
						start.getYTile() + dir[1] * counter);
			}
			
			counter++;
		}
		
		checkpoint = new Checkpoint(next, dir);
		return checkpoint;
	}
	
	private int[] FindNextDirections(Tile start) {
		int[] dir = new int[2];
		
//		Get the tiles up, right, down and left of the start tile.
		Tile up = grid.GetTile(start.getXTile(), start.getYTile() - 1);
		Tile right = grid.GetTile(start.getXTile() + 1, start.getYTile());
		Tile down = grid.GetTile(start.getXTile(), start.getYTile() + 1);
		Tile left = grid.GetTile(start.getXTile() - 1, start.getYTile());

//		If we can go up and we're not currently going down, go up.
		if (start.getType() == up.getType() && directions[1] != 1) {
			dir[0] = 0;
			dir[1] = -1;
//		If we can go right and we're not currently going left, go right.
		} else if (start.getType() == right.getType() && directions[0] != -1) {
			dir[0] = 1;
			dir[1] = 0;
//		If we can down and we're not currently going up, go down.
		} else if (start.getType() == down.getType() && directions[1] != -1) {
			dir[0] = 0;
			dir[1] = 1;
//		If we can go left and we're not currently going right, go left.
		} else if (start.getType() == left.getType() && directions[0] != 1) {
			dir[0] = -1;
			dir[1] = 0;
		} else {
//			If the enemy has nowhere to go, make the directions have an impossible value.
			dir[0] = 2;
			dir[1] = 2;
		}
		
		return dir;
	}
	
	public void Draw() {
		DrawQuadTex(texture, x, y, width, height);
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}
	
	public TileGrid getTileGrid() {
		return grid;
	}
	
}
