package manish.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import manish.game.Game;
import manish.game.Id;
import manish.game.entity.Entity;

public class KeyInput implements KeyListener{

	@Override
	public void keyPressed(KeyEvent ke) {
		for(Entity en: Game.handler.entity) {
			if(en.getId() == Id.player) {
				int key = ke.getKeyCode();
				switch(key) {
				case KeyEvent.VK_A:
					en.setVelX(-5);
					en.facing = 0;
					break;
				case KeyEvent.VK_D:
					en.setVelX(5);
					en.facing = 1;
					break;
			}
		}
	}
}

	@Override
	public void keyReleased(KeyEvent ke) {
		for(Entity en: Game.handler.entity) {
			if(en.getId() == Id.player) {
				int key = ke.getKeyCode();
				switch(key) {
				case KeyEvent.VK_A:
					en.setVelX(0);
					break;
				case KeyEvent.VK_D:
					en.setVelX(0);
					break;
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		
	}

}
