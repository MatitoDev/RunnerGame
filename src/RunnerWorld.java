import greenfoot.World;

public class RunnerWorld extends World {
	public int ticks = 0;
	private int nextCoin = 0;
	private int score = 0;
	public RunnerWorld() {
		super(1500, 700, 1);
		setBackground("bg.png");
		addObject( new Runner(), 100, 300);
		showText( "Score: " + score, 1300, 10);

	}

	@Override
	public void act() {
		if (ticks == nextCoin) {
			spawnCoin();
			nextCoin = ticks + Utils.getRandomInt(50, 150);
		}

		System.out.println(score);
		ticks++;
	}

	public void spawnCoin() {
		addObject( new Coin(), 1500, Math.random() > 0.5 ? 300 : 175);
	}

	public int getScore() {
		return score;
	}

	public void addScore(int points) {
		score += points;
		showText( "Score: " + score, 1300, 10);
	}
}
