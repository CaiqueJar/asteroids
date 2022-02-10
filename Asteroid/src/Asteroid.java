
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Asteroid extends Rectangle {

	private static final long serialVersionUID = 1L;


	public double x, y;
	public int width, height;
	
	public int typeSpr;
	
	public BufferedImage sprite;
	
	public int angle;
	public double dx, dy;
	
	public int vida, timer;
	
	
	public Asteroid(int x, int y, int vida) {
		super(x, y, 0, 0);
		
		this.x = x;
		this.y = y;
		
		if(dx == 0) dx = 1;
		
		
		this.vida = vida;
		
		
		typeSpr = Game.random.nextInt(3);
		
		try {
			switch(typeSpr) {
			case 0:
				sprite = ImageIO.read(getClass().getResource("/asteroid1.png"));
				break;
				
			case 1:
				sprite = ImageIO.read(getClass().getResource("/asteroid2.png"));
				break;
				
			case 2:
				sprite = ImageIO.read(getClass().getResource("/asteroid3.png"));
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void tick() {
		timer++;
		
		if(timer == 60*5) {
			Game.asteroids.remove(this);
		}

		switch(vida) {
		case 2:
			width = 64;
			height = 64;
			break;
			
		case 1:
			width = 32;
			height = 32;
			break;
			
		case 0:
			width = 20;
			height = 20;
			break;
		}
		
		x+=dx*2;
		y+=dy*2;
		
		
		for(int i = 0; i < Game.bullets.size(); i++) {
			
			Rectangle mask1 = new Rectangle((int)(x), (int)(y), width, height);
			Rectangle mask2 = new Rectangle((int)(Game.bullets.get(i).x), (int)(Game.bullets.get(i).y), Game.bullets.get(i).width, Game.bullets.get(i).height);

			
			if(mask1.intersects(mask2)) {
				Game.bullets.remove(i);
				
				vida--;

				Asteroid asteroid1 = new Asteroid((int)x, (int)y, vida);
				Asteroid asteroid2 = new Asteroid((int)x, (int)y, vida);
				
				asteroid1.angle = Game.random.nextInt(360);
				asteroid1.dx = Math.cos(Math.toRadians(angle));
				asteroid1.dy = Math.sin(Math.toRadians(angle));

				asteroid2.dx = asteroid1.dx * (-1);
				
				Game.asteroids.remove(this);
				Game.asteroids.add(asteroid1);
				Game.asteroids.add(asteroid2);

			}
		}
		if(vida < 0) {
			Game.asteroids.remove(this);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, (int)(x), (int)(y), width, height, null);
	}
}
