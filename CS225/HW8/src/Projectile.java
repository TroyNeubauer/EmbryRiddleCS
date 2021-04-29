
public class Projectile {

	//create attributes for the projectiles (take them from the hw doc)
	//don't forget to generate setters and getters
	private double x = 0;
	private double y;
	private double x_0 = 0;
	private double y_0 = 0;
	private double angle = 0;
	private double velocity = 0;

	//write the constructor so it takes in the inputs for each projectile and puts the
	//values in the right attributes
	public Projectile(double x_0, double y_0, double angle, double velocity) {
		//take a look back at Racer1 from lab 4 for help
		this.x_0 = x_0;
		this.y_0 = y_0;
		this.y = y_0;
		this.angle = Math.toRadians(angle);
		this.velocity = velocity;
	}
	
	public void move(double time) {
		
		//just put the equations from the hw doc in here
		x = (time * velocity * Math.cos(angle)) + x_0;
		y = (time * velocity * Math.sin(angle)) - (0.5 * 9.81 * Math.pow(time,2)) + y_0;
		
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX_0() {
		return x_0;
	}

	public void setX_0(double x_0) {
		this.x_0 = x_0;
	}

	public double getY_0() {
		return y_0;
	}

	public void setY_0(double y_0) {
		this.y_0 = y_0;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
}
