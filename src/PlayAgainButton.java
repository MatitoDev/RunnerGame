import greenfoot.*;

public class PlayAgainButton extends Actor {
	public PlayAgainButton() {
		GreenfootImage img = new GreenfootImage("again_button.png");
		img.scale(333, 200);
		setImage(img);
	}

	@Override
	public void act() {
		if (Greenfoot.mouseClicked(this)) {
			Greenfoot.setWorld(new RunnerWorld());
		}
	}
}