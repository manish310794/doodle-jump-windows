package manish.game.tile;

import java.awt.Graphics2D;

import manish.game.Handler;
import manish.game.Id;
import manish.game.gfx.Sprite;

public class Spring extends Tile {
	
	private Sprite sprite;

	public Spring(int x, int y, int width, int height, Id id, Handler handler, Sprite sprite) {
		super(x, y, width, height, id, handler);
		this.sprite = sprite;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(sprite.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		
	}

}
