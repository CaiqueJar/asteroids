
public class Spawner {

	public int dir;

	public void tick() {
		if (Game.random.nextInt(100) < 3) {
			dir = Game.random.nextInt(4);
			int vida = Game.random.nextInt(3);
			
			int angle;
			double dx, dy;
			
			Asteroid asteroid;
			
			switch (dir) {
			case 0:
				angle = Game.random.nextInt(90);
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));
				
				asteroid = new Asteroid(0, Game.random.nextInt(Game.HEIGHT), vida);
				
				asteroid.angle = angle;
				asteroid.dx = dx;
				asteroid.dy = dy;
				
				Game.asteroids.add(asteroid);
				break;

			case 1:
				angle = Game.random.nextInt(90)-315;
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));

				asteroid = new Asteroid(Game.random.nextInt(Game.WIDTH), 0, vida);
				
				asteroid.dx = dx;
				asteroid.dy = dy;
				
				Game.asteroids.add(asteroid);
				break;

			case 2:
				angle = Game.random.nextInt(90)+120;
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));
				
				asteroid = new Asteroid(Game.WIDTH, Game.random.nextInt(Game.HEIGHT), vida);
				
				asteroid.dx = dx;
				asteroid.dy = dy;
				
				Game.asteroids.add(asteroid);
				break;
			case 3:
				angle = Game.random.nextInt(90)+225;
				dx = Math.cos(Math.toRadians(angle));
				dy = Math.sin(Math.toRadians(angle));

				asteroid = new Asteroid(Game.random.nextInt(Game.WIDTH), Game.HEIGHT, vida);
				
				asteroid.dx = dx;
				asteroid.dy = dy;
				
				Game.asteroids.add(asteroid);
				break;
			}

			
		}
	}

}
