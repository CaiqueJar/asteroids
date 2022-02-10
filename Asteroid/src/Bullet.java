import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Bullet extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	
	public double x, y;
	
	public int timer;
	public int spd = 12;
	
	public double dx, dy;
	
	public Bullet(int x, int y, int width, int height) {
		super(x, y, width, height);
		
		this.x = x;
		this.y = y;
		
		dx = Game.player.dx;
		dy = Game.player.dy;
		
		if(dx == 0) dx = 1;
	}
	
	public void tick() {
		timer++;
		if(timer == 60*0.5) {
			Game.bullets.remove(this);
			return;
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
		
		
		x+=(dx)*spd;
		y+=(dy)*spd;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		
		g.fillRect((int)(x), (int)(y), width, height);
	}
}
