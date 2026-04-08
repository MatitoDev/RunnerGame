import greenfoot.*;

public class Runner extends Actor {
	private static final int JUMP_STRENGTH = 22;
	private static final int GRAVITY = 1;
	private static final int GROUND_Y = 300;

	private int velocityY = 0;
	private boolean onGround = true;

	private final GreenfootImage runImage1;
	private final GreenfootImage runImage2;
	private final GreenfootImage jumpImage;

	private int animationCounter = 0;
	private boolean animationIndicator = true;

	public Runner() {
		runImage1 = new GreenfootImage("runner1.png");
		runImage1.scale(80, 110);

		runImage2 = new GreenfootImage("runner2.png");
		runImage2.scale(80, 110);

		jumpImage = new GreenfootImage("runner_jump.png");
		jumpImage.scale(80, 110);

		setImage(runImage1);
	}

	@Override
	public void act() {
		jump();
		applyGravity();
		updateAnimation();

		checkCollision();
	}

	//steuerung
	private void jump() {
		if ((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("space") || Greenfoot.mousePressed(null) ) && onGround) {
			velocityY = -JUMP_STRENGTH;
			onGround = false;
		}
	}

	private void applyGravity() {
		if (!onGround) {
			setLocation(getX(), getY() + velocityY);
			velocityY += GRAVITY;

			if (getY() >= GROUND_Y) {
				setLocation(getX(), GROUND_Y);
				velocityY = 0;
				onGround = true;
			}
		}
	}

	private void updateAnimation() {
		if (onGround) {
			runAnimation();
		} else {
			setImage(jumpImage);
		}
	}
	private void runAnimation() {
		animationCounter++;
		if (animationCounter % 7 == 0) {
			if (animationIndicator) setImage(runImage1);
			else setImage(runImage2);
			animationIndicator = !animationIndicator;
		}
	}

	private void checkCollision() {
		if (isTouching(Coin.class)) {
			Greenfoot.playSound("pop.mp3");
			removeTouching(Coin.class);
			((RunnerWorld) getWorld()).addScore(1); //points für Münzen
		} else if (isTouching(Obstacle.class)) {
			Greenfoot.playSound("false.mp3");
			((RunnerWorld) getWorld()).removeScore(((Obstacle) getOneIntersectingObject(Obstacle.class)).getWidth() > 90 ? 20 : 10); //-20 Punkte bei großen umd -10 bei kleinem Hindernis
			removeTouching(Obstacle.class);
		}
	}
}