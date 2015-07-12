package manish.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import manish.game.entity.Entity;
import manish.game.gfx.Sprite;
import manish.game.input.KeyInput;
import manish.game.input.MouseInput;

public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 650;
	public static final String GAME_NAME = "Space Walk";
	
	public static Thread thread;
	private BufferedImage image;
	public static BufferedImage level;
	public static BufferedImage topbar;
	
	public static Handler handler;
	public static Camera cam;
	public static boolean running = false;
	public static boolean gameOver = false;
	public static Sprite[] player = new Sprite[4];
	public static Sprite greenPlatform;
	public static Sprite whitePlatform;
	public static Sprite bluePlatform;
	public static Sprite brokenPlatform;
	public static Sprite[] monster = new Sprite[3];
	public static Sprite bullet;
	public static Sprite spring;
	public static Sprite shield;
	public static Sprite spaceship;
	
	public static AudioClip jumping;
	public static AudioClip gameover;
	public static AudioClip shooting;
	public static AudioClip whoosh;
	
	public static BufferedImage startGameImage;
	public static BufferedImage instructionsImage;
	public static BufferedImage gameOverImage;
	
	private JFrame frame;
	
	public Game() {
		Dimension dim = new Dimension(WIDTH, HEIGHT);
		setSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		
		/*Launcher.gameRunning = false;
		Launcher.gameOver = false;*/
		frame = new JFrame(GAME_NAME);
		frame.setFocusable(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		addKeyListener(new KeyInput());
		addMouseListener(new MouseInput());
	}
	
	private void init() {
		player[0] = new Sprite("/doodleL.png");
		player[1] = new Sprite("/doodleR.png");
		player[2] = new Sprite("/doodleS.png");
		player[3] = new Sprite("/spaceship_with_bob.png");
		greenPlatform = new Sprite("/p-green.png");
		whitePlatform = new Sprite("/p-white.png");
		bluePlatform = new Sprite("/p-blue.png");
		brokenPlatform = new Sprite("/p-brown-1.png");
		bullet = new Sprite("/bullet.png");
		spring = new Sprite("/p-green-s1.png");
		shield = new Sprite("/shield.png");
		spaceship = new Sprite("/spaceship.png");
		for(int i=0; i < monster.length; i++) {
			monster[i] = new Sprite("/bat" + (i+1) + ".png");
		}
		
		jumping = Applet.newAudioClip(getClass().getResource("/jumping.wav"));
		gameover = Applet.newAudioClip(getClass().getResource("/gameover.wav"));
		shooting = Applet.newAudioClip(getClass().getResource("/ball.wav"));
		whoosh = Applet.newAudioClip(getClass().getResource("/whoosh.wav"));
		
		try {
			level = ImageIO.read(getClass().getResource("/level.png"));
			image = ImageIO.read(getClass().getResource("/bg-grid.png"));
			startGameImage = ImageIO.read(getClass().getResource("/intro0.png"));
			instructionsImage = ImageIO.read(getClass().getResource("/instructions.png"));
			gameOverImage = ImageIO.read(getClass().getResource("/gameover.png"));
			topbar = ImageIO.read(getClass().getResource("/topbar.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*launcher = new Launcher();
		addMouseListener(launcher);*/
		cam = new Camera(0, level.getHeight() - Game.HEIGHT);
		handler = new Handler();
		handler.createLevel(level);
	}
	
	@Override
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		@SuppressWarnings("unused")
		int frames = 0;
		@SuppressWarnings("unused")
		int ticks = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(frames + " Frames per second " + ticks + " updates per second");
				frames = 0;
				ticks = 0;
			}
			
			if(!running) {
				gameOver = true;
				render();
			}
		}
		stop();
	}
	
	public void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		/*if(!running)
			return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		running = false;
		frame.setVisible(false);
		MainMenu.frame.setVisible(true);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		/*if((!Launcher.gameRunning && !Launcher.gameOver) || Launcher.gameOver) {
			launcher.render(g);
		}
		if(Launcher.gameRunning) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			g.translate(0, cam.getTranslateY());
			handler.render(g);
		} */
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.translate(0, cam.getTranslateY());
		handler.render(g);
		cam.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public void tick() {
		/*if(!Launcher.gameRunning && !Launcher.gameOver) {
			launcher.tick();
		}
		if(Launcher.gameRunning) {
*/			handler.tick();
			for(Entity en: handler.entity) {
				if(en.getId() == Id.player) {
					cam.tick(en);
				}
			}
		//}
	}
	
	/*public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}*/

}
