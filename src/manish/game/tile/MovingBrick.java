package manish.game.tile;

import java.awt.Graphics2D;
import java.util.Random;

import manish.game.Game;
import manish.game.Handler;
import manish.game.Id;
import manish.game.gfx.Sprite;

public class MovingBrick extends Tile{
	
	private Sprite sprite;
	
	public int direction; //0-left, 1-right;

	public MovingBrick(int x, int y, int width, int height, Id id, Handler handler, Sprite sprite) {
		super(x, y, width, height, id, handler);
		this.sprite = sprite;
		Random rand = new Random();
		direction = rand.nextInt(2);
		velX = 2;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(sprite.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
			if(x <= 0) {
				direction = 1;
				setVelX(3);
			}
			if(x >= Game.WIDTH - width) {
				direction = 0;
				setVelX(-3);
			}
			x += velX;
	}

}
