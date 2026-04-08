import greenfoot.Greenfoot;
import greenfoot.World;

public class RunnerWorld extends World {
	public int objectSpeed = 8;
	public int ticks = 1;
	public boolean gameOver = false;
	private int nextObject = 1;
	private int score = 0;
	private int level = 0;
	public RunnerWorld() {
		super(1500, 700, 1);
		setBackground("bg.png");
		addObject( new Runner(), 100, 300);
		showText( "Score: " + score, 1300, 10);
		showText( "Level: " + level, 1200, 10);

	}

	@Override
	public void act() {
		if (ticks == nextObject) {
			if (Math.random() > getThreshold()) spawnCoin();
			else spawnObstacle();
			nextObject = ticks + Utils.getRandomInt(24 - ((int) objectSpeed/2), 104 - ((int) objectSpeed/2));
		}

		if (ticks % 1000 == 0) level++;
		showText( "Level: " + level, 1200, 10);

		ticks++;
	}

	private void gameOver() {
		gameOver = true;
		Greenfoot.playSound( "over.mp3");
	}

	private void spawnCoin() {
		addObject( new Coin(), 1500, Math.random() > 0.5 ? 300 : 175);
	}

	private void spawnObstacle() {
		addObject( new Obstacle(Math.random() > 0.5 ? 80 : 160), 1500, Math.random() > 0.5 ? 300 : 175);
	}

	private double getThreshold() {
		return switch (level) {
			case 0 -> 0.2;
			case 1 -> 0.3;
			case 2 -> 0.4;
			case 3 -> 0.5;
			case 4 -> 0.6;
			default -> 0.7;
		};
	}

	public void addScore(int points) {
		score += points;
		showText( "Score: " + score, 1300, 10);
	}

	public void removeScore(int points) {
		score -= points;
		showText( "Score: " + score, 1300, 10);
		if (score < 0) gameOver();
	}

	public int getScore() {
		return score;
	}

	public int getObjectSpeed() {
		return objectSpeed + level;
	}

	public boolean isGameOver() {
		return gameOver;
	}
}
