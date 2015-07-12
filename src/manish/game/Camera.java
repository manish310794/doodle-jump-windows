package manish.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import manish.game.entity.Entity;
import manish.game.entity.Player;

public class Camera {
	public int x, y;
	public int translateY;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
		translateY = -y;
	}
	
	public void render(Graphics2D g) {
		g.drawImage(Game.topbar, x, y, Game.topbar.getWidth(), Game.topbar.getHeight(), null);
		g.setColor(Color.RED);
		g.setFont(new Font("Times New Roman", 15, 30));
		g.drawString("Score", 400, y+30);
		g.drawString(Player.score+"", 500, y+30);
	}
	
	public void tick(Entity player) {
		if(player.jumping) {
			if(player.getY() <= y + Game.HEIGHT/2) {
				y -= (y + Game.HEIGHT/2) - player.getY();
				translateY = -y;
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getTranslateY() {
		return translateY;
	}
	
}
