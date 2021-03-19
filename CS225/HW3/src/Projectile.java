
public class Projectile {
	private double x, y;
	private final double initialX, initialY;
	private final double initialVelocity, gravity, rads;
	private final double groundY;
	private boolean isFlying = true;
	private String name;

	
	public Projectile(double xo, double yo, double vo, double angle, double gravity, double groundY, String name) {
		this.x = xo;
		this.y = yo;
		this.initialX = xo;
		this.initialY = yo;
		this.initialVelocity = vo;
		this.gravity = gravity;
		this.rads = Math.toRadians(angle);
		this.groundY = groundY;
		this.name = name;
	}


	public void step(double time) {
		if (!isFlying) return;

		this.x = initialVelocity * time * Math.cos(rads) + initialX;
		this.y = initialVelocity * time * Math.sin(rads) - 0.5 * gravity * time * time + initialY;

		if (y < groundY) {
			isFlying = false;
		}
	}

	
	public boolean isFlying() {
		return isFlying;
	}


	public void displayPos() {
		System.out.println("The " + name + " is at: " + x + ", " + y);
	}
	
	public double getX() {
		return x;
	}
	
	
}
