import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Coin extends Actor {
	public Coin() {
		GreenfootImage img = new GreenfootImage("coin.png");
		img.scale(80, 80);
		setImage(img);
	}
	public void act() {
		if (!((RunnerWorld) getWorld()).isGameOver()) setLocation(getX() - ((RunnerWorld) getWorld()).getObjectSpeed(), getY());
		if (getX() <= 0) getWorld().removeObject(this);
	}
}
