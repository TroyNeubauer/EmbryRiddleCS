
public abstract class Projectile {
	private double x, y;

	public Projectile(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public abstract void update(double time, double dt);

	public boolean isDead() {
		return Manager.isDead(getX(), getY());
	}

	public void updatePosition(double vx, double vy, double dt) {
		x += vx * dt;
		y += vy * dt;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public void print() {
		System.out.println(this.getClass().getSimpleName() + " At x: " + x + " y: " + y);
	}


}
