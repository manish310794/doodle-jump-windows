package manish.game.tile;

import java.util.Random;

import manish.game.Game;
import manish.game.Handler;
import manish.game.Id;
import manish.game.gfx.Sprite;

public class SlidingMonster extends Monster {
	
	private int direction; //0-left, 1-right

	public SlidingMonster(int x, int y, int width, int height, Id id, Handler handler, Sprite[] sprite) {
		super(x, y, width, height, id, handler, sprite);
		this.velX = 4;
		this.velY = 0;
		Random rand = new Random();
		direction = rand.nextInt(2);
	}
	
	@Override
	public void tick() {
		if(direction == 0) {
			velX = -4;
		}
		else {
			velX = 4;
		}
		
		x += velX;
		y += velY;
		
		if(x <= 0)
			direction = 1;
		else if(x >= Game.WIDTH - width)
			direction = 0;
	}

}
