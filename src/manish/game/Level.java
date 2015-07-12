package manish.game;

import manish.game.tile.Brick;

public class Level {

	public void createBase(int i, int j, Handler handler) {
		handler.addTile(new Brick(i, j, Game.greenPlatform.getWidth(), Game.greenPlatform.getHeight(), Id.brick, handler, Game.greenPlatform));
		handler.addTile(new Brick(i+100, j+100, Game.greenPlatform.getWidth(), Game.greenPlatform.getHeight(), Id.brick, handler, Game.greenPlatform));
		handler.addTile(new Brick(i+200, j+300, Game.greenPlatform.getWidth(), Game.greenPlatform.getHeight(), Id.brick, handler, Game.greenPlatform));
		//handler.addTile(new Brick(i, j, Game.greenPlatform.getWidth(), Game.greenPlatform.getHeight(), Id.brick, handler, Game.greenPlatform));
	}
	
}
