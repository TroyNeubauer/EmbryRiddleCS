
public class Hound extends Projectile {
	
	public Hound(double x_0, double y_0) {
		super(x_0, y_0, 0, 0);
	}

	@Override
	public void move(double time) {
		setX(getX_0() + (11.3*time));
		setY(getY_0() - (9.81*time));
	}
	
	public void burnFuel() {
		setY_0(getY_0() + 11);
	}

}
