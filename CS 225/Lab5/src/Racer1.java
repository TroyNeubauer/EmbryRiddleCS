
public class Racer1 extends GenericRacer {
	
	public Racer1(String name) {
		super(name);
	}

	// example of a random number generator given a lower and higher bound
	public int randomFrom (int low, int high) {

		int randNum = 0;

		// (int) is casting since Math.random() return a double and randNum is an int
		randNum = (int) (Math.random()*(high-low) + low);

		return randNum;
	}
	
	// override the parents move method
	@Override
	public void move() {

		// declare variables
		int move; 
		// flip a coin
		int randNum = randomFrom(0, 2);

		// if 1 move 4
		if (randNum == 1) {
			move = 4;

		// if 0 move 8
		} else {
			move = 8;
		}
		setLocation(getLocation() + move); 
	}

}
