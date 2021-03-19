
public class Manager {
		
	private static final double GRAVITY = 9.81;
	private static final double TIME_STEP= 0.5;
	
	public static void main(String[] args) {


		for (int i = 0; i < 2; i++) {
			double heigth = (i == 0) ? 0 : 120;
			double time = 0.0;
			System.out.println("\n\n==============================\n");
			System.out.println("Starting iteration #" + i + "\n");

			Projectile hare = new Projectile(0, rand(400, 1000), rand(70, 120), rand(0, 90), GRAVITY, heigth, "Hare");
			Projectile hound = new Projectile(0, 600, rand(50, 150), rand(0, 90), GRAVITY, heigth, "Hound");
		
			while (hare.isFlying() && hound.isFlying()) {

				hare.displayPos();
				hound.displayPos();
					
				hare.step(time);
				hound.step(time);
				time += TIME_STEP;
					
			}
			System.out.println("Hare and hound hit ground " + Math.abs(hare.getX() - hound.getX()) + " Units apart");
		
		}
		

	}

	private static double rand(double lower, double upper) {
		return lower + Math.random() * (upper - lower);
	}
}
