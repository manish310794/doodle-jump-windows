package manish.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import manish.game.Game;
import manish.game.Handler;
import manish.game.Id;
import manish.game.gfx.Sprite;
import manish.game.tile.Tile;

public class Player extends Entity {
	
	public static final double GRAVITY = 12;
	public static int score;
	
	public boolean shooting = false;
	
	private Sprite[] sprite;
	private boolean shield = false;
	private int shieldTime = 15*60;
	public boolean spaceship = false;
	private int spaceshipTime = 2*60;

	public Player(int x, int y, int width, int height, Id id, Handler handler, Sprite[] sprite) {
		super(x, y, width, height, id, handler);
		this.sprite = sprite;
		Player.score = 0;
	}

	@Override
	public void render(Graphics2D g) {
		if(shield) {
			g.setColor(Color.ORANGE);
			if(facing == 1)
				g.fillOval(x-10, y, width, height);
			else
				g.fillOval(x+10, y, width, height);
		}
		if(spaceship) {
			g.drawImage(sprite[3].getBufferedImage(), x, y, sprite[3].getWidth(), sprite[3].getHeight(), null);
		}
		else if(shooting && !spaceship) {
			g.drawImage(sprite[2].getBufferedImage(), x, y, sprite[2].getWidth(), sprite[2].getHeight(), null);
			Game.shooting.play();
		}
		else
			g.drawImage(sprite[facing].getBufferedImage(), x, y, width, height, null);
	}
	
	private void gameOver() {
		Game.gameover.play();
		Game.gameOver = true;
		Game.running = false;
	}

	@Override
	public void tick() {
		x += velX;
		if(y < y + velY)
			score = Game.level.getHeight() - y;
		y += velY;
		
		//System.out.println("x = " + x + " y = " + y);
		
		if(facing == 0 && x <= -2*width/3)
			x = Game.WIDTH - 10;
		
		if(facing == 1 && x >= Game.WIDTH - width/3)
			x = 10;
		
		if(y >= Game.cam.getY() + Game.HEIGHT)
			gameOver();
		
		if(shield) {
			if(shieldTime > 0)
				shieldTime--;
			else {
				shieldTime = 15*60;
				shield = false;
			}
		}
		
		if(spaceship) {
			if(spaceshipTime > 0) {
				spaceshipTime--;
				velY = -10;
				jumping = true;
				return;
			}
			else {
				spaceshipTime = 5*60;
				spaceship = false;
				jumping = false;
			}
		}
		
		for(int i=0; i < handler.tile.size(); i++) {
			Tile t = handler.tile.get(i);
			if(t.getId() == Id.brick) {
				if(getBoundsBottom().intersects(t.getBoundsTop())) {
					setVelY(0);
					if(falling) {
						falling = false;
						gravity = GRAVITY;
						jumping = true;
						Game.jumping.play();
					}
				}
			}
			if(t.getId() == Id.broken_brick) {
				if(getBoundsBottom().intersects(t.getBoundsTop())) {
					if(falling)
						handler.removeTile(t);
				}
			}
			if(t.getId() == Id.moving_brick) {
				if(getBoundsBottom().intersects(t.getBoundsTop())) {
					setVelY(0);
					if(falling) {
						falling = false;
						gravity = GRAVITY;
						jumping = true;
						Game.jumping.play();
					}
				}
			}
			if(t.getId() == Id.spring) {
				if(getBoundsBottom().intersects(t.getBoundsTop())) {
					setVelY(0);
					if(falling) {
						falling = false;
						gravity = GRAVITY + 5;
						jumping = true;
					}
				}
			}
			if(t.getId() == Id.monster) {
				if(getBoundsBottom().intersects(t.getBoundsTop())) {
					if(falling) {
						handler.removeTile(t);
						falling = false;
						gravity = GRAVITY;
						jumping = true;
						Game.jumping.play();
					}
				}
				else if(getBounds().intersects(t.getBounds()) && !shield) {
					handler.removeEntity(this);
					gameOver();
				}
			}
		}
		
		for(int i=0; i < handler.entity.size(); i++) {
			Entity en = handler.entity.get(i);
			if(en.getId() == Id.shield) {
				if(getBounds().intersects(en.getBounds())) {
					shield = true;
					handler.removeEntity(en);
				}
			}
			if(en.getId() == Id.spaceship) {
				if(getBounds().intersects(en.getBounds())) {
					spaceship = true;
					handler.removeEntity(en);
					Game.whoosh.play();
				}
			}
		}
		
		if(jumping) {
			gravity -= 0.3;
			setVelY((int)-gravity);
			if(gravity <= 0.0) {
				jumping = false;
				gravity = 1.0;
				falling = true;
			}
		}
		
		if(falling) {
			gravity += 0.3;
			setVelY((int)gravity);
		}
	}

}
