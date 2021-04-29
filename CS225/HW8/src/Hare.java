
public class Hare extends Projectile {
	
	public Hare(double x_0, double y_0, double angle, double velocity) {
		super(x_0, y_0, angle, velocity);
	}

	public void boost() {
		setX_0(getX_0() + 10);
	}

}
