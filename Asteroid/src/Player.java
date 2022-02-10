import java.awt.Rectangle;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public BufferedImage sprite;
	
	public boolean right, left, up, shoot, dead, criarParticles;
	public int rotation;
	
	public double dx, dy, x, y, aceleration;
	
	
	public double spd = 3.5;
	
	public List<Particle> particles;


	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.x = x;
		this.y = y;
		
		try {
			sprite = ImageIO.read(getClass().getResource("/player.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		particles = new ArrayList<Particle>();

	}	
	
	public void tick() {
		
		if(!dead) {
			if (right) {
				rotation+=spd;
			}
			else if(left) {
				rotation-=spd;
			}
			
			
			
			if(x + width < 0) {
				x = Game.WIDTH;
			}
			else if(x > Game.WIDTH) {
				x = 0;
			}
			
			if(y + height < 0) {
				y = Game.HEIGHT;
			}
			else if(y > Game.HEIGHT) {
				y = 0;
			}
			
	
			if(up) {
				if(aceleration < 10) 
					aceleration+=0.075;
				
			}
			else {
				aceleration-=0.075;
				
				if(aceleration < 0) 
					aceleration = 0;
				
				
			}
			dx = Math.cos(Math.toRadians(rotation));
			dy = Math.sin(Math.toRadians(rotation));
			
			x+=dx*aceleration;
			y+=dy*aceleration;
			
			
			if(shoot) {
				shoot = false;
				
				Bullet bullet = new Bullet((int)(x+32/2), (int)(y+32/2), 6, 6);
				Game.bullets.add(bullet);
			}
			
			for(int i = 0; i < Game.asteroids.size(); i++) {
				
				Rectangle mask1 = new Rectangle((int)(x), (int)(y), width, height);
				Rectangle mask2 = new Rectangle((int)(Game.asteroids.get(i).x), (int)(Game.asteroids.get(i).y), Game.asteroids.get(i).width, Game.asteroids.get(i).height);
	
				
				if(mask1.intersects(mask2)) {
					dead = true;;
				}
			}
		}
		else {
			if(criarParticles == false) {
				criarParticles = true;
				
				for(int i = 0; i < 8; i ++) {
					particles.add(new Particle((int)x, (int)y));
				}
			}
			
			
			for(int i = 0; i < particles.size(); i ++) {
				particles.get(i).tick();
			}
		}
	}

	public void render(Graphics2D g) {
		if(dead == false) {
			g.rotate(Math.toRadians(rotation), x+width/2, y+height/2);
			g.drawImage(sprite, (int)(x), (int)(y), width, height, null);
			g.rotate(Math.toRadians(-rotation), x+width/2, y+height/2);
		}
		else {
			for(int i = 0; i < particles.size(); i ++) {
				particles.get(i).render(g);
			}
		}
	}
}
