
public class Manager {

	public static final double GRAVITY = 9.81;
	private static final double TIME_STEP= 0.1;

	private static double[] heights;
	private static double time = 0;
	
	private static Hare hare = null;
	private static Hound hound = null;
	

	private static void simulate(Projectile projectile) {
		double time = 0.0;
		final double k = 0.5;
		while (!projectile.isDead()) {
			System.out.println(" t=" + time);
			projectile.print();

			projectile.update(time, TIME_STEP);
			time += TIME_STEP;
		}
		System.out.println("Landed!!");
	}

	public void oneIteration() {
		if (hare.isDead() && !hound.isDead()) {
			hound.update(time, TIME_STEP);
		} else {
			hare.update(time, TIME_STEP);
		}
		time += TIME_STEP;
	}

	public static boolean isDone() {
		return hound.isDead() && hare.isDead();
	}

	public static void init() {
		
		heights = new double[20];
		for (int i = 0; i < heights.length; i++) {
			heights[i] = rand(100, 350);
		}
		hare = new Hare(0, rand(400, 1000), rand(70, 120), rand(0, 90), GRAVITY);

		hound = new Hound(0, 600);
	}


	public static boolean isDead(double x, double y) {

		if (x >= 2000) return true;
		if (y >= 2500) return true;
		if (x < 0.0) return true;
		double height = heights[(int) x / 100];
		return y <= height;
	}

	public static double getHeight(double x) {
		double height = heights[(int) x / 100];
		return height;
	}

	public static String getWinner() {
		double distance = Math.sqrt(Math.pow(Math.abs(hare.getX() - hound.getX()), 2) + Math.pow(Math.abs(hare.getY() - hound.getY()), 2));

		if (distance < 7.0)
			return "The hound wins!";
		else
			return "The hare wins!";

	}



	public static double rand(double lower, double upper) {
		return lower + Math.random() * (upper - lower);
	}


	public static Projectile getHare() {
		return hare;
	}

	public static Projectile getHound() {
		return hound;
	}
}
