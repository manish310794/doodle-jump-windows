package manish.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import manish.game.entity.Entity;
import manish.game.entity.Player;
import manish.game.entity.powerups.Shield;
import manish.game.entity.powerups.Spaceship;
import manish.game.tile.Brick;
import manish.game.tile.BrokenBrick;
import manish.game.tile.Monster;
import manish.game.tile.MovingBrick;
import manish.game.tile.MovingMonster;
import manish.game.tile.SlidingMonster;
import manish.game.tile.Tile;

public class Handler {
	
	public LinkedList<Entity> entity;
	public LinkedList<Tile> tile;
	
	public Handler() {
		entity = new LinkedList<Entity>();
		tile = new LinkedList<Tile>();
		//createLevel();
	}
	
	public void addEntity(Entity en) {
		entity.add(en);
	}
	
	public void removeEntity(Entity en) {
		entity.remove(en);
	}
	
	public void addTile(Tile t) {
		tile.add(t);
	}
	
	public void removeTile(Tile t) {
		tile.remove(t);
	}
	
	public void render(Graphics2D g) {
		for(int i=0; i < tile.size(); i++) {
			Tile t = tile.get(i);
			t.render(g);
		}
		
		for(int i=0; i < entity.size(); i++) {
			Entity en = entity.get(i);
			en.render(g);
		}
	}
	
	public void tick() {
		for(Entity en: entity) {
			en.tick();
		}
		
		for(Tile t: tile) {
			t.tick();
		}
	}
	
	public void createLevel(BufferedImage image) {
		if(image != null) {
			for(int j=0; j<image.getHeight(); j++) {
				for(int i=0; i<image.getWidth(); i++) {
					int pixel = image.getRGB(i, j);
					int red = (pixel >> 16) & 0xff;
					int green = (pixel >> 8) & 0xff;
					int blue = (pixel) & 0xff;
					
					/*if(red == 0 && blue == 0 && green == 0)
						addTile(new Brick(i, j, Game.greenPlatform.getWidth(), Game.greenPlatform.getHeight(), Id.brick, this, Game.greenPlatform));
					
					if(red == 0 && blue == 255 && green == 0)
						addEntity(new Player(i, j, Game.player[0].getWidth(), Game.player[0].getHeight(), Id.player, this, Game.player));
					
					if(red == 0 && blue == 0 && green == 255)
						addTile(new MovingBrick(i, j, Game.bluePlatform.getWidth(), Game.bluePlatform.getHeight(), Id.moving_brick, this, Game.bluePlatform));
					
					if(red == 255 && blue == 0 && green == 0)
						addTile(new Spring(i, j, Game.spring.getWidth(), Game.spring.getHeight(), Id.spring, this, Game.spring));
					
					if(i == 195 && j == 2430)
						addEntity(new Spaceship(i, j, Game.spaceship.getWidth(), Game.spaceship.getHeight(), Id.spaceship, this, Game.spaceship));
					
					if(i == 100 && j == 2000)
						addTile(new Monster(i, j, Game.monster[0].getWidth(), Game.monster[0].getHeight(), Id.monster, this, Game.monster));*/
					
					if(red == 0 && blue == 255 && green == 0)
						addEntity(new Player(i, j, Game.player[0].getWidth(), Game.player[0].getHeight(), Id.player, this, Game.player));
						
					if(red == 0 && blue == 200 && green == 0)
						addEntity(new Shield(i, j, Game.shield.getWidth(), Game.shield.getHeight(), Id.shield, this, Game.shield));
						
					if(red == 0 && blue == 100 && green == 0)
						addEntity(new Spaceship(i, j, Game.spaceship.getWidth(), Game.spaceship.getHeight(), Id.spaceship, this, Game.spaceship));
						
						if(red == 255 && blue == 0 && green == 0)
							addTile(new Brick(i, j, Game.greenPlatform.getWidth(), Game.greenPlatform.getHeight(), Id.brick, this, Game.greenPlatform));
						
						if(red == 200 && blue == 0 && green == 0)
							addTile(new BrokenBrick(i, j, Game.brokenPlatform.getWidth(), Game.brokenPlatform.getHeight(), Id.broken_brick, this, Game.brokenPlatform));
								
						if(red == 150 && blue == 0 && green == 0)
							addTile(new MovingBrick(i, j, Game.bluePlatform.getWidth(), Game.bluePlatform.getHeight(), Id.moving_brick, this, Game.bluePlatform));
						
						if(red == 0 && blue == 0 && green == 255)
							addTile(new Monster(i, j, Game.monster[0].getWidth(), Game.monster[0].getHeight(), Id.monster, this, Game.monster));
							
						if(red == 0 && blue == 0 && green == 200)
							addTile(new MovingMonster(i, j, Game.monster[0].getWidth(), Game.monster[0].getHeight(), Id.monster, this, Game.monster));
						
						if(red == 0 && blue == 0 && green == 150)
							addTile(new SlidingMonster(i, j, Game.monster[0].getWidth(), Game.monster[0].getHeight(), Id.monster, this, Game.monster));
				}
			}
		}
	}
	
}
