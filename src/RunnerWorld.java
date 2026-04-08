import greenfoot.Greenfoot;
import greenfoot.World;

public class RunnerWorld extends World {
	public int objectSpeed = 8; //Speed der Objekte in Pixel pro Tick
	public int ticks = 1; //akteller Tick
	public boolean gameOver = false;
	private int nextObject = 1; //nächstes Objekt wird bei diesem Tick gespawned
	private int score = 0;
	private int highScore = 0;
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
			nextObject = ticks + (int) ((Math.random() * (104 - ((int) objectSpeed/2) - 24 - ((int) objectSpeed/2))) + 24 - ((int) objectSpeed/2)); //etwa alle 0.4 - 1.8 Sekunden ein neues Objekt (~60tps)
																											  										//wird schneller bei höherem level
		}

		if (ticks % 1000 == 0) level++; //alle 1000 ticks neues level
		showText( "Level: " + level, 1200, 10);

		if (!gameOver) ticks++;
	}

	private void gameOver() {
		gameOver = true;
		Greenfoot.playSound( "over.mp3");
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		addObject(new GameOverPanel(), centerX, centerY);

		showText("Level: " + level, centerX, centerY + 20);
		showText("Highest Score: " + highScore, centerX, centerY + 55);

		addObject(new PlayAgainButton(), centerX, centerY + 135);
	}

	private void spawnCoin() {
		addObject( new Coin(), 1500, Math.random() > 0.5 ? 300 : 175); //50% oben (175px) 50% unten (300px)
	}

	private void spawnObstacle() {
		addObject( new Obstacle(Math.random() > 0.5 ? 80 : 160), 1500, Math.random() > 0.5 ? 300 : 175);
								//50% große (160px) 50% kleine (80px) Hindernisse       50% oben (175px) 50% unten (300px)
	}

	private double getThreshold() { //Prozentsatz der Hindernisse
		return switch (level) {
			case 0 -> 0.2; //level 0 - 20% Hindernisse
			case 1 -> 0.3; //level 1 - 30% Hindernisse
			case 2 -> 0.4; //...
			case 3 -> 0.5;
			case 4 -> 0.6;
			default -> 0.7;
		};
	}

	public void addScore(int points) {
		score += points;
		showText( "Score: " + score, 1300, 10);
		if (score > highScore) highScore = score;
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
