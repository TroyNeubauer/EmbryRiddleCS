
public class Manager {
	
	private double plat[];
	
	private Hare hare;
	private Hound hound;

	public Manager() {
		genPlateaus();
		hare = new Hare(0, randomFrom(400, 1000), randomFrom(0, 90), randomFrom(70, 120));
		hound = new Hound(0, 600);
	}
	
	public void genPlateaus() {
		plat = new double[20];
		for (int i = 0; i < plat.length; i++) {
			plat[i] = randomFrom(100, 350);
		}
	}
	
	public double randomFrom (int low, int high) {

		//taken from lab 3/4
		
		double randNum = 0;
		randNum = (Math.random()*(high-low) + low);
		return randNum;
	}
	
	public boolean gameOver(Projectile proj) {
		if ((proj.getY() > plat[(int)(proj.getX()/100)]) && (proj.getX() < 2000 && proj.getY() < 2500)) {
			return false;
		} else {
			return true;
		}
	}
	
	public String distanceBetween(Projectile hare, Projectile hound) {
		
		//print out the distance between the projectiles
		return ("The distance between the hare and hound is " + Math.abs(hare.getX() - hound.getX()));
	}
	
	public String winner(Projectile hare, Projectile hound) {
		
		return ("The winner is " + ((Math.abs(hare.getX()-hound.getX())>7) ? "hare" : "hound") + "!");
	}

	public double[] getPlat() {
		return plat;
	}

	public void setPlat(double[] plat) {
		this.plat = plat;
	}

	public Hare getHare() {
		return hare;
	}

	public void setHare(Hare hare) {
		this.hare = hare;
	}

	public Hound getHound() {
		return hound;
	}

	public void setHound(Hound hound) {
		this.hound = hound;
	}

}

