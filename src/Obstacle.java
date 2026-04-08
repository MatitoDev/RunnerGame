import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class Obstacle extends Actor {
	public int width;
	public Obstacle(int w) {
		GreenfootImage img = new GreenfootImage("obstacle.png");
		img.scale(width = w, 80); //width = länge des Hindernisses
		setImage(img);
	}

	public void act() {
		if (!((RunnerWorld) getWorld()).isGameOver()) setLocation(getX() - ((RunnerWorld) getWorld()).getObjectSpeed(), getY());
		if (getX() <= 0) getWorld().removeObject(this);
	}

	public int getWidth() {
		return width;
	}
}
