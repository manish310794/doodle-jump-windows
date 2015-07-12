package manish.game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import manish.game.Game;
import manish.game.Id;
import manish.game.entity.Bullet;
import manish.game.entity.Entity;
import manish.game.entity.Player;

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent me) {
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		for(int i=0; i < Game.handler.entity.size(); i++) {
			Entity en = Game.handler.entity.get(i);
			if(en.getId() == Id.player) {
				Player player = (Player) en;
				if(player.spaceship)
					return;
				player.shooting = true;
				int x = player.getX()-10+player.getWidth()/2;
				int y = player.getY()-20;
				Bullet bullet = new Bullet(x, y, 10, 10, Id.bullet, Game.handler, Game.bullet);
				int targetX = me.getX();
				int targetY = Game.cam.getY() + me.getY();
				
				double dx = targetX - x;
				double dy = targetY - y;
				double m = dy/dx;
				
				/*System.out.println("X = " + x + " Y = " + y);
				System.out.println("targetX = " + targetX + " targetY = " + targetY);
				System.out.println("CamY = " + Game.cam.getY());*/
				
				if(m < 0) {
					if(dx > Math.abs(dy)) {
						bullet.setVelX(1.0);
						bullet.setVelY(m);
					}
					else {
						bullet.setVelX(1/Math.abs(m));
						bullet.setVelY(-1.0);
					}
				}
				else {
					if(dx < dy) {
						bullet.setVelX(-1.0/m);
						bullet.setVelY(-1.0);
					}
					else {
						bullet.setVelX(-1.0);
						bullet.setVelY(-m);
					}
				}
				
				
				Game.handler.addEntity(bullet);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		for(int i=0; i < Game.handler.entity.size(); i++) {
			Entity en = Game.handler.entity.get(i);
			if(en.getId() == Id.player) {
				Player player = (Player) en;
				player.shooting = false;
			}
		}
	}

}
