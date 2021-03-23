
public class Hare extends Projectile {
	private final double xo, yo;
	private final double vo, angle, gravity;

	public Hare(double xo, double yo, double vo, double angle, double gravity) {
		super(xo, yo);
		this.xo = xo;
		this.yo = yo;
		this.vo = vo;
		this.angle = angle;
		this.gravity = gravity;
	}

	public void update(double time, double dt) {

		setX(vo * time * Math.cos(Math.toRadians(angle)) + xo);
		setY(vo * time * Math.sin(Math.toRadians(angle)) - 0.5 * gravity * time * time + yo);

		updatePosition(10 / 1.5, 0, dt);
	}

}
