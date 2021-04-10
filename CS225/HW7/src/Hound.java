
public class Hound extends Projectile {

	public Hound(double x, double y) {
		super(0.0, 600.0);
	}

	public void update(double time, double dt) {

		updatePosition(11.3, Manager.GRAVITY, dt);

		// AI:
		if (Manager.rand(0, 100) <= 5) 
			burnFuel();
	}

	public void burnFuel() {
		setY(getY() + 11);
	}

}
