package manish.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import manish.game.entity.Player;

public class MainMenu extends Canvas implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage startGame;
	private BufferedImage instructions;
	public static JFrame frame;
	private boolean ins = false;
	
	public MainMenu() {
		Dimension dim = new Dimension(Game.WIDTH, Game.HEIGHT);
		setSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		frame = new JFrame(Game.GAME_NAME);
		frame.setFocusable(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		try {
			startGame = ImageIO.read(getClass().getResource("/intro0.png"));
			instructions = ImageIO.read(getClass().getResource("/instructions.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		addMouseListener(this);
		
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(Game.gameOver) {
			frame.setVisible(true);
			g.drawImage(Game.gameOverImage, 0, 0, Game.WIDTH, Game.HEIGHT, null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Times New Roman", 30, 50));
			g.drawString("Your score:", 100, 300);
			g.drawString(Player.score+"", 350, 300);
		}
		else if(ins) {
			g.drawImage(instructions, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		}
		else
			g.drawImage(startGame, 0, 0, Game.WIDTH, Game.HEIGHT, null);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		int x = me.getX();
		int y = me.getY();
		if(Game.gameOver) {
			Game.gameOver = false;
			if(x > 245 && x < 370 && y > 445 && y < 495)
				startGame();
			if(x > 245 && x < 370 && y > 510 && y < 560)
				repaint();
		}
		if(ins) {
			if(x > 230 && x < 355 && y > 490 && y < 540) {
				ins = false;
				repaint();
			}
		}
		if(x > 395 && x < 530 && y > 410 && y < 460) {
			ins = true;
			repaint();
		}
		if(x > 100 && x < 230 && y > 220 && y < 270) {
			startGame();
			//frame.setVisible(true);
		}
	}
	
	public void startGame() {
		Game game = new Game();
		frame.setVisible(false);
		game.start();
		/*try {
			game.thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
