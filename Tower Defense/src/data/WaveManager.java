package data;

import static data.Player.modifyCash;

public class WaveManager {
	
	private float timeBetweenEnemies;
	public static int waveNumber = 0;
	private int enemiesPerWave;
	private Enemy enemyType;
	private Wave currentWave;
	
	public WaveManager(Enemy enemyType, float timeBetweenEnemies, int enemiesPerWave) {
		this.enemyType = enemyType;
		this.timeBetweenEnemies = timeBetweenEnemies;
		this.enemiesPerWave = enemiesPerWave;
		
		this.currentWave = null;
		
		newWave();
	}
	
	public void update() {
		if (!currentWave.isCompleted())
			currentWave.update();
		else
			newWave();
	}
	
	private void newWave() {
		waveNumber++;
		currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave);
		modifyCash((waveNumber - 1) * 5);
		System.out.println("Beginning wave " + waveNumber + ".");
	}
	
	public Wave getCurrentWave() {
		return currentWave;
	}

}
