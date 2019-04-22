package data;

import java.util.ArrayList;
import static helpers.Clock.*;

public class Wave {

	private float cooldown, spawnTime;
	private Enemy enemyType;
	private ArrayList<Enemy> enemyList;

	public Wave(float spawnTime, Enemy enemyType) {
		this.spawnTime = spawnTime;
		this.enemyType = enemyType;
		cooldown = 0;
		enemyList = new ArrayList<Enemy>(); // make a new enemyList
	}

	public void Update() {
		cooldown -= Delta();
		if (cooldown <= 0) {
			Spawn();
			cooldown = spawnTime;
		}

		for (Enemy e : enemyList) {
			if (e.isAlive()) {
				e.Update();
				e.Draw();
			}
		}
	}

	public void Spawn() {
		enemyList.add(new Enemy(enemyType.getTexture(), enemyType.getStartTile(), enemyType.getTileGrid(), 64, 64,
				enemyType.getSpeed()));
	}

}
