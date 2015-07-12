package manish.game.entity;

import java.awt.Graphics2D;

import manish.game.Game;
import manish.game.Handler;
import manish.game.Id;
import manish.game.gfx.Sprite;
import manish.game.tile.Monster;
import manish.game.tile.Tile;

public class Bullet extends Entity{
	
	private double dx, dy;
	private double newX, newY;
	private Sprite sprite;

	public Bullet(int x, int y, int width, int height, Id id, Handler handler, Sprite sprite) {
		super(x, y, width, height, id, handler);
		this.sprite = sprite;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(sprite.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		newX += dx;
		x += (int)newX;
		newY += dy;
		y += (int) newY;
		
		if(x <= 0 || y <= Game.cam.getY() - Game.HEIGHT || x >= Game.WIDTH)
			handler.removeEntity(this);
		
		for(int i=0; i < handler.tile.size(); i++) {
			Tile tile = handler.tile.get(i);
			if(tile.getId() == Id.monster) {
				Monster monster = (Monster) tile;
				if(getBounds().intersects(monster.getBounds())) {
					monster.hits--;
					if(monster.hits == 0)
						handler.removeTile(monster);
					handler.removeEntity(this);
				}
			}
		}
	}
	
	public void setVelX(double dx) {
		this.dx = dx;
	}
	
	public void setVelY(double dy) {
		this.dy = dy;
	}

}
