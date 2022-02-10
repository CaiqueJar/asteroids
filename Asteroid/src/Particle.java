
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Particle extends Rectangle {

	private static final long serialVersionUID = 1L;


	public double x, y;
	
	public int angle;
	public double dx, dy;
	
	public int timer;
	
	
	public Particle (int x, int y) {
		super(x, y, 4, 4);
		
		this.x = x;
		this.y = y;
		
		angle = Game.random.nextInt(360);
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		x+=dx*0.5;
		y+=dy*0.5;
		
		timer++;
		if(timer >= 60*2) {
			Game.renascer = true;
			Game.player.particles.remove(this);
			return;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)(x), (int)(y), width, height);

	}
}
