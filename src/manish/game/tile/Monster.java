package manish.game.tile;

import java.awt.Graphics2D;

import manish.game.Handler;
import manish.game.Id;
import manish.game.gfx.Sprite;

public class Monster extends Tile {
	
	private Sprite[] sprite;
	private int index = 0;
	public int hits;

	public Monster(int x, int y, int width, int height, Id id, Handler handler, Sprite[] sprite) {
		super(x, y, width, height, id, handler);
		this.sprite = sprite;
		this.hits = 1;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(sprite[index].getBufferedImage(), x, y, width, height, null);
		if(++index == 3)
			index = 0;
	}

	@Override
	public void tick() {
		
	}
	
	public void setHits(int hits) {
		this.hits = hits;
	}

}
