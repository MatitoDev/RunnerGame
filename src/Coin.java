import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Coin extends Actor {
	private static final int SPEED = 8;
	public Coin() {
		GreenfootImage img = new GreenfootImage("coin.png");
		img.scale(80, 80);
		setImage(img);
	}
	public void act() {
		setLocation(getX() - SPEED, getY());
		if (getX() <= 0) {
			setLocation(1500, getY());
		}
	}

	public void spawn(){

	}

}
