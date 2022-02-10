import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 480, HEIGHT = 480;

	public static Random random = new Random();
	
	private static Image icon = null;
	
	public static int gameState = 0, startTimer;
	public boolean startFX;
	
	
	public static Player player;
	public static boolean renascer;
	
	public static List<Bullet> bullets;
	public static List<Asteroid> asteroids;
	
	public static Spawner spawner;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addKeyListener(this);
		
		player = new Player((WIDTH/2)-32, (HEIGHT/2)-32, 32, 32);
		
		bullets = new ArrayList<Bullet>();
		asteroids = new ArrayList<Asteroid>();
		
		spawner = new Spawner();
		
		
		
		try {
			icon = ImageIO.read(getClass().getResource("/icon.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		
		
		
		JFrame frame = new JFrame("Asteroids");
		frame.add(game);
		frame.pack();
		frame.setIconImage(icon);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		game.run();
	}
	
	
	public void tick() {
		
		if(gameState == 1) {
			if(renascer) {
				renascer = false;
				player = new Player((WIDTH/2)-32, (HEIGHT/2)-32, 32, 32);
				bullets = new ArrayList<Bullet>();
				asteroids = new ArrayList<Asteroid>();
				
				spawner = new Spawner();
			}
			
			player.tick();
			
			for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).tick();
			}
			
			for(int i = 0; i < asteroids.size(); i++) {
				asteroids.get(i).tick();
			}
			
			spawner.tick();
		}
		else if(gameState == 0){
			startTimer++;

			if(startTimer == 60*0.5) {
				startTimer = 0;
				
				if(startFX) startFX = false;
				else startFX = true;
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == 1) {
			player.render((Graphics2D) g);
			
			for(int i = 0; i < bullets.size(); i++) {
				bullets.get(i).render(g);
			}
			
			for(int i = 0; i < asteroids.size(); i++) {
				asteroids.get(i).render(g);
			}
		}
		else {
			g.setColor(Color.white);
			
			g.setFont(new Font("Arial Black", Font.BOLD, 40));
			
			g.drawString("ASTEROIDS", Game.WIDTH/2-135, Game.HEIGHT/2-100);
			
			if(startFX) {
				g.setFont(new Font("Arial Black", Font.BOLD, 18));
				g.drawString("Press start", Game.WIDTH/2-50, Game.HEIGHT/2+120);

			}
		}
		
		bs.show();
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			tick();
			render();
			
			try {
				Thread.sleep(1000 / 60);
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(gameState == 1) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player.right = true;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				player.left = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				player.up = true;
			}
			
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				player.shoot = true;
			}
		}
		else if(gameState == 0) {
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				gameState = 1;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
