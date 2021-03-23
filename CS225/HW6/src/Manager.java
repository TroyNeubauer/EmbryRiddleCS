
public class Manager {

	public static final double GRAVITY = 9.81;
	private static final double TIME_STEP= 0.5;

	private static double[] heights;

	private static void simulate(Projectile projectile) {
		heights = new double[20];
		double time = 0.0;
		final double k = 0.5;

		for (int i = 0; i < heights.length; i++) {
			heights[i] = rand(100, 350);
		}

		while (!projectile.isDead()) {
			System.out.println(" t=" + time);
			projectile.print();

			projectile.update(time, TIME_STEP);
			time += TIME_STEP;
		}
		System.out.println("Landed!!");
	}

	public static void main(String[] args) {
		

		Hare hare = new Hare(0, rand(400, 1000), rand(70, 120), rand(0, 90), GRAVITY);
		simulate(hare);

		Hound hound = new Hound(0, 600);
		simulate(hound);

		System.out.println("Final positions");
		hare.print();
		hound.print();
		
		double distance = Math.sqrt(Math.pow(Math.abs(hare.getX() - hound.getX()), 2) + Math.pow(Math.abs(hare.getY() - hound.getY()), 2));

		System.out.println("Distance: " + distance);
		if (distance < 7.0)
			System.out.println("The hound wins!");
		else
			System.out.println("The hare wins!");

	}


	public static boolean isDead(double x, double y) {

		if (x >= 2000) return true;
		if (y >= 2500) return true;
		if (x < 0.0) return true;
		double height = heights[(int) x / 100];
		return y <= height;
	}



	public static double rand(double lower, double upper) {
		return lower + Math.random() * (upper - lower);
	}

}
