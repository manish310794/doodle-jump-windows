package manish.game.entity.powerups;

import java.awt.Graphics2D;

import manish.game.Handler;
import manish.game.Id;
import manish.game.entity.Entity;
import manish.game.gfx.Sprite;

public class Shield extends Entity{
	
	private Sprite sprite;

	public Shield(int x, int y, int width, int height, Id id, Handler handler, Sprite sprite) {
		super(x, y, width, height, id, handler);
		this.sprite = sprite;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(sprite.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
